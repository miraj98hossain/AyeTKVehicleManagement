package com.aye.backendservice.service.user;


import com.aye.backendservice.repository.user.MroleRepo;
import com.aye.backendservice.repository.user.MuserRepo;
import com.aye.dto.request.UserSearchRequest;
import com.aye.entity.projection.MuserDto;
import com.aye.entity.user.Mrole;
import com.aye.entity.user.Muser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class MuserServiceImpl implements MuserService {


    @PersistenceContext
    private EntityManager em;

    private Muser finalmuser;
    @Autowired
    private MuserRepo muserRepo;
    @Autowired
    private MroleRepo mroleRepo;


    public MuserServiceImpl() {

    }

    // @Autowired
    public Muser getuser() {
        return finalmuser;
    }

//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        // finalmuser =this.muserRepo.findByUserName(userName);
////        //System.out.println(userName);
//        String UserName = userName.toLowerCase();
//        Muser muser = this.muserRepo.findByUserName(UserName);
//
//        if (muser != null) {
////            return muser;
//
//            return new org.springframework.security.core.userdetails.User
//                    (muser.getUserName(), muser.getPassword(), muser.isEnabled(), muser.isAccountExpired(), muser.isCredentialsExpired(), muser.isAccountLocked(), muser.getGrantedAuthorities());

    /// /                    (
    /// /                    muser.getUserName(),muser.getPassword(),muser.getGrantedAuthorities()
    /// /
    /// /                    );
//        }
//        LOGGER.info("UsernameNotFoundException --", this.jsonWrite.getUserByName(userName).getUsername(), module);
//        throw new UsernameNotFoundException(userName);
//
//    }
    @Override
    public List<Muser> findAll() {
        return this.muserRepo.findAll();
    }

    @Override
    public List<MuserDto> findAllUser() {
        return this.muserRepo.findDistinctUser();
    }

    @Override
    public List<Muser> findAllUsers() {
        return this.muserRepo.findDistinctByOrderByIdDesc();
    }

//    @Override
//    // @Transactional
//    public Muser findByUserNameLogin(String userName) throws UserPrincipalNotFoundException {
//        UserDetails userDetails = loadUserByUsername(userName);
//
//        if (userDetails.isAccountNonLocked()) {
//            return this.muserRepo.findByUserName(userName.toLowerCase());
//        } else {
//            throw new UserPrincipalNotFoundException(userName + "Not Found");
//        }
//
//
//        // TODO Auto-generated method stub
//
//    }

    @Override
    // @Transactional
    public Muser findByUserName(String userName) {
//        return this.muserRepo.findByUserName(userName.toLowerCase());
        Muser mUser = this.muserRepo.findByUserNameIgnoreCase(userName.toLowerCase()).orElseThrow(() -> new RuntimeException("Invalid UserName"));
        return mUser;
    }


    @Override
    @Transactional
    public void updateUserAutoNumber(Integer userId) {
//        LOGGER.info("updateUserAutoNumber; userId "+userId,  this.jsonWrite.getUserById(userId).getUsername(),module);
        this.em.createNativeQuery("BEGIN m_common.M_update_autoNumber(:p_user_id); END;")
                .setParameter("p_user_id", userId).executeUpdate();
    }

    @Override
    public String findAutoNumberByUserName(String userName) {

        return this.muserRepo.findAutoNumberByUserName(userName.toLowerCase());
    }

    @Override
    public Integer findUserByAutoNumber(String autoNumber) throws NullPointerException {
//        LOGGER.info("findUserByAutoNumber ; AutoNumber -"+autoNumber, this.jsonWrite.getUserByAutonumber(autoNumber).getUserName(),module );
        return this.muserRepo.findUserByAutoNumber(autoNumber);
    }

    @Override
    public Muser findUserModelByAutoNumber(String autoNumber) {

        Muser muser = this.muserRepo.findByAutoNumber(autoNumber).orElseThrow(() -> new RuntimeException("Invalid Autonumber"));
        return muser;
    }

    @Override
    public Integer checkUserExist(String autoNumber) {

        return this.muserRepo.checkUserExist(autoNumber);
    }


    @Override
    public Muser findByAutoNumber(String autoNumber) {
        Muser muser = this.muserRepo.findByAutoNumber(autoNumber).orElseThrow(() -> new RuntimeException("Invalid Autonumber"));
        return muser;
    }


    @Override
    @Transactional
    public void PassWordChange(Integer Usid, String nPass) {

        //System.out.println(nPass + " nPass");
        this.em.createNativeQuery("BEGIN M_COMMON.M_CHANGE_PASS ( :P_USER_ID, :PASS ); END;")
                .setParameter("P_USER_ID", Usid)
                .setParameter("PASS", nPass)
                .executeUpdate();


    }

    @Override
    public List<Muser> findByUserNameLike(String s) {
        return this.muserRepo.findByUserNameIgnoreCaseContaining(s);
    }

    @Override
    public List<Muser> filterUsers(UserSearchRequest srcReq) {
        boolean isUserNameEmpty = (srcReq.getUserName() == null || srcReq.getUserName().isEmpty());
        boolean isFirstNameEmpty = (srcReq.getFirstName() == null || srcReq.getFirstName().isEmpty());
        boolean isLastNameEmpty = (srcReq.getLastName() == null || srcReq.getLastName().isEmpty());

        if (isUserNameEmpty && isFirstNameEmpty && isLastNameEmpty) {
            return this.muserRepo.findAll(PageRequest.of(0, 10)).getContent();
        }

        return this.muserRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (srcReq.getUserName() != null && !srcReq.getUserName().isEmpty()) {
                if (srcReq.getUserName().contains("%")) {
                    predicates.add(cb.like(cb.lower(root.get("userName")), srcReq.getUserName().toLowerCase().trim()));
                } else {
                    predicates.add(cb.like(cb.lower(root.get("userName")), "%" + srcReq.getUserName().toLowerCase().trim() + "%"));
                }
            }
            if (srcReq.getFirstName() != null && !srcReq.getFirstName().isEmpty()) {
                if (srcReq.getFirstName().contains("%")) {
                    predicates.add(cb.like(cb.lower(root.get("name")), srcReq.getFirstName().toLowerCase().trim()));
                } else {
                    predicates.add(cb.like(cb.lower(root.get("name")), "%" + srcReq.getFirstName().toLowerCase().trim() + "%"));
                }
            }
            if (srcReq.getLastName() != null && !srcReq.getLastName().isEmpty()) {
                if (srcReq.getLastName().contains("%")) {
                    predicates.add(cb.like(cb.lower(root.get("lastName")), srcReq.getLastName().toLowerCase().trim()));
                } else {
                    predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + srcReq.getLastName().toLowerCase().trim() + "%"));
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    public void updateUser(Muser muser) {
        this.muserRepo.save(muser);
    }

    @Override
    public Mrole findRoleByName(String name) {
        return this.mroleRepo.findByRole(name);
    }

    @Override
    public Mrole findRoleById(Integer id) {
        return this.mroleRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid Role id"));
    }

    @Override
    public List<Mrole> findAllRoles() {
        return this.mroleRepo.findAll();
    }

    @Override
    public Muser findById(Integer id) {
        return this.muserRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No User Found with this id:- " + id)
        );
    }


}
