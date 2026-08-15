package com.aye.backendservice.service.userData;

//import java.util.List;


import com.aye.backendservice.repository.userData.MuserDataCommonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.aye.RestfulServer.model.userData.MuserDataCommon;

@Service
public class MuserDataCommonServiceImpl implements MuserDataCommonService {

    @Autowired
    private MuserDataCommonRepo muserDataCommonRepo;

    public MuserDataCommonServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Integer getOrg(String AutoNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getInvOrgId(String AutoNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getOrderType(String AutoNumber) {

        return this.muserDataCommonRepo.findByAutoNumber(AutoNumber);
    }

}
