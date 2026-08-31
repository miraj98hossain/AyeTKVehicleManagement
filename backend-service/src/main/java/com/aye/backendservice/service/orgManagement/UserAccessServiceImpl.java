package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.UserAccessRepo;
import com.aye.entity.*;
import com.aye.entity.user.Muser;
import com.aye.enums.RoleTypes;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Administrator on 2/17/2019.
 */
@Service
public class UserAccessServiceImpl implements UserAccessService {

    @Autowired
    private UserAccessRepo userAccessRepo;
    @Autowired
    private UserMenuService userMenuService;
    @Autowired
    private UserAccessTempltService userAccessTempltService;


    @Override

    public List<UserAccess> getAllUserAccess() {
        return this.userAccessRepo.findAll();
    }

    @Override

    public List<UserAccess> findByUserId(Muser user) {
        return this.userAccessRepo.findByUserId(user.getId());

    }

    @Override

    public List<UserAccess> findByUserId(Integer userId) {
        return this.userAccessRepo.findByUserId(userId);
    }

    @Override

    public void saveDtlLine(UserAccess userAccess) {
        this.userAccessRepo.save(userAccess);


    }

    @Override

    public UserAccess findById(Integer id) {
        UserAccess uacess = this.userAccessRepo.findById(id).orElse(null);
//        UserAccess uacess = this.userAccessRepo.getOne(id);
        return uacess;
    }

    @Override

    public UserAccess findByUserAccessTempletAndUser(UserAccessTemplt userAccessTemplt, Muser user) {
        return this.userAccessRepo.findByUserAccessTempltAndUser(userAccessTemplt, user);
    }

    @Override
    public List<UserAccess> findActiveAccessByUsAndAppModule(Muser lUser, AppModule am) {
        return List.of();
    }

    private UserAccessTemltDtl setSubMenu(UserAccessTemltDtl userAccessTemltDtl) {
        UserAccessTemltDtl nUserAccessTemltDtl1 = new UserAccessTemltDtl();
        if (userAccessTemltDtl.getUserMenu().isActive()) {
            List<UserSubMenu> newUserSubMenus = new ArrayList<>();


            for (UserSubMenu usm : this.userMenuService.findSubMenuByUserMenu(userAccessTemltDtl.getUserMenu())) {
                if (usm.getIsActive()) {

                    newUserSubMenus.add(usm);
                }

            }
            newUserSubMenus.sort(Comparator.comparing(UserSubMenu::getId));
            UserMenu um = userAccessTemltDtl.getUserMenu();
            um.setSubMenus(newUserSubMenus);

            nUserAccessTemltDtl1 = userAccessTemltDtl;
        }

        return nUserAccessTemltDtl1;
    }

    private List<UserAccess> setUserAccessByUser(Muser u) {
        Date sysDate = new Date();
        List<UserAccess> userAccesses = this.userAccessRepo.findAll(new Specification<UserAccess>() {
            @Override
            public Predicate toPredicate(Root<UserAccess> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("user"), u));
                // If start_date is null, use sysDate
                Expression<Date> start = cb.coalesce(root.get("start_date"), sysDate);
                // If end_date is null, use sysDate
                Expression<Date> end = cb.coalesce(root.get("end_date"), sysDate);
                //  start <= sysDate <= end
                predicates.add(cb.lessThanOrEqualTo(start, sysDate));
                predicates.add(cb.greaterThanOrEqualTo(end, sysDate));
                return cb.and(predicates.toArray(new Predicate[0]));

            }
        });
        return userAccesses;
    }

    @Override

    public List<UserAccessTemltDtl> getUserAccess(Muser user, RoleTypes roleTypes) {

        List<UserAccessTemltDtl> outMenus = new ArrayList<>();
        List<UserAccess> access = this.setUserAccessByUser(user);

        for (UserAccess usacc : access) {
            List<UserAccessTemplt> accessTemp = this.userAccessTempltService.findByIdOrderById(usacc.getUserAccessTemplt().getId());

            for (UserAccessTemplt uact : accessTemp) {

                uact.getUserAccessTemltDtls().sort(Comparator.comparing(UserAccessTemltDtl::getId));

                for (UserAccessTemltDtl tmpdtl : uact.getUserAccessTemltDtls()) {
                    Calendar cd = Calendar.getInstance();
                    cd.setTime(new Date());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String from = sdf.format(cd.getTime());
                    String start = sdf.format(tmpdtl.getStartDate());
                    String end;

                    if (tmpdtl.getEndDate() == null) {
                        end = from;
                    } else {
                        end = sdf.format(tmpdtl.getEndDate());
                    }
                    LocalDate cc = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate lstart = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate lend = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    if ((lstart.compareTo(cc) * lend.compareTo(cc)) == 0) {
                        if (tmpdtl.getUserMenu().getPageType().equals(roleTypes)) {
                            UserAccessTemltDtl ntmpdtl = setSubMenu(tmpdtl);
                            outMenus.add(ntmpdtl);
                        }
                    }
                }
            }
        }


        return outMenus;
    }

    @Override
    public List<UserAccessTemltDtl> getUserAccess(Muser user) {

        List<UserAccessTemltDtl> outMenus = new ArrayList<>();
        List<UserAccess> access = this.setUserAccessByUser(user);

        for (UserAccess usacc : access) {
            List<UserAccessTemplt> accessTemp = this.userAccessTempltService.findByIdOrderById(usacc.getUserAccessTemplt().getId());

            for (UserAccessTemplt uact : accessTemp) {

                uact.getUserAccessTemltDtls().sort(Comparator.comparing(UserAccessTemltDtl::getId));
                for (UserAccessTemltDtl tmpdtl : uact.getUserAccessTemltDtls()) {
                    LocalDate cc = LocalDate.now();
                    LocalDate lstart = tmpdtl.getStartDate();
                    LocalDate lend = (tmpdtl.getEndDate() == null) ? cc : tmpdtl.getEndDate();

                    if ((lstart.compareTo(cc) * lend.compareTo(cc)) == 0) {
                        UserAccessTemltDtl ntmpdtl = setSubMenu(tmpdtl);
                        outMenus.add(ntmpdtl);
                    }
                }
//                for (UserAccessTemltDtl tmpdtl : uact.getUserAccessTemltDtls()) {
//                    Calendar cd = Calendar.getInstance();
//                    cd.setTime(new Date());
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    String from = sdf.format(cd.getTime());
//                    String start = sdf.format(tmpdtl.getStartDate());
//                    String end;
//
//                    if (tmpdtl.getEndDate() == null) {
//                        end = from;
//                    } else {
//                        end = sdf.format(tmpdtl.getEndDate());
//                    }
//                    LocalDate cc = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                    LocalDate lstart = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                    LocalDate lend = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                    if ((lstart.compareTo(cc) * lend.compareTo(cc)) == 0) {
//                        UserAccessTemltDtl ntmpdtl = setSubMenu(tmpdtl);
//                        outMenus.add(ntmpdtl);
//                    }
//                }
            }
        }


        return outMenus;
    }

    @Override
    public List<UserAccessTemltDtl> findUserAccessTempDtlByMenuId(Integer menuId) {
        List<UserAccessTemltDtl> list = this.userAccessTempltService.findUserAccessTempDtlByMenuId(menuId);
        return list;
    }

//    @Override
//    public List<UserAccessTemltDtl> getUserAccessNew1(String user) {
//        List<UserAccessTemltDtl> outMenus = new ArrayList<>();
//        List<UserAccess> access = this.userAccessRepo.findByUserOrderById(this.muserService.findByUserName(user));
//        for (UserAccess usacc : access) {
//            List<UserAccessTemplt> accessTemp = this.userAccessTempltService.findByIdOrderById(usacc.getUserAccessTemplt().getId());
//            for (UserAccessTemplt uact : accessTemp) {
//                uact.getUserAccessTemltDtls().sort(Comparator.comparing(UserAccessTemltDtl::getId));
//                for (UserAccessTemltDtl tmpdtl : uact.getUserAccessTemltDtls()) {
//                    Calendar cd = Calendar.getInstance();
//                    cd.setTime(new Date());
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    String from = sdf.format(cd.getTime());
//                    String start = sdf.format(tmpdtl.getStartDate());
//
//                    String end;
//                    if (tmpdtl.getEndDate().equals(null)) {
//                        end = from;
//                    } else {
//                        end = sdf.format(tmpdtl.getEndDate());
//                    }
//                    outMenus.add(tmpdtl);
//                }
//            }
//        }
//
//
//        return outMenus;
//    }
}
