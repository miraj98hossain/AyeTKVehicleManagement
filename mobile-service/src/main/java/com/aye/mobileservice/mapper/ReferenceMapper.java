package com.aye.mobileservice.mapper;

import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;


//@Component
//public class ReferenceMapper {
//    @PersistenceContext
//    private EntityManager entityManager;
//    @ObjectFactory
//   public <T> T map(@NotNull final Long id, @TargetType Class<T> type){
//        return entityManager.find(type,id);
//   }
//}
@Component
public class ReferenceMapper {

    @PersistenceContext
    private EntityManager entityManager;

    @ObjectFactory
    public <T> T map(@NotNull final Long id, @TargetType Class<T> type) {
        T entity = entityManager.find(type, id);
        if (entity == null) {
            throw new EntityNotFoundException(
                    String.format("%s not found with id %d", type.getSimpleName(), id)
            );
        }
        return entity;
    }
}
