package com.aye.backendservice.service.factory;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:43
 */


import com.aye.backendservice.postgresEntity.BeforeTripOrder;
import com.aye.backendservice.postgresEntity.BeforeTripSchedule;
import com.aye.backendservice.postgresEntity.MItemCatVNew;
import com.aye.backendservice.service.ItemFamilyWiseStepDetailService;
import com.aye.backendservice.service.MItemCatVService;
import com.aye.backendservice.service.NoGenService;
import com.aye.dto.request.StepTransDetailsLinesRequest;
import com.aye.dto.request.StepTransDetailsRequest;
import com.aye.entity.schedule.XxtkgTripDelvDtlV;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.ItemFamilyWiseStepDetail;
import com.aye.entity.vehicleproject.StepTrans;
import com.aye.entity.vehicleproject.StepTransDetails;
import com.aye.entity.vehicleproject.StepTransDetailsLines;
import com.aye.mapper.StepTransDetailsLinesMapper;
import com.aye.mapper.StepTransDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepTransDetailsFactory {
    @Autowired
    private ItemFamilyWiseStepDetailService familySetupService;
    @Autowired
    private MItemCatVService mItemCatVService;
    @Autowired
    private NoGenService noGenService;
    @Autowired
    private StepTransDetailsMapper stepTransDetailsMapper;
    @Autowired
    private StepTransDetailsLinesMapper stepTransDetailsLinesMapper;

    public StepTransDetails fromBeforeTripV(BeforeTripOrder src, StepTrans stepTrans, Muser user) {
        StepTransDetails dtl = new StepTransDetails();
        dtl.setStepTrans(stepTrans);
        dtl.setCreatedBy(Long.valueOf(user.getId()));
        dtl.setStepTransDtlNo(noGenService.createTransDNo());
        dtl.setCustAccountId(src.getCustAccountId());
        dtl.setCustName(src.getPartyName());
        dtl.setOrderNumber(src.getOrderNumber());
        dtl.setOrderMode(src.getOrderMode());
        return dtl;
    }

    public StepTransDetails fromTripDtlView(XxtkgTripDelvDtlV src, StepTrans stepTrans, Muser user) {
        StepTransDetails dtl = new StepTransDetails();
        dtl.setStepTrans(stepTrans);
        dtl.setCreatedBy(Long.valueOf(user.getId()));
        dtl.setStepTransDtlNo(noGenService.createTransDNo());
        dtl.setCustAccountId(src.getCustAccountId());
        dtl.setCustName(src.getPartyName());
        dtl.setOrderNumber(src.getOrderNumber());
        dtl.setScheduleNo(src.getScheduleNumber());
        dtl.setOrderMode(src.getOrderMode());

        StepTransDetailsLines ln = new StepTransDetailsLines();
        ln.setStepTransDetails(dtl);
        ln.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        ln.setInvItemId(src.getInventoryItemId());
        ln.setOrderedItem(src.getOrderedItem());
        ln.setUom(src.getOrderQuantityUom());
        ln.setOrderedQuantity(src.getOrderedQuantity());
        ln.setCreatedBy(Long.valueOf(user.getId()));
        dtl.getStepTransDetailsLines().add(ln);
        return dtl;
    }

    public StepTransDetails fromStepDetailsReq(StepTransDetailsRequest request, Muser user) {
        var obj = stepTransDetailsMapper.dtoToEntity(request);
        obj.setStepTransDtlNo(noGenService.createTransDNo());
        obj.setCreatedBy(Long.valueOf(user.getId()));
        return obj;
    }

    public StepTransDetailsLines fromStepDetailsLineReq(StepTransDetailsLinesRequest request, Muser user) {
        var obj = stepTransDetailsLinesMapper.dtoToEntity(request);
        obj.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        obj.setCreatedBy(Long.valueOf(user.getId()));
        return obj;
    }

    public StepTransDetailsLines fromStepDetailsLineReq(StepTransDetailsLinesRequest request, StepTransDetailsLines dbObj, Muser user) {
        List<MItemCatVNew> items = this.mItemCatVService.findByInvItemId(request.getInvItemId());
        if (items != null && !items.isEmpty()) {
            var item = items.get(0);
            request.setItemGroupName(item.getFamily());
            ItemFamilyWiseStepDetail familyWiseStepDetail = this.familySetupService.findByOrgIdAndInvOrgId(item.getOrgId(), item.getOrganizationId(), item.getFamily());
            if (familyWiseStepDetail.getStepSetupDetails() == null) {
                throw new IllegalArgumentException("This Item Family" + item.getFamily() + " Setup not found");
            }
            dbObj.setStepSetup(familyWiseStepDetail.getStepSetupDetails());
        } else {
            throw new IllegalArgumentException("Items not found with id: " + request.getInvItemId());
        }
        stepTransDetailsLinesMapper.dtoToEntity(request, dbObj);
        dbObj.setUpdatedBy(Long.valueOf(user.getId()));
        return dbObj;
    }


    public StepTransDetailsLines lineFromBeforeTripV(BeforeTripOrder src, StepTransDetails parent, Muser user) {
        StepTransDetailsLines ln = new StepTransDetailsLines();
        List<MItemCatVNew> items = this.mItemCatVService
                .findByInvItemIdAndOrgIdAndInvOrgId(src.getInventoryItemId(), src.getOrgId(), src.getInvOrgId());
        if (items != null && !items.isEmpty()) {
            var item = items.get(0);
            ln.setItemGroupName(item.getFamily());
            ItemFamilyWiseStepDetail familyWiseStepDetail = this.familySetupService.findByOrgIdAndInvOrgId(item.getOrgId(), item.getOrganizationId(), item.getFamily());
            if (familyWiseStepDetail.getStepSetupDetails() == null) {
                throw new IllegalArgumentException("This Item Family" + item.getFamily() + " Setup not found");
            }
            ln.setStepSetup(familyWiseStepDetail.getStepSetupDetails());
        } else {
            throw new IllegalArgumentException("Items not found with id: " + src.getInventoryItemId());
        }
        ln.setStepTransDetails(parent);
        ln.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        ln.setInvItemId(src.getInventoryItemId());
        ln.setOrderedItem(src.getOrderedItem());
        ln.setUom(src.getOrderQuantityUom());
        ln.setOrderedQuantity(src.getOrderedQuantity());
        ln.setCreatedBy(Long.valueOf(user.getId()));
        return ln;
    }

    public StepTransDetails fromBeforeTripWDsV(BeforeTripSchedule src, StepTrans stepTrans, Muser user) {
        StepTransDetails dtl = new StepTransDetails();
        dtl.setStepTrans(stepTrans);
        dtl.setCreatedBy(Long.valueOf(user.getId()));
        dtl.setStepTransDtlNo(noGenService.createTransDNo());
        dtl.setCustAccountId(src.getCustAccountId());
        dtl.setCustName(src.getPartyName());
        dtl.setOrderNumber(src.getOrderNumber());
        dtl.setScheduleNo(src.getScheduleNumber());
        return dtl;
    }

    public StepTransDetailsLines lineFromBeforeTripWDsV(BeforeTripSchedule src, StepTransDetails parent, Muser user) {
        StepTransDetailsLines ln = new StepTransDetailsLines();
        List<MItemCatVNew> items = this.mItemCatVService
                .findByInvItemIdAndOrgIdAndInvOrgId(src.getInventoryItemId(), src.getOrgId(), src.getInvOrgId());
        if (items != null && !items.isEmpty()) {
            var item = items.get(0);
            ln.setItemGroupName(item.getFamily());
            ItemFamilyWiseStepDetail familyWiseStepDetail = this.familySetupService.findByOrgIdAndInvOrgId(item.getOrgId(), item.getOrganizationId(), item.getFamily());
            if (familyWiseStepDetail.getStepSetupDetails() == null) {
                throw new IllegalArgumentException("This Item Family" + item.getFamily() + " Setup not found");
            }
            ln.setStepSetup(familyWiseStepDetail.getStepSetupDetails());
        } else {
            throw new IllegalArgumentException("Items not found with id: " + src.getInventoryItemId());
        }
        ln.setStepTransDetails(parent);
        ln.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        ln.setInvItemId(src.getInventoryItemId());
        ln.setOrderedItem(src.getOrderedItem());
        ln.setUom(src.getOrderQuantityUom());
        ln.setOrderedQuantity(src.getOrderedQuantity());
        ln.setCreatedBy(Long.valueOf(user.getId()));
        return ln;
    }
}

