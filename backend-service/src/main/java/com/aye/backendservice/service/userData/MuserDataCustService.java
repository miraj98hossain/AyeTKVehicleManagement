package com.aye.backendservice.service.userData;


import com.aye.entity.user.CustProfile;
import com.aye.entity.user.userData.MuserDataCust;
import com.aye.entity.user.userData.MuserDataCustM;

import java.util.List;

//import org.springframework.data.repository.query.Param;

public interface MuserDataCustService {
    public List<MuserDataCust> getAllCustomer();

    public List<MuserDataCust> getAllCustomerByOrg(Long orgId);

    public List<MuserDataCustM> findByAutonumberName(String autoNumber, String CustName);

    //	public List<MuserDataCustM> findByAutonumberOrgName(String autoNumber, Long orgId, String CustName);
    public List<MuserDataCustM> findByAutonumberOrgName(String autoNumber, Long orgId, String CustName);

    public MuserDataCust getById(Integer id);

    public Integer getCustExist(Integer custAcctId, String AutoNumber, Long orgId);

    public Integer getShipto(Integer custAcctId, String AutoNumber, Long orgId);

    public Integer getBillto(Integer custAcctId, String AutoNumber, Long orgId);

    public List<MuserDataCustM> getByAutoNumber(String AutoNumber);

    public List<MuserDataCust> searchMuserDataCust(MuserDataCust s);

    public List<CustProfile> searchCustProfile(CustProfile s);


}
