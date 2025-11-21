package com.aye.backendservice.service;


import com.aye.RestfulServer.model.geo.Geo;
import com.aye.backendservice.entity.GeoSerach;

import java.util.List;

/**
 * Created by Toufiq on 9/24/2019.
 */
public interface GeoService {
    public List<Geo> findAll();

    public List<Geo> search(GeoSerach geoSerach);

    public void save(Geo geo, String mode);

}
