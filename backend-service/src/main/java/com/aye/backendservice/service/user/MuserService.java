package com.aye.backendservice.service.user;


//import org.springframework.data.repository.query.Param;


import com.aye.dto.request.UserSearchRequest;
import com.aye.entity.projection.MuserDto;
import com.aye.entity.user.Mrole;
import com.aye.entity.user.Muser;

import java.util.List;

//import com.aye.RestfulServer.exception.NotValidAutoNumber;

public interface MuserService {
    List<Muser> findAll();

    List<MuserDto> findAllUser();

    List<Muser> findAllUsers();

    // @Transactional
//    Muser findByUserNameLogin(String userName) throws UserPrincipalNotFoundException;

    Muser findByUserName(String userName);


    void updateUserAutoNumber(Integer userId);

    Integer findUserByAutoNumber(String autoNumber) throws NullPointerException;

    Integer checkUserExist(String autoNumber);

    String findAutoNumberByUserName(String userName);

    Muser findUserModelByAutoNumber(String autoNumber);


    Muser findByAutoNumber(String autoNumber);

    Muser findById(Integer id);

    void PassWordChange(Integer Usid, String nPass);

    List<Muser> findByUserNameLike(String s);

    List<Muser> filterUsers(UserSearchRequest userSearchRequest);

    void updateUser(Muser muser);

    Mrole findRoleByName(String name);

    Mrole findRoleById(Integer id);

    List<Mrole> findAllRoles();

}
