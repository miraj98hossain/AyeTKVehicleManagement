package com.aye.backendservice.repository;


import com.aye.entitylib.entity.MtrnsCount;
import com.aye.entitylib.entity.geo.Geo;
import com.aye.enums.GeoRefTrnsSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by toufiq on 5/28/2021.
 */
public interface MtrnsCountRepo extends JpaRepository<MtrnsCount, Long> {

    List<MtrnsCount> findByTrnsSourceNot(GeoRefTrnsSource trnsSource);

    List<MtrnsCount> findByTrnsSource(GeoRefTrnsSource trnsSource);
}
