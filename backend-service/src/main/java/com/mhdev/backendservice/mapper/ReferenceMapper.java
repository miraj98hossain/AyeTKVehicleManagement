package com.mhdev.backendservice.mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;



@Component
public class ReferenceMapper {
    @PersistenceContext
    private EntityManager entityManager;
    @ObjectFactory
    public <T> T map(@NotNull final Long id, @TargetType Class<T> type){
        return entityManager.find(type,id);
    }
}
