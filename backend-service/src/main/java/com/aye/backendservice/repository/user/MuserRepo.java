package com.aye.backendservice.repository.user;


import com.aye.entity.projection.MuserDto;
import com.aye.entity.user.Muser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MuserRepo extends JpaRepository<Muser, Integer>, JpaSpecificationExecutor<Muser> {

//    @Query(value="select a.user_name from xxtkg.m_user a,xxtkg.m_role where a.user_id=b.user_id and a.user_name=:userName",nativeQuery = true)

    //	Muser findById(Integer id);
    @Query("select distinct x.id as id,  x.userName as userName , x.password as password, x.name as name, x.lastName as lastName, x.accountExpired as accountExpired, " +
            " x.accountLocked as accountLocked,  x.credentialsExpired as credentialsExpired, x.enabled as enabled, " +
            " x.autoNumber as autoNumber , x.userType as userType , x.isPassChange as isPassChange,  x.chkDeviceId as chkDeviceId" +
            " from Muser x order by x.id desc ")
    List<MuserDto> findDistinctUser();

    List<Muser> findByUserNameIgnoreCaseContaining(String s);

    @Query("SELECT DISTINCT user FROM Muser user " +
            "INNER JOIN FETCH user.roles AS authorities " +
            "WHERE lower(user.userName) = :userName")
    Muser findByUserNameV1(@Param("userName") String userName);
//	Muser findByUserName(String userName);

    @Query("SELECT user.autoNumber FROM Muser user " +
            "WHERE lower(user.userName) = :userName")
    String findAutoNumberByUserName(@Param("userName") String userName);

    @Query("SELECT user.id FROM Muser user " +
            "WHERE user.autoNumber= :autoNumber")
    Integer findUserByAutoNumber(@Param("autoNumber") String autoNumber);

    @Query("SELECT count(user.id) FROM Muser user " +
            "WHERE user.autoNumber= :autoNumber")
    Integer checkUserExist(@Param("autoNumber") String autoNumber);

    Optional<Muser> findByUserNameIgnoreCase(String userName);

    Optional<Muser> findByAutoNumber(String autonumber);

    List<Muser> findDistinctByOrderByIdDesc();
}