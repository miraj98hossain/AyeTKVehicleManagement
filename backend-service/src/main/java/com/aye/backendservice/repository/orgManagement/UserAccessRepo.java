package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.UserAccess;
import com.aye.entity.UserAccessTemplt;
import com.aye.entity.user.Muser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserAccessRepo extends JpaRepository<UserAccess, Integer>, JpaSpecificationExecutor<UserAccess> {

    public List<UserAccess> findByUser(Muser user);

    public List<UserAccess> findByUserOrderById(Muser user);
//	public List<UserAccess> findByUserAndStart_dateLessThanEqualAndEnd_dateGreaterThanEqualOrderById(User user,Date d1,Date d2);
//	public List<UserAccess> findByUserAndUserAccessTempltUserAccessTemltDtlsOrgHierarchy(User user,OrgHierarchy isActive);

    public List<UserAccess> findByUserId(Integer id);

    UserAccess findByUserAccessTempltAndUser(UserAccessTemplt userAccessTemplt, Muser user);
//	public UserAccess findById (Integer id);


}
