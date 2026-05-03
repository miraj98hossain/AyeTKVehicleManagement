package com.aye.backendservice.service;

import com.aye.entitylib.entity.order.MItemCatV;

import java.util.List;

/**
 * @author: Miraj
 * @date: 02/05/2026
 * @time: 7:34 pm
 */
public interface MItemCatVService {


    List<MItemCatV> findAllByItemCombinations(List<String> itemCombinations);
}
