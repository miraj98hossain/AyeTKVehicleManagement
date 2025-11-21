package com.aye.backendservice.repository;


import com.aye.RestfulServer.model.geo.Geo;
import com.aye.backendservice.entity.MtrnsCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by toufiq on 5/28/2021.
 */
public interface MtrnsCountRepo extends JpaRepository<MtrnsCount, Long> {

    List<MtrnsCount> findByTrnsSourceNot(Geo.GeoRefTrnsSource trnsSource);

    List<MtrnsCount> findByTrnsSource(Geo.GeoRefTrnsSource trnsSource);
}
