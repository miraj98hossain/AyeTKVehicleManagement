package com.aye.backendservice.repository.concurrency;


import com.aye.entity.concurrency.Executables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutableRepo extends JpaRepository<Executables, Long> {

//    @Query(value = "SELECT :sequenceName.NEXTVAL FROM dual", nativeQuery = true)
//    Long getNextSequenceValue(@Param("sequenceName")String sequenceName);
}
