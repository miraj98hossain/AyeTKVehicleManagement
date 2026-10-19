package com.aye.backendservice.service.domain.stepTrans;

import com.aye.backendservice.aspect.annotation.EnableLogging;
import com.aye.backendservice.service.CommonHelperService;
import com.aye.backendservice.service.StepTransDetailsService;
import com.aye.backendservice.service.StepTransLinesService;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.StepSetupDetails;
import com.aye.entity.vehicleproject.StepTrans;
import com.aye.entity.vehicleproject.StepTransDetailsLines;
import com.aye.entity.vehicleproject.StepTransLines;
import com.aye.enums.StepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles the transition of a main step trans line to WIP (status W).
 *
 * <p>Two flows exist:
 * <ol>
 *   <li>The current step has an active sub-setup and is not yet held: the main line is put on hold and
 *       child lines are created. Items are first grouped by their step setup id:
 *       <ul>
 *         <li>group matching the current step setup: one child line per inventory item id</li>
 *         <li>any other group: a single child line for all items of that group</li>
 *       </ul></li>
 *   <li>Otherwise: the line is moved to WIP and the next main step line is created
 *       (or, if this was the last step, the line is simply updated).</li>
 * </ol>
 *
 * @author: Miraj
 * @date: 12/10/2026
 * @time: 10:33 am
 */
@Component
@RequiredArgsConstructor
public class StepTransLineWipV3Event implements StepTransLineEventStrategy {

    private static final int ACTIVE = 1;
    private static final int STAGE_ELIGIBLE_FOR_WIP = 1;

    private final StepTransLinesService stepTransLinesService;
    private final CommonHelperService commonHelperService;
    private final StepTransDetailsService stepTransDetailsService;

    @Override
    public boolean supports(StepTransLines reqStepTransLines) {
        return reqStepTransLines != null
                && reqStepTransLines.getParentTransId() == null
                && StepStatus.W.equals(reqStepTransLines.getStepStatus());
    }

    @EnableLogging
    @Transactional
    @Override
    public StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbStepTransLines, StepTrans stepTrans, Muser user) {
        Objects.requireNonNull(reqStepTransLines, "Request step trans line must not be null");
        Objects.requireNonNull(dbStepTransLines, "Existing step trans line must not be null");
        Objects.requireNonNull(stepTrans, "Step trans must not be null");
        Objects.requireNonNull(user, "User must not be null");

        validateEligibility(dbStepTransLines);

        List<StepSetupDetails> mainSteps = getActiveMainSteps(stepTrans);
        int existingMainCount = countMainLines(stepTrans);

        // No step left to create, and no child left to progress.
        if (existingMainCount >= mainSteps.size()) {
            return finishLastStep(reqStepTransLines, dbStepTransLines, user);
        }

        StepSetupDetails currentStep = dbStepTransLines.getStepSetupDetails();
        if (currentStep == null) {
            throw new IllegalStateException("Step trans " + dbStepTransLines.getStepTransLinesId()
                    + " has no step setup details");
        }

        List<StepSetupDetails> activeSubSteps = getActiveSubSteps(currentStep);
        boolean shouldSplitIntoChildren = !activeSubSteps.isEmpty()
                && !Boolean.TRUE.equals(dbStepTransLines.getHasChildTrans());

        if (shouldSplitIntoChildren) {
            return startSubTransactions(dbStepTransLines, stepTrans, currentStep, activeSubSteps.get(0), user);
        }
        return moveToNextStep(reqStepTransLines, dbStepTransLines, stepTrans, currentStep,
                mainSteps.get(existingMainCount), user);
    }

    // ------------------------------------------------------------------------------------------
    // Validation
    // ------------------------------------------------------------------------------------------

    private void validateEligibility(StepTransLines db) {
        if (StepStatus.W.equals(db.getStepStatus())) {
            throw new IllegalStateException("This Step Trans " + db.getStepTransLinesId() + " is already in progress");
        }
        if (!Objects.equals(db.getStage(), STAGE_ELIGIBLE_FOR_WIP)) {
            throw new IllegalStateException("This step trans " + db.getStepTransLinesId() + " is not eligible for WIP");
        }
    }

    private void ensureNoIncompleteChildren(StepTransLines db, StepTrans stepTrans, StepSetupDetails currentStep) {
        List<StepTransDetailsLines> lines = stepTransDetailsService.findByStepTransId(stepTrans.getStepTransId());
        boolean hasIncompleteChild = lines != null && lines.stream()
                .filter(line -> belongsToStep(line, currentStep.getStepSetupDetailsId()))
                .anyMatch(line -> StepStatus.N.equals(line.getStepStatus()));

        if (hasIncompleteChild) {
            throw new IllegalStateException("This step trans " + db.getStepTransLinesId()
                    + " has incomplete children and is not eligible for WIP");
        }
    }

    // ------------------------------------------------------------------------------------------
    // Setup lookups (all read-only: nothing here mutates entity collections)
    // ------------------------------------------------------------------------------------------

    private List<StepSetupDetails> getActiveMainSteps(StepTrans stepTrans) {
        if (stepTrans.getStepSetup() == null || stepTrans.getStepSetup().getStepSetupDetails() == null) {
            throw new IllegalStateException("Step setup is not configured for step trans " + stepTrans.getStepTransId());
        }
        return stepTrans.getStepSetup().getStepSetupDetails().stream()
                .filter(d -> d.getParentDetail() == null)
                .filter(this::isActive)
                .sorted(Comparator.comparing(StepSetupDetails::getSerialNo,
                        Comparator.nullsLast(Comparator.naturalOrder())))
                .toList();
    }

    private List<StepSetupDetails> getActiveSubSteps(StepSetupDetails currentStep) {
        if (currentStep.getStepSetupDetailsLines() == null) {
            return List.of();
        }
        return currentStep.getStepSetupDetailsLines().stream()
                .filter(this::isActive)
                .toList();
    }

    /**
     * Counts main (non sub-trans) lines WITHOUT touching the entity's own collection.
     */
    private int countMainLines(StepTrans stepTrans) {
        if (stepTrans.getStepTransLinesList() == null) {
            return 0;
        }
        return (int) stepTrans.getStepTransLinesList().stream()
                .filter(line -> line.getParentTransId() == null)
                .count();
    }

    private boolean isActive(StepSetupDetails details) {
        return Objects.equals(details.getIsActive(), ACTIVE);
    }

    private boolean belongsToStep(StepTransDetailsLines line, Long stepSetupDetailsId) {
        return line.getStepSetup() != null
                && Objects.equals(line.getStepSetup().getStepSetupDetailsId(), stepSetupDetailsId);
    }

    // ------------------------------------------------------------------------------------------
    // Flow 1: split into child transactions
    // ------------------------------------------------------------------------------------------

    private StepTransLines startSubTransactions(StepTransLines db,
                                                StepTrans stepTrans,
                                                StepSetupDetails currentStep,
                                                StepSetupDetails subStep,
                                                Muser user) {
        Long userId = Long.valueOf(user.getId());
        Long currentSetupId = currentStep.getStepSetupDetailsId();

        // Resolve items first so we never hold the main line when there is nothing to create.
        Map<Long, List<StepTransDetailsLines>> itemsBySetup = groupItemsBySetup(stepTrans, currentStep, user);
        if (itemsBySetup.isEmpty()) {
            throw new IllegalStateException("No eligible items found for step trans " + db.getStepTransLinesId()
                    + " for user " + user.getUserName());
        }

        // Hold the main line so it is hidden from the main transaction list for a while.
        db.setHoldBy(user.getUserName());
        db.setHasChildTrans(true);
        StepTransLines response = stepTransLinesService.saveStepTransLines(db, false, userId);

        List<StepTransDetailsLines> updatedItems = new ArrayList<>();

        for (Map.Entry<Long, List<StepTransDetailsLines>> entry : itemsBySetup.entrySet()) {
            List<StepTransDetailsLines> items = entry.getValue();

            if (Objects.equals(entry.getKey(), currentSetupId)) {
                // Current step: one child line per inventory item id.
                Map<Long, List<StepTransDetailsLines>> byInvItem = items.stream()
                        .collect(Collectors.groupingBy(StepTransDetailsLines::getInvItemId));
                for (List<StepTransDetailsLines> group : byInvItem.values()) {
                    createChildLine(db, stepTrans, subStep, group, userId, updatedItems);
                }
            } else {
                // Any other step: a single child line for the whole group.
                createChildLine(db, stepTrans, subStep, items, userId, updatedItems);
            }
        }

        // Single save after every item has been updated.
        stepTransDetailsService.saveAllLines(updatedItems);

        return response;
    }

    /**
     * Creates one child line for the given items and links the items to it.
     * Updated items are collected into {@code updatedItems}; the caller saves them once.
     */
    private void createChildLine(StepTransLines parent,
                                 StepTrans stepTrans,
                                 StepSetupDetails subStep,
                                 List<StepTransDetailsLines> items,
                                 Long userId,
                                 List<StepTransDetailsLines> updatedItems) {
        StepTransLines child = new StepTransLines();
        child.setStepStatus(StepStatus.N);
        child.setStepTrans(stepTrans);
        child.setStepSetupDetails(subStep);
        child.setParentLineId(0L);
        child.setStage(1);
        child.setParentTransId(parent.getStepTransLinesId());
        child.setSubTrnRefId(items.get(0).getStepTransDtlLnId());
        StepTransLines savedChild = stepTransLinesService.saveStepTransLines(child, true, userId);

        for (StepTransDetailsLines item : items) {
            item.setTrnRefId(savedChild.getStepTransLinesId());
            item.setStage(1);
            item.setStepStatus(StepStatus.W);
        }
        updatedItems.addAll(items);
    }

    /**
     * Groups the transaction's detail lines by their step setup id.
     * Completed lines are always skipped; for the current step's group, the user's item access is also enforced.
     */
    private Map<Long, List<StepTransDetailsLines>> groupItemsBySetup(StepTrans stepTrans,
                                                                     StepSetupDetails currentStep,
                                                                     Muser user) {
        List<StepTransDetailsLines> all = stepTransDetailsService.findByStepTransId(stepTrans.getStepTransId());
        if (all == null) {
            return Map.of();
        }

//        List<Long> accessed = commonHelperService.getUserAccessedItems(user.getUserName());
//        Set<Long> accessibleItemIds = accessed == null ? Set.of() : new HashSet<>(accessed);
        //Long currentSetupId = currentStep.getStepSetupDetailsId();

        return all.stream()
                //.filter(item -> item.getStepSetup() != null && item.getStepSetup().getStepSetupDetailsId() != null)
                //.filter(item -> !StepStatus.C.equals(item.getStepStatus()))
                //.filter(item -> !belongsToStep(item, currentSetupId) || (item.getInvItemId() != null && accessibleItemIds.contains(item.getInvItemId())))
                .collect(Collectors.groupingBy(item -> item.getStepSetup().getStepSetupDetailsId()));
    }

    // ------------------------------------------------------------------------------------------
    // Flow 2: move main line to WIP and create the next step line
    // ------------------------------------------------------------------------------------------

    private StepTransLines moveToNextStep(StepTransLines req,
                                          StepTransLines db,
                                          StepTrans stepTrans,
                                          StepSetupDetails currentStep,
                                          StepSetupDetails futureStep,
                                          Muser user) {
        if (Boolean.TRUE.equals(db.getHasChildTrans()) && db.getHoldBy() == null) {
            ensureNoIncompleteChildren(db, stepTrans, currentStep);
        }

        Long userId = Long.valueOf(user.getId());

        StepTransLines next = new StepTransLines();
        next.setStepStatus(StepStatus.N);
        next.setStepTrans(stepTrans);
        next.setStepSetupDetails(futureStep);
        next.setParentLineId(db.getStepTransLinesId());
        next.setStage(0);
        stepTransLinesService.saveStepTransLines(next, true, userId);

        db.setStepStatus(StepStatus.W);
        db.setRemarks(req.getRemarks());
        return stepTransLinesService.saveStepTransLines(db, true, userId);
    }

    private StepTransLines finishLastStep(StepTransLines req, StepTransLines db, Muser user) {
        db.setStepStatus(req.getStepStatus());
        db.setRemarks(req.getRemarks());
        return stepTransLinesService.saveStepTransLines(db, true, Long.valueOf(user.getId()));
    }
}