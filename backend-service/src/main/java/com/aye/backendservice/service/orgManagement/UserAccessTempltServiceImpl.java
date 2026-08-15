package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.UserAccessTempltDtlRepo;
import com.aye.backendservice.repository.orgManagement.UserAccessTempltRepo;
import com.aye.entity.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccessTempltServiceImpl implements UserAccessTempltService {
    @Autowired
    private UserAccessTempltRepo userAccessTempltRepo;
    @Autowired
    private UserAccessTempltDtlRepo userAccessTempltDtlRepo;

    @Autowired
    private OrgHierarchyService orgHierarchyService;

    @Override
    public List<UserAccessTemplt> getAllTemplet() {

        return this.userAccessTempltRepo.findAll();
    }

    @Override
    public UserAccessTemplt findByTempId(Integer id) {

        UserAccessTemplt utemp = this.userAccessTempltRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("UserAccessTemp not found with id: " + id)
        );
        return utemp;
    }

    @Override
    public UserAccessTemplt save(UserAccessTemplt userAccessTemplt) throws Exception {

        if (userAccessTemplt != null) {
            return this.userAccessTempltRepo.save(userAccessTemplt);
        } else {
            throw new Exception("userAccessTemp Req is Null");

        }

    }

    @Override
    public List<UserAccessTemltDtl> findByTempHdr(UserAccessTemplt userAccessTemplt) {

        List<UserAccessTemltDtl> outTempaletDtl = new ArrayList<>();
        UserAccessTemplt uat = this.userAccessTempltRepo.findById(userAccessTemplt.getId()).orElseThrow(
                () -> new EntityNotFoundException("UserAccessTemp not found with id " + userAccessTemplt.getId())
        );
        for (UserAccessTemltDtl uatd : uat.getUserAccessTemltDtls()) {
            outTempaletDtl.add(uatd);
        }

        return outTempaletDtl;
    }

    @Override
    public List<UserAccessTemltDtl> findByTempHdrId(Integer id) {
        UserAccessTemplt uat = this.userAccessTempltRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("UserAccessTemplt not found with id " + id)
        );
        return new ArrayList<>(uat.getUserAccessTemltDtls());
    }

    @Override
    public void saveDtlLine(UserAccessTemltDtl userAccessTemltDtl) {

        this.userAccessTempltDtlRepo.save(userAccessTemltDtl);

    }

    @Override
    public UserAccessTemltDtl findByDtlId(Integer id) {

        return this.userAccessTempltDtlRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("UserAccessTemp not found with id " + id)
        );
    }

    @Override
    public UserAccessTemplt findById(Integer id) {
        return this.userAccessTempltRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("UserAccessTemp not found with id " + id)
        );

    }

    @Override
    public String findBytmpltdtlIdAndPageName(Integer id, String pName) {
        return null;
    }

    @Override
    public UserAccessTemplt findByTempDesc(String desc) {
        return this.userAccessTempltRepo.findByTempDesc(desc);
    }

    @Override
    public UserAccessTemltDtl findByTempletDtlIdAndMenuId(Integer id, UserMenu userMenu) {
        return this.userAccessTempltDtlRepo.findByIdAndUserMenu(id, userMenu);
    }

    @Override
    public List<UserAccessTemltDtl> findByRequestGroupHeader(RequestGroupHeader r) {
        return this.userAccessTempltDtlRepo.findByRequestGroupHeader(r);
    }

    @Override
    public List<OrgHierarchy> getAllOrgHierachy() {

        return this.orgHierarchyService.findall();
    }

    @Override
    public List<UserAccessTemplt> findByIdOrderById(Integer id) {
        return this.userAccessTempltRepo.findByIdOrderById(id);
    }

    @Override
    public List<UserAccessTemltDtl> findUserAccessTempDtlByMenuId(Integer menuId) {
        return this.userAccessTempltDtlRepo.findAllByUserMenu_Id(menuId);
    }

}
