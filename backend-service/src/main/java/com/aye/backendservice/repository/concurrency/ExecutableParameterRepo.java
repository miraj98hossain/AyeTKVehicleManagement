package com.aye.backendservice.repository.concurrency;

import com.aye.entity.concurrency.ExecutableParameter;
import com.aye.entity.concurrency.Executables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Miraj
 * @date: 04/07/2026
 * @time: 11:06 am
 */
public interface ExecutableParameterRepo extends JpaRepository<ExecutableParameter, Long> {
    List<ExecutableParameter> findByExecutables(Executables executablesMApp);

    List<ExecutableParameter> findAllByExecutables_Id(Long executablesId);

    ExecutableParameter findByExecutables_IdAndId(Long executablesId, Long id);
}
