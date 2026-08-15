package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.UserMenu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserMenuRepo extends JpaRepository<UserMenu, Integer> {

//	public UserMenu findById(Integer id);
//	public UserMenu findById(Integer id);

    public UserMenu findByMenuName(String menuName);

    public UserMenu findById(Integer id, Sort sort);

    @Query("select x.menuName from UserMenu x")
    public List<String> getUserMenuName();


}
