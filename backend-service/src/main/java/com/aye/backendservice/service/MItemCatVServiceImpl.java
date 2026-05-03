package com.aye.backendservice.service;

import com.aye.backendservice.repository.MItemCatVRepo;
import com.aye.entitylib.entity.order.MItemCatV;
import jakarta.persistence.criteria.Expression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Miraj
 * @date: 02/05/2026
 * @time: 7:34 pm
 */
@Service
@RequiredArgsConstructor
public class MItemCatVServiceImpl implements MItemCatVService {
    private final MItemCatVRepo mItemCatVRepo;

    @Transactional
    @Override
    public List<MItemCatV> findAllByItemCombinations(List<String> itemCombinations) {
        var list = this.mItemCatVRepo.findAll((root, query, cb) -> {
            Expression<String> fullPath = cb.concat(
                    cb.concat(root.get("categories"), "-"),
                    cb.concat(root.get("family"), cb.concat("-", root.get("clazz")))
            );

            return fullPath.in(itemCombinations);
        });
        return list;
    }
}
