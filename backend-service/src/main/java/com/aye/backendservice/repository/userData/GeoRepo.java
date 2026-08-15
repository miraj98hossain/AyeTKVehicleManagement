package com.aye.backendservice.repository.userData;

import com.aye.entity.geo.Geo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Toufiq on 9/24/2019.
 */
public interface GeoRepo extends JpaRepository<Geo, Integer>, JpaSpecificationExecutor<Geo> {


}
