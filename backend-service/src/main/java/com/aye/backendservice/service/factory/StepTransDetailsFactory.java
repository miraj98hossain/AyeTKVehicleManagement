package com.aye.backendservice.service.factory;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:43
 */


import com.aye.backendservice.service.MItemCatVService;
import com.aye.backendservice.service.NoGenService;
import com.aye.dto.request.StepTransDetailsLinesRequest;
import com.aye.dto.request.StepTransDetailsRequest;
import com.aye.entity.order.MItemCatVNew;
import com.aye.entity.schedule.BeforeTripV;
import com.aye.entity.schedule.BeforeTripWDsV;
import com.aye.entity.schedule.XxtkgTripDelvDtlV;
import com.aye.entity.user.Muser;
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
    private MItemCatVService mItemCatVService;
    @Autowired
    private NoGenService noGenService;
    @Autowired
    private StepTransDetailsMapper stepTransDetailsMapper;
    @Autowired
    private StepTransDetailsLinesMapper stepTransDetailsLinesMapper;

    public StepTransDetails fromBeforeTripV(BeforeTripV src, StepTrans stepTrans, Muser user) {
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
        request.setItemGroupName(items.get(0).getFamily());
        stepTransDetailsLinesMapper.dtoToEntity(request, dbObj);
        dbObj.setUpdatedBy(Long.valueOf(user.getId()));
        return dbObj;
    }


    public StepTransDetailsLines lineFromBeforeTripV(BeforeTripV src, StepTransDetails parent, Muser user) {
        List<MItemCatVNew> items = this.mItemCatVService.findByInvItemId(src.getInventoryItemId());
        StepTransDetailsLines ln = new StepTransDetailsLines();
        ln.setStepTransDetails(parent);
        ln.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        ln.setInvItemId(src.getInventoryItemId());
        ln.setItemGroupName(items.get(0).getFamily());
        ln.setOrderedItem(src.getOrderedItem());
        ln.setUom(src.getOrderQuantityUom());
        ln.setOrderedQuantity(src.getOrderedQuantity());
        ln.setCreatedBy(Long.valueOf(user.getId()));
        return ln;
    }

    public StepTransDetails fromBeforeTripWDsV(BeforeTripWDsV src, StepTrans stepTrans, Muser user) {
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

    public StepTransDetailsLines lineFromBeforeTripWDsV(BeforeTripWDsV src, StepTransDetails parent, Muser user) {
        List<MItemCatVNew> items = this.mItemCatVService.findByInvItemId(src.getInventoryItemId());
        StepTransDetailsLines ln = new StepTransDetailsLines();
        ln.setStepTransDetails(parent);
        ln.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        ln.setInvItemId(src.getInventoryItemId());
        ln.setItemGroupName(items.get(0).getFamily());
        ln.setOrderedItem(src.getOrderedItem());
        ln.setUom(src.getOrderQuantityUom());
        ln.setOrderedQuantity(src.getOrderedQuantity());
        ln.setCreatedBy(Long.valueOf(user.getId()));
        return ln;
    }
}

