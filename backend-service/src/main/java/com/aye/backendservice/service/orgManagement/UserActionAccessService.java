package com.aye.backendservice.service.orgManagement;


import com.aye.entity.*;
import com.aye.entity.user.Muser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Toufiq on 11/2/2019.
 */
public interface UserActionAccessService {
    List<UserActionAccess> findAll();

    UserActionAccess findById(Long id);

    UserActionAccess findByPage(Pages pages);

    List<UserActionAccess> findByUser(Muser user);

    UserActionAccess findByPageAndUser(Pages pages, Muser user);

    UserActionAccess findByPageAndUserMenuAndUserAndOrgHierarchyAndInventoryInformations(Pages pages, UserMenu userMenu, Muser user, OrgHierarchy orgHierarchy, InventoryInformations inventoryInformations);

    UserActionAccess findByPageAndUserMenuAndUserAndOrgHierarchy(Pages pages, UserMenu userMenu, Muser user, OrgHierarchy orgHierarchy);

    void save(UserActionAccess u);

    void saveAll(List<UserActionAccess> u);

    void delete(UserActionAccess u);

    Page<UserActionAccess> findByUser(Muser user, Pageable pageable);

    public Page<UserActionAccess> finBySearch(UserActionAccessSearch userActionAccessSearch, Pageable pageable);

    List<UserActionAccess> finBySearch(UserActionAccessSearch userActionAccessSearch);

    Page<UserActionAccess> findByUserAndAppModule(Muser user, AppModule appModule, Pageable pageable);

    List<UserActionAccess> findByUserAndAppModule(Muser user, AppModule appModule);

    Page<UserActionAccess> findByUserAndAppModuleAndOrgHierarchy(Muser user, AppModule appModule, OrgHierarchy oh, Pageable pageable);

    List<OrgHierarchy> findDistinctOgrByUserAndAppModule(Muser u, AppModule am);

    Long countByPageAndUserMenuAndUserAndOrgHierarchyAndInventoryInformations(Pages pages, UserMenu userMenu, Muser user, OrgHierarchy orgHierarchy, InventoryInformations inventoryInformations);

    void deleteByUserAndAppModuleAndOrgHierarchy(Muser user, AppModule appModule, OrgHierarchy oh);

    void deleteAll(List<UserActionAccess> content);

    void processAccess(Muser lUser, AppModule am);

    void deleteByUserAndAppModule(Muser user, AppModule am);
}
