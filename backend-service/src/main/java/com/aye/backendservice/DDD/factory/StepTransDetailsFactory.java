package com.aye.backendservice.DDD.factory;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:43
 */


import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.om.BeforeTripV;
import com.aye.RestfulServer.model.om.BeforeTripWDsV;
import com.aye.backendservice.entity.StepTrans;
import com.aye.backendservice.entity.StepTransDetails;
import com.aye.backendservice.entity.StepTransDetailsLines;
import com.aye.backendservice.mapper.StepTransDetailsMapper;
import com.aye.backendservice.service.NoGenService;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StepTransDetailsFactory {
    @Autowired
    private NoGenService noGenService;
    @Autowired
    private StepTransDetailsMapper stepTransDetailsMapper;

    public StepTransDetails fromBeforeTripV(BeforeTripV src, StepTrans stepTrans, Muser user) {
        StepTransDetails dtl = new StepTransDetails();
        dtl.setStepTrans(stepTrans);
        dtl.setCreatedBy(Long.valueOf(user.getId()));
        dtl.setStepTransDtlNo(noGenService.createTransDNo());
        dtl.setCustAccountId(src.getCustAccountId());
        dtl.setCustName(src.getPartyName());
        dtl.setOrderNumber(src.getOrderNumber());
        return dtl;
    }

    public StepTransDetails fromStepDetailsReq(StepTransDetailsRequest request, Muser user) {
        var obj = stepTransDetailsMapper.dtoToEntity(request);
        obj.setCreatedBy(Long.valueOf(user.getId()));
        return obj;
    }

    public StepTransDetailsLines lineFromBeforeTripV(BeforeTripV src, StepTransDetails parent, Muser user) {
        StepTransDetailsLines ln = new StepTransDetailsLines();
        ln.setStepTransDetails(parent);
        ln.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        ln.setInvItemId(src.getInventoryItemId());
        ln.setOrderedItem(src.getOrderedItem());
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
        StepTransDetailsLines ln = new StepTransDetailsLines();
        ln.setStepTransDetails(parent);
        ln.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        ln.setInvItemId(src.getInventoryItemId());
        ln.setOrderedItem(src.getOrderedItem());
        ln.setOrderedQuantity(src.getOrderedQuantity());
        ln.setCreatedBy(Long.valueOf(user.getId()));
        return ln;
    }
}

