package com.aye.backendservice.repository.orgManagement;

import com.aye.entity.RequestGroupHeader;
import com.aye.entity.UserAccessTemltDtl;
import com.aye.entity.UserMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccessTempltDtlRepo extends JpaRepository<UserAccessTemltDtl, Integer> {

//	public UserAccessTemltDtl findById (Integer id);

    //public UserAccessTemltDtl findByUserMenuId(Integer id);
    UserAccessTemltDtl findByIdAndUserMenu(Integer id, UserMenu userMenu);

    List<UserAccessTemltDtl> findByRequestGroupHeader(RequestGroupHeader r);

    List<UserAccessTemltDtl> findAllByUserMenu_Id(Integer userMenuId);
}
