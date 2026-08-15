package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.concurrency.RequestGroupLinesRepo;
import com.aye.backendservice.repository.orgManagement.*;
import com.aye.entity.*;
import com.aye.entity.user.Muser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class UserMenuServiceImpl implements UserMenuService {

    @Autowired
    private UserMenuRepo userMenuRepo;

    @Autowired
    private UserSubMenuRepo userSubMenuRepo;

    @Autowired
    private UserAccessRepo userAccessRepo;

    @Autowired
    private UserAccessTempltRepo userAccessTempltRepo;

    @Autowired
    private UserAccessTempltDtlRepo userAccessTempltDtlRepo;

    @Autowired
    private RequestGroupLinesRepo requestGroupLinesRepo;


    @Override
    public UserMenu findByMenuId(Integer id) {

        UserMenu um = this.userMenuRepo.findById(id).orElse(null);
        return um;
    }

    @Override
    public List<UserMenu> getUserAccess(Muser user) {


        List<UserMenu> outMenu = new ArrayList<>();


        List<UserAccess> access = this.userAccessRepo.findByUserOrderById(user);

        int i = 0;

        for (UserAccess usacc : access) {

            List<UserAccessTemplt> accessTemp = this.userAccessTempltRepo.findByIdOrderById(usacc.getUserAccessTemplt().getId());

            for (UserAccessTemplt uact : accessTemp) {

                uact.getUserAccessTemltDtls().sort(Comparator.comparing(UserAccessTemltDtl::getId));


                for (UserAccessTemltDtl tmpdtl : uact.getUserAccessTemltDtls()) {

                    i++;
                    UserMenu userm = new UserMenu();
                    userm = this.userMenuRepo.findById(tmpdtl.getUserMenu().getId()).orElseThrow(
                            () -> new EntityNotFoundException("UserMenu Not Found")
                    );
                    userm.setMenuName(tmpdtl.getDetailName());
                    outMenu.add(userm);
                }
            }
        }


        return outMenu;
    }

    @Override
    public List<UserMenu> getUserAccessNew(Muser user) {
        List<UserMenu> userMenus = new ArrayList<>();
        return userMenus;
    }

    @Override
    public List<UserAccess> findByUser(Muser user) {
        List<UserAccess> usrAcc = this.userAccessRepo.findByUserOrderById(user);
        return usrAcc;
    }

    @Override
    public List<UserMenu> getAllMenu() {

        return this.userMenuRepo.findAll();
    }

    @Override
    public UserMenu save(UserMenu userMenu) {

        if (userMenu != null) {
            return this.userMenuRepo.save(userMenu);
        } else {

            throw new RuntimeException("UserMenu Request is null");
        }

    }

    @Override
    public void saveline(UserSubMenu userSubMenu) {
        this.userSubMenuRepo.save(userSubMenu);

    }

    @Override
    public List<UserSubMenu> findByMainMenu(UserMenu userMenu) {
        List<UserSubMenu> outSmenu = new ArrayList<>();
        UserMenu um = this.userMenuRepo.findById(userMenu.getId()).orElseThrow(
                () -> new EntityNotFoundException("UserMenu Not Found")
        );
        for (UserSubMenu usm : um.getSubMenus()) {
            outSmenu.add(usm);

        }
        return outSmenu;
    }

    @Override
    public UserSubMenu findBysubmId(Integer id) {
        return this.userSubMenuRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("UserSubMenu not found")
        );
    }

    @Override
    public List<String> getAllManuName() {
        return this.userMenuRepo.getUserMenuName();
    }

    @Override
    public UserSubMenu findSubMenuById(Integer submenuId) {
        return this.userSubMenuRepo.findById(submenuId).orElseThrow(
                () -> new EntityNotFoundException("UserSubMenu not found")
        );
    }

    @Override
    public UserMenu findByMeuName(String menuName) {
        return this.userMenuRepo.findByMenuName(menuName);
    }

    @Override
    public UserSubMenu findByPage(String pageName) {
        return this.userSubMenuRepo.findByPageName(pageName);
    }

    @Override
    public UserAccessTemltDtl getUserReport(Integer temltDtId) {
        UserAccessTemltDtl outReportMenu = this.userAccessTempltDtlRepo.findById(temltDtId).orElseThrow(
                () -> new EntityNotFoundException("UserAccessTempDtl not found")
        );

        outReportMenu.getRequestGroupHeader().setRequestGroupLines(this.requestGroupLinesRepo.findByRequestGroupHeader(outReportMenu.getRequestGroupHeader()));

        return outReportMenu;
    }

    @Override
    public List<UserSubMenu> findSubMenuByUserMenu(UserMenu menu) {
        return this.userSubMenuRepo.findByUserMenu(menu);
    }


}
