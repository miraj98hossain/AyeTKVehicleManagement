package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.UserActionAccessRepo;
import com.aye.entity.*;
import com.aye.entity.user.Muser;
import com.aye.enums.MenuLevel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Toufiq on 11/2/2019.
 */
@Service
class UserActionAccessServiceImpl implements UserActionAccessService {

    @Autowired
    private UserActionAccessRepo userActionAccessRepo;

    @Autowired
    private UserAccessService userAccessService;
    @Autowired
    private UserAccessInvOrgService userAccessInvOrgService;

    @Autowired
    private PageService pageService;
    @Autowired
    private CommonColumnServiceImpl commonColumnService;

    @Override
    public List<UserActionAccess> findAll() {
        return this.userActionAccessRepo.findAll();
    }

    @Override
    public UserActionAccess findById(Long id) {
        return this.userActionAccessRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("UserActionAccess with id " + id + " not found!")
        );
    }

    @Override
    public UserActionAccess findByPage(Pages pages) {
        return this.userActionAccessRepo.findByPages(pages);
    }

    @Override
    public List<UserActionAccess> findByUser(Muser user) {
        return this.userActionAccessRepo.findByUser(user);
    }

    @Override
    public UserActionAccess findByPageAndUser(Pages pages, Muser user) {
        return this.userActionAccessRepo.findByPagesAndUser(pages, user);
    }

    @Override
    public UserActionAccess findByPageAndUserMenuAndUserAndOrgHierarchyAndInventoryInformations
            (Pages pages, UserMenu userMenu, Muser user, OrgHierarchy orgHierarchy, InventoryInformations inventoryInformations) {
//        System.out.println("pages >"+pages);
//        System.out.println("userMenu >"+userMenu);
//        System.out.println("userMenu >"+userMenu);
//        System.out.println("user >"+user);
//        System.out.println("orgHierarchy >"+orgHierarchy);
//        System.out.println("inventoryInformations >"+inventoryInformations);
        return this.userActionAccessRepo.findByPagesAndUserMenuAndUserAndOrgHierarchyAndInventoryInformations(pages, userMenu, user, orgHierarchy, inventoryInformations);
    }

    @Override
    public UserActionAccess findByPageAndUserMenuAndUserAndOrgHierarchy(Pages pages, UserMenu userMenu, Muser user, OrgHierarchy orgHierarchy) {

        return this.userActionAccessRepo.findByPagesAndUserMenuAndUserAndOrgHierarchy(pages, userMenu, user, orgHierarchy);
    }

    @Override
    public void save(UserActionAccess u) {
        this.userActionAccessRepo.save(u);
    }

    @Override
    public void saveAll(List<UserActionAccess> u) {
        this.userActionAccessRepo.saveAll(u);
    }

    @Override
    public void delete(UserActionAccess u) {
        this.userActionAccessRepo.delete(u);
    }

    @Override
    public Page<UserActionAccess> findByUser(Muser user, Pageable pageable) {
        return this.userActionAccessRepo.findByUser(user, pageable);
    }

    @Override
    public Page<UserActionAccess> finBySearch(UserActionAccessSearch userActionAccessSearch, Pageable pageable) {
//         List<UserActionAccess> u= this.userActionAccessRepo.findBySearch(userActionAccessSearch.getUser().getId(),userActionAccessSearch.getOrgHierarchy().getId(),
//                userActionAccessSearch.getLspInformations().getId(),userActionAccessSearch.getPages().getId());
//        Page<UserActionAccess> p = new PageImpl<UserActionAccess>(u);
        return this.userActionAccessRepo.findByUserAndOrgHierarchyAndInventoryInformationsAndPages(userActionAccessSearch.getUser(),
                userActionAccessSearch.getOrgHierarchy(),
                userActionAccessSearch.getInvOrgInformations(),
                userActionAccessSearch.getPages(), pageable);
    }

    @Override
    public List<UserActionAccess> finBySearch(UserActionAccessSearch u) {
        Long isp = null, lsp = null, page = null;
        Integer uid = null;
        if (u.getOrgHierarchy() != null) {
            isp = u.getOrgHierarchy().getId();
        }
        if (u.getInvOrgInformations() != null) {
            lsp = u.getInvOrgInformations().getId();
        }
        if (u.getPages() != null) {
            lsp = u.getPages().getId();
        }
        uid = u.getUser().getId();
        return this.userActionAccessRepo.findBySearch(uid, isp, lsp, page);
    }

    @Override
    public Page<UserActionAccess> findByUserAndAppModule(Muser user, AppModule appModule, Pageable pageable) {
        return this.userActionAccessRepo.findByUserAndUserMenu_appModule(user, appModule, pageable);
    }

    @Override
    public List<UserActionAccess> findByUserAndAppModule(Muser user, AppModule appModule) {
        return this.userActionAccessRepo.findByUserAndUserMenu_appModule(user, appModule);
    }

    @Override
    public Page<UserActionAccess> findByUserAndAppModuleAndOrgHierarchy(Muser user, AppModule appModule, OrgHierarchy oh, Pageable pageable) {
        return this.userActionAccessRepo.findByUserAndUserMenu_appModuleAndOrgHierarchy(user, appModule, oh, pageable);
    }

    @Override
    public List<OrgHierarchy> findDistinctOgrByUserAndAppModule(Muser u, AppModule am) {
//        List<OrgHierarchy> orgHierarchies = new ArrayList<>();
////        List<UserActionAccess.UserActionAccessInterfaceLong> longList=this.userActionAccessRepo.findDistinctOrgHierarchyIdByUserAndUserMenu_appModule(u, am);
//        List<UserActionAccess.UserActionAccessInterfaceLong> longList = this.userActionAccessRepo.getDistinctOrgIdByUserAppModule(u.getId(), am.getId());
//        longList.forEach(x ->
//        {
//            orgHierarchies.add(this.orgHierarchyService.findById(x.getId()));
//        });
        List<UserActionAccess.UserActionAccessInterface> actionAccessInterfaces = this.userActionAccessRepo.findDistinctOrgHierarchyByUserAndUserMenuAppModule(u, am);
//        return this.userActionAccessRepo.findDistinctOrgHierarchyIdByUserAndUserMenu_appModeul(u, am);
        //        return orgHierarchies;
        return actionAccessInterfaces.stream().map(UserActionAccess.UserActionAccessInterface::getOrgHierarchy).collect(Collectors.toList());
    }

    @Override
    public Long countByPageAndUserMenuAndUserAndOrgHierarchyAndInventoryInformations(Pages pages, UserMenu userMenu, Muser user, OrgHierarchy orgHierarchy, InventoryInformations inventoryInformations) {
        return this.userActionAccessRepo.countByPagesAndUserMenuAndUserAndOrgHierarchyAndInventoryInformations(pages, userMenu, user, orgHierarchy, inventoryInformations);
    }


    @Override
    public void deleteByUserAndAppModuleAndOrgHierarchy(Muser user, AppModule appModule, OrgHierarchy oh) {
        List<UserActionAccess> userActionAccesses = this.userActionAccessRepo.findByUserAndUserMenuAppModuleAndOrgHierarchy(user, appModule, oh);
        if ((userActionAccesses != null) && (!userActionAccesses.isEmpty())) {
            deleteAll(userActionAccesses);
        }
    }

    @Override
    public void deleteAll(List<UserActionAccess> content) {
        this.userActionAccessRepo.deleteAll(content);
    }


    @Override
    public void processAccess(Muser lUser, AppModule am) {
        LocalDateTime start = LocalDateTime.now();
        CommonColumn cc = setCommonColumn(new CommonColumn(), "E", lUser.getUserName());

        List<UserAccess> userAccesses = userAccessService.findActiveAccessByUsAndAppModule(lUser, am);
        List<UserActionAccess> userActionAccessList = this.userActionAccessRepo.findByUserAndUserMenu_appModule(lUser, am);
        userAccesses.forEach(ua -> {
            ua.getUserAccessTemplt().getUserAccessTemltDtls().stream()
                    .sorted(Comparator.comparing(uatd -> uatd.getUserMenu().getId()))
                    .filter(f -> f.getUserMenu().getAppModule().getId().equals(am.getId()))
                    .forEach(uatd -> processAccessDetail(uatd.getOrgHierarchy(), lUser, uatd, userActionAccessList, cc));
        });

    }

    private void processAccessDetail(OrgHierarchy oh, Muser lUser, UserAccessTemltDtl uatd, List<UserActionAccess> userActionAccessList, CommonColumn cc) {
//        System.out.println(LocalDateTime.now()+ "  ->>> "+uatd.getUserAccessTemplt().getTempDesc());
        OrgHierarchy ohInternal = oh;
        if (oh == null) {
            if (uatd.getOrgHierarchy() != null && uatd.getOrgHierarchy().getId() != null) {

                ohInternal = uatd.getOrgHierarchy();
            }
        }
        if (!uatd.getUserMenu().getLevel().equals(MenuLevel.SYS)) {
            List<UserAccessInvOrg> userAccessInvOrgs = findUserAccessInvOrgByUserAccessTemltDtlAndOrg(uatd, ohInternal);

            if (userAccessInvOrgs != null && !userAccessInvOrgs.isEmpty()) {

                userAccessInvOrgs.forEach(org ->
                        uatd.getUserMenu().getSubMenus().stream()
                                .filter(UserSubMenu::getIsActive)
                                .forEach(userSubMenu -> setInvOrgAccess(org.getInvOrgs(), lUser, userSubMenu, "INVORG", userActionAccessList, cc))
                );
            } else if (uatd.getOrgHierarchy() != null) {
//                System.out.println(LocalDateTime.now() +" org");
                uatd.getUserMenu().getSubMenus().stream()
                        .filter(UserSubMenu::getIsActive)
                        .forEach(userSubMenu -> setOrgAccess(uatd.getOrgHierarchy(), lUser, userSubMenu, "ORG", userActionAccessList, cc));
            } else {
//                System.out.println(LocalDateTime.now()+ "  oth ");
                uatd.getUserMenu().getSubMenus().stream()
                        .filter(UserSubMenu::getIsActive)
                        .forEach(userSubMenu -> setOtherAccess(lUser, userSubMenu, "BLANK", userActionAccessList, cc));
            }
        }
    }


    @Override
    public void deleteByUserAndAppModule(Muser user, AppModule am) {
        List<UserActionAccess> userActionAccesses = findByUserAndAppModule(user, am);
        deleteAll(userActionAccesses);
    }

    protected void setInvOrgAccess(InventoryInformations inventoryInformations, Muser user, UserSubMenu userSubMenu, String ex, List<UserActionAccess> userActionAccessList, CommonColumn cc) {

        List<Pages> pagesList = this.pageService.findByPageGroup(userSubMenu.getPage().getPageGroup());


        pagesList.removeIf(r -> userActionAccessList.stream().filter(ua -> ua.getPages().getId().equals(r.getId())
                && ua.getUserMenu().getId().equals(userSubMenu.getUserMenu().getId()) && ua.getUser().getId().equals(user.getId())
                && (ua.getInventoryInformations() != null && ua.getInventoryInformations().getId() != null
                && ua.getInventoryInformations().getId().equals(inventoryInformations.getId())) &&
                ua.getOrgHierarchy().getId().equals(inventoryInformations.getOrgHierarchy().getId())).count() > 0);


        List<UserActionAccess> userActionAccesses = pagesList.stream().map(
                pages ->
                {
//                    System.out.println(inventoryInformations.getCode()+" <<<<< "+userSubMenu.getMenuName()+"  "+pages.getPageName()+" "+pages.getPageGroup()+" "+pages.getId());
                    UserActionAccess userActionAccess = new UserActionAccess();
                    userActionAccess.setInventoryInformations(inventoryInformations);
                    userActionAccess.setOrgHierarchy(inventoryInformations.getOrgHierarchy());
                    userActionAccess.setUserMenu(userSubMenu.getUserMenu());
                    userActionAccess.setPages(pages);
                    userActionAccess.setUser(user);
                    userActionAccess.setColumn(cc);
                    userActionAccess.setEntryFrom(ex);
                    return userActionAccess;
//                    save(userActionAccess);

//                    }
//                    System.out.println(pages);

                }
        ).collect(Collectors.toList());
//        System.out.println(LocalDateTime.now()+ "  inv1.1 "+ userActionAccesses.size());
        saveAll(userActionAccesses);
//        System.out.println(LocalDateTime.now()+ "  inv1");
    }

    protected void setOrgAccess(OrgHierarchy orgHierarchy, Muser user, UserSubMenu userSubMenu, String ex, List<UserActionAccess> userActionAccessList, CommonColumn cc) {
//        CommonColumn cc = new CommonColumn();
//        setCommonColumn(cc, "E");
//        System.out.println(LocalDateTime.now()+ "  org1");

        List<Pages> pagesList = this.pageService.findByPageGroup(userSubMenu.getPage().getPageGroup());


//        pagesList.removeIf(p -> findByPageAndUserMenuAndUserAndOrgHierarchyAndInventoryInformations(
//                p, userSubMenu.getUserMenu(), user, orgHierarchy, null) != null);

//        List<UserActionAccess> userActionAccessList=this.userActionAccessRepo.findByUserAndPagesPageGroup(user,userSubMenu.getPage().getPageGroup());

        pagesList.removeIf(r -> userActionAccessList.stream().filter(ua -> ua.getPages().getId().equals(r.getId())
                && ua.getUserMenu().getId().equals(userSubMenu.getUserMenu().getId()) && ua.getUser().getId().equals(user.getId()) &&
                ua.getOrgHierarchy().getId().equals(orgHierarchy.getId()) && ua.getInventoryInformations() == null).count() > 0);
//        this.pageService.findByPageGroup(userSubMenu.getPage().getPageGroup()).forEach(
//        System.out.println(LocalDateTime.now()+ "  org2");
        List<UserActionAccess> userActionAccesses = pagesList.stream().map(
                pages ->
                {

                    UserActionAccess userActionAccess = new UserActionAccess();
                    userActionAccess.setOrgHierarchy(orgHierarchy);
                    userActionAccess.setUserMenu(userSubMenu.getUserMenu());
//                    userActionAccess.setPages(this.pageService.findById(pages.getId()));
                    userActionAccess.setPages(pages);
                    userActionAccess.setUser(user);
                    userActionAccess.setColumn(cc);
                    userActionAccess.setEntryFrom(ex);
                    return userActionAccess;
//                    this.userActionAccessService.save(userActionAccess);
//                    }
                }
        ).collect(Collectors.toList());

        saveAll(userActionAccesses);
    }

    protected void setOtherAccess(Muser user, UserSubMenu userSubMenu, String ex, List<UserActionAccess> userActionAccessList, CommonColumn cc) {


        List<Pages> pagesList = this.pageService.findByPageGroup(userSubMenu.getPage().getPageGroup());


        pagesList.removeIf(r -> userActionAccessList.stream().filter(ua -> ua.getPages().getId().equals(r.getId())
                && ua.getUserMenu().getId().equals(userSubMenu.getUserMenu().getId()) && ua.getUser().getId().equals(user.getId()) &&
                ua.getOrgHierarchy() == null && ua.getInventoryInformations() == null).count() > 0);


        pagesList.removeIf(p -> findByPageAndUserMenuAndUserAndOrgHierarchyAndInventoryInformations(
                p, userSubMenu.getUserMenu(), user, null, null) != null);

        List<UserActionAccess> userActionAccesses = pagesList.stream().map(
                pages ->
                {
//                    if (this.userActionAccessService.findByPageAndUserMenuAndUserAndOrgHierarchyAndInventoryInformations(
//                            pages, userSubMenu.getUserMenu(), user, null, null) == null) {
                    UserActionAccess userActionAccess = new UserActionAccess();
                    userActionAccess.setPages(pages);
                    userActionAccess.setUserMenu(userSubMenu.getUserMenu());
                    userActionAccess.setUser(user);
                    userActionAccess.setColumn(cc);
                    userActionAccess.setEntryFrom(ex);
                    return userActionAccess;
//                    this.userActionAccessService.save(userActionAccess);
//                    }
                }
        ).collect(Collectors.toList());

        if (!userActionAccesses.isEmpty()) {
            saveAll(userActionAccesses);
        }
    }

    private List<UserAccessInvOrg> findUserAccessInvOrgByUserAccessTemltDtl(UserAccessTemltDtl uatd) {
        return this.userAccessInvOrgService.findByUserAccessTemltDtl(uatd);

    }

    private List<UserAccessInvOrg> findUserAccessInvOrgByUserAccessTemltDtlAndOrg(UserAccessTemltDtl uatd, OrgHierarchy oh) {
        return this.userAccessInvOrgService.findByUserAccessTemltDtlAndOrg(uatd, oh);

    }

    public CommonColumn setCommonColumn(CommonColumn commonColumn, String mode, String userName) {
        return this.commonColumnService.setCommonColumn(commonColumn, mode, userName);
    }

}
