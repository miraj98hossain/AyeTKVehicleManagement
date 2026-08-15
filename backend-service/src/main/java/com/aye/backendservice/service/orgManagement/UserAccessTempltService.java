package com.aye.backendservice.service.orgManagement;


import com.aye.entity.*;

import java.util.List;

public interface UserAccessTempltService {

    public UserAccessTemplt findByTempId(Integer id);

    public UserAccessTemplt findByTempDesc(String desc);

    public List<UserAccessTemplt> getAllTemplet();

    public UserAccessTemplt save(UserAccessTemplt userAccessTemplt) throws Exception;


    public UserAccessTemplt findById(Integer id);

    String findBytmpltdtlIdAndPageName(Integer id, String pName);

    //public List<UserSubMenu> findByMainMenu(UserMenu userMenu);

    public List<UserAccessTemltDtl> findByTempHdr(UserAccessTemplt userAccessTemplt);

    List<UserAccessTemltDtl> findByTempHdrId(Integer id);

    public void saveDtlLine(UserAccessTemltDtl userAccessTemltDtl);


    public UserAccessTemltDtl findByDtlId(Integer id);


    public List<OrgHierarchy> getAllOrgHierachy(); // Thsi Is for Org hierarchy LOV


    UserAccessTemltDtl findByTempletDtlIdAndMenuId(Integer id, UserMenu userMenu);

    List<UserAccessTemltDtl> findByRequestGroupHeader(RequestGroupHeader r);


    List<UserAccessTemplt> findByIdOrderById(Integer id);

    List<UserAccessTemltDtl> findUserAccessTempDtlByMenuId(Integer menuId);
}
