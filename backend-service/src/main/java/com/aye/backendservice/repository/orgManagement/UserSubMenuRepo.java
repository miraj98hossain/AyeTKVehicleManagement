package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.UserMenu;
import com.aye.entity.UserSubMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubMenuRepo extends JpaRepository<UserSubMenu, Integer> {

    UserSubMenu findByPageName(String pageName);


    List<UserSubMenu> findByUserMenu(UserMenu menu);


}
