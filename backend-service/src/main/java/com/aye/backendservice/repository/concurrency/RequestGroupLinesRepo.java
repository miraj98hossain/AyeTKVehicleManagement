package com.aye.backendservice.repository.concurrency;


import com.aye.entity.RequestGroupHeader;
import com.aye.entity.RequestGroupLine;
import com.aye.entity.concurrency.Executables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Toufiq on 3/7/2019.
 */
public interface RequestGroupLinesRepo extends JpaRepository<RequestGroupLine, Long> {
    List<RequestGroupLine> findByRequestGroupHeader(RequestGroupHeader RequestGroupHeader);

    List<RequestGroupLine> findByRequestGroupHeaderId(Long requestGroupHeader);

    List<RequestGroupLine> findByExecutables(Executables e);

    List<RequestGroupLine> findByRequestGroupHeaderAndIsActiveTrue(RequestGroupHeader RequestGroupHeader);


    List<RequestGroupLine> findByExecutables_Id(Long executablesId);
}
