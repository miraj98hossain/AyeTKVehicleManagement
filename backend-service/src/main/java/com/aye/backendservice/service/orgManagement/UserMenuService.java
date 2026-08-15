package com.aye.backendservice.service.orgManagement;


import com.aye.entity.UserAccess;
import com.aye.entity.UserAccessTemltDtl;
import com.aye.entity.UserMenu;
import com.aye.entity.UserSubMenu;
import com.aye.entity.user.Muser;

import java.util.List;

/**
 * Created by Toufiq on 11/6/2019.
 */
public interface UserMenuService {
    UserMenu findByMenuId(Integer id);

    UserMenu findByMeuName(String menuName);

    //	public List<UserMenu> getUserAccess(Integer userId);
    List<UserMenu> getUserAccess(Muser user);

    List<UserMenu> getUserAccessNew(Muser user);


    List<String> getAllManuName();


    List<UserMenu> getAllMenu();

    UserMenu save(UserMenu userMenu);

    void saveline(UserSubMenu userSubMenu);

    List<UserAccess> findByUser(Muser user);

    List<UserSubMenu> findByMainMenu(UserMenu userMenu);
    //public void

    UserSubMenu findBysubmId(Integer id);

    UserSubMenu findSubMenuById(Integer submenuId);

    //    public List<UserAccessTemltDtl> getUserAccessNew1(Muser user);
    UserSubMenu findByPage(String pageName);

    UserAccessTemltDtl getUserReport(Integer temltDtId);

    List<UserSubMenu> findSubMenuByUserMenu(UserMenu menu);

}
