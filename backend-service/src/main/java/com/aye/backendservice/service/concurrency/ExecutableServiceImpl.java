package com.aye.backendservice.service.concurrency;


import com.aye.backendservice.repository.concurrency.ExecutableParameterRepo;
import com.aye.backendservice.repository.concurrency.ExecutableRepo;
import com.aye.backendservice.service.user.MuserService;
import com.aye.dto.response.concurrency.ExecutableParameterDto;
import com.aye.dto.response.concurrency.ExecutablesDto;
import com.aye.entity.CommonColumn;
import com.aye.entity.concurrency.DataSet;
import com.aye.entity.concurrency.ExecutableParameter;
import com.aye.entity.concurrency.Executables;
import com.aye.entity.user.Muser;
import com.aye.mapper.concurrency.ExecutableMapper;
import com.aye.mapper.concurrency.ExecutableParameterMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
//@AllArgsConstructor
public class ExecutableServiceImpl implements ExecutableService {
    private final ExecutableRepo executableRepo;
    private final ExecutableParameterRepo executableParameterRepo;
    private final ExecutableMapper executableMapper;
    private final ExecutableParameterMapper executableParameterMapper;
    private final MuserService muserService;
    @PersistenceContext(unitName = "ebs")
    private EntityManager entityManager;
    @Value("${spring.Report.dir}")
    private String reportDirectory;


    @Override
    public List<Executables> findAllExecutables() {
        return this.executableRepo.findAll();
    }

    @Override
    public Executables findById(Long id) {
        var byId = this.executableRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("invalid Executables id", null));

        return byId;
    }

    @Override
    public Executables saveExecutable(ExecutablesDto executablesDto, String userName) {
        Executables entity;
        Muser userM = muserService.findByUserName(userName);
        if (executablesDto.getId() == null) {
            entity = this.executableMapper.toEntity(executablesDto);
            CommonColumn commonColumn = new CommonColumn();
            commonColumn.setCreatedBy(userM);
            entity.setColumn(commonColumn);
        } else {
            entity = this.executableRepo.findById(executablesDto.getId()).orElseThrow(() -> new EntityNotFoundException("invalid Executables id", null));
            CommonColumn commonColumn = entity.getColumn();
            commonColumn.setLastUpdateBy(userM.getId());
            executableMapper.toEntity(executablesDto, entity);
            entity.setColumn(commonColumn);
        }
        return this.executableRepo.save(entity);
    }

    @Override
    public Executables findExecutablesById(Long executablesId) {
        return findById(executablesId);
    }

    @Override
    public Executables uploadFile(Long executableId, MultipartFile files, String userName) throws IOException {
        if (files.isEmpty()) {
            return null;
        }
        String original = StringUtils.cleanPath(files.getOriginalFilename() == null ? "" : files.getOriginalFilename());
        if (original.isBlank()) {
            throw new IllegalArgumentException("Filename is empty");
        }
        String lower = original.toLowerCase();
        if (!lower.endsWith(".jrxml")) {
            throw new IllegalArgumentException("Only .jrxml files are allowed");
        }
        Path dir = Paths.get(reportDirectory).toAbsolutePath().normalize();
        Files.createDirectories(dir);

        Path dest = dir.resolve(original).normalize();
        if (!dest.startsWith(dir)) {
            throw new IllegalArgumentException("Invalid file path");
        }

        try (InputStream in = files.getInputStream()) {
            Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
        }

        Executables executables = this.findExecutablesById(executableId);

        String baseName = original.substring(0, original.length() - ".jrxml".length());
        String pageName = "D:/g-apps-services/reports-jrxml/";
        executables.setExecPath(pageName);
        executables.setFileName(baseName);
        ExecutablesDto executablesDto = executableMapper.toResponseDto(executables);
        return saveExecutable(executablesDto, userName);

    }


    // ******************** Executable Params ************************
    @Override
    public List<ExecutableParameter> getExecutableParamList(Long executableId) {

        List<ExecutableParameter> executableParameters = executableParameterRepo.findAllByExecutables_Id(executableId);
        return executableParameters;

    }

    @Override
    public ExecutableParameter getExecutableParamById(Long executableId, Long exectParamId) {
        ExecutableParameter executableParameter = this.executableParameterRepo.findByExecutables_IdAndId(executableId, exectParamId);
        return executableParameter;
    }

    @Override
    @Transactional
    public ExecutableParameter saveExecutableParam(
            ExecutableParameterDto dto,
            Long executableId,
            String userName) {

        DataSet dataSet = null;
        ExecutableParameter dataDependsOn = null;

        // Existing DataSet reference
        if (dto.getDataSet() != null && dto.getDataSet().getId() != null) {
            dataSet = entityManager.getReference(
                    DataSet.class,
                    dto.getDataSet().getId()
            );
        }

        // Existing ExecutableParameter dependency reference
        if (dto.getDataDependsOn() != null
                && dto.getDataDependsOn().getId() != null) {

            dataDependsOn = entityManager.getReference(
                    ExecutableParameter.class,
                    dto.getDataDependsOn().getId()
            );
        }

        Muser userM = muserService.findByUserName(userName);

        ExecutableParameter entity;

        if (dto.getId() == null) {

            entity = executableParameterMapper.toEntity(dto);

            CommonColumn commonColumn = new CommonColumn();
            commonColumn.setCreatedBy(userM);
            entity.setColumn(commonColumn);

        } else {

            entity = executableParameterRepo.findById(dto.getId())
                    .orElseThrow(() ->
                            new EntityNotFoundException(
                                    "Invalid ExecutableParameter id",
                                    null
                            ));

            CommonColumn commonColumn = entity.getColumn();
            commonColumn.setLastUpdateBy(userM.getId());

            executableParameterMapper.toEntity(dto, entity);

            entity.setColumn(commonColumn);
        }

        // Set relationships AFTER mapper
        entity.setDataSet(dataSet);
        entity.setDataDependsOn(dataDependsOn);

//        // Set executable relationship
//        Executables executables = entityManager.getReference(
//                Executables.class,
//                executableId
//        );
//        entity.setExecutables(executables);

        return executableParameterRepo.save(entity);
    }

}
