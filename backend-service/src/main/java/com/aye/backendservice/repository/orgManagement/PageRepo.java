package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.Pages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Toufiq on 11/2/2019.
 */
public interface PageRepo extends JpaRepository<Pages, Long> {
    Pages findByPageName(String pageNmae);

    //        Pages findByPageUrl(PageUrl pageUrl);
//        List<Pages> findByPageGroup(Integer pageGroup);
    List<Pages> findAllByOrderByPageNameAsc();
}
