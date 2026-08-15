package com.aye.backendservice.repository.orgManagement;

import com.aye.entity.*;
import com.aye.entity.user.Muser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserActionAccessRepo extends JpaRepository<UserActionAccess, Long>, JpaSpecificationExecutor<UserActionAccess> {
    UserActionAccess findByPages(Pages pages);

    List<UserActionAccess> findByUser(Muser user);

    Page<UserActionAccess> findByUserAndUserMenu_appModule(Muser user, AppModule appModule, Pageable p);

    List<UserActionAccess> findByUserAndUserMenu_appModule(Muser user, AppModule appModule);

    Page<UserActionAccess> findByUserAndUserMenu_appModuleAndOrgHierarchy(Muser user, AppModule appModule, OrgHierarchy oh, Pageable p);

    List<UserActionAccess> findByUserAndUserMenuAppModuleAndOrgHierarchy(Muser user, AppModule appModule, OrgHierarchy oh);

    UserActionAccess findByPagesAndUser(Pages pages, Muser user);

    UserActionAccess findByPagesAndUserMenuAndUser(Pages pages, UserMenu userMenu, Muser user);

    Page<UserActionAccess> findByUser(Muser user, Pageable p);

    UserActionAccess findByPagesAndUserMenuAndUserAndOrgHierarchyAndInventoryInformations
            (Pages pages, UserMenu userMenu, Muser user, OrgHierarchy orgHierarchy, InventoryInformations inventoryInformations);

    Long countByPagesAndUserMenuAndUserAndOrgHierarchyAndInventoryInformations
            (Pages pages, UserMenu userMenu, Muser user, OrgHierarchy orgHierarchy, InventoryInformations inventoryInformations);

    UserActionAccess findByPagesAndUserMenuAndUserAndOrgHierarchy(Pages pages, UserMenu userMenu, Muser user, OrgHierarchy orgHierarchy);

    Page<UserActionAccess> findByUserAndOrgHierarchyAndInventoryInformationsAndPages(Muser user, OrgHierarchy orgHierarchy,
                                                                                     InventoryInformations inventoryInformations, Pages pages, Pageable pageable);

    @Query("select x from UserActionAccess x where x.user.id = :userId and " +
            " (:orgHierarchy is null or  x.orgHierarchy.id= :orgHierarchy) " +
            " and ( :inventoryInformations is null or x.inventoryInformations.id = :inventoryInformations ) " +
            "and (:pages is null or x.pages.id = :pages )")
    List<UserActionAccess> findBySearch(@Param("userId") Integer userId, @Param("orgHierarchy") Long orgHierarchy,
                                        @Param("inventoryInformations") Long inventoryInformations, @Param("pages") Long pages);


    List<UserActionAccess.UserActionAccessInterfaceLong> findDistinctOrgHierarchyIdByUserAndUserMenu_appModule(Muser user, AppModule appModule);


    @Query(value = "select distinct useraction0_.ORG_ID as id from AYE.USER_ACTION_ACCESS useraction0_ left outer join AYE.menu_header usermenu1_ on useraction0_.MENU_ID=usermenu1_.id where useraction0_.USER_ID=:userId and usermenu1_.APPLICATION_MODULE_ID=:appModuleId", nativeQuery = true)
    List<UserActionAccess.UserActionAccessInterfaceLong> getDistinctOrgIdByUserAppModule(@Param("userId") Integer userId, @Param("appModuleId") Long appModuleId);

    List<UserActionAccess.UserActionAccessInterface> findDistinctOrgHierarchyByUserAndUserMenuAppModule(Muser u, AppModule am);

}