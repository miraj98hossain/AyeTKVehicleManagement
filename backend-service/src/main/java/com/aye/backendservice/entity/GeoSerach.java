package com.aye.backendservice.entity;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.geo.Geo;

import java.util.Date;

/**
 * Created by toufiq on 5/11/2021.
 */
public class GeoSerach {

    private Date fromDate;
    private Date toDate;
    private Geo.GeoRefTrnsSource trnsSource;
    private String trnsNumber;
    private Integer trnsId;
    //    private Integer createdBy;
    private Muser createdBy;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Geo.GeoRefTrnsSource getTrnsSource() {
        return trnsSource;
    }

    public void setTrnsSource(Geo.GeoRefTrnsSource trnsSource) {
        this.trnsSource = trnsSource;
    }

    public Integer getTrnsId() {
        return trnsId;
    }

    public void setTrnsId(Integer trnsId) {
        this.trnsId = trnsId;
    }

//    public Integer getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(Integer createdBy) {
//        this.createdBy = createdBy;
//    }


    public String getTrnsNumber() {
        return trnsNumber;
    }

    public void setTrnsNumber(String trnsNumber) {
        this.trnsNumber = trnsNumber;
    }

    public Muser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Muser createdBy) {
        this.createdBy = createdBy;
    }
}
