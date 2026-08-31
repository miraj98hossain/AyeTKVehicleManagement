package com.aye.backendservice.service.concurrency;


import com.aye.backendservice.repository.concurrency.RequestGroupHeaderRepo;
import com.aye.backendservice.repository.concurrency.RequestGroupLinesRepo;
import com.aye.backendservice.service.orgManagement.UserAccessTempltService;
import com.aye.backendservice.service.user.MuserService;
import com.aye.dto.response.concurrency.UserExecutable;
import com.aye.dto.response.user.RequestGroupHeaderDto;
import com.aye.dto.response.user.RequestGroupLineDto;
import com.aye.entity.CommonColumn;
import com.aye.entity.RequestGroupHeader;
import com.aye.entity.RequestGroupLine;
import com.aye.entity.UserAccessTemltDtl;
import com.aye.entity.user.Muser;
import com.aye.enums.MenuType;
import com.aye.mapper.concurrency.RequestGroupHeaderMapper;
import com.aye.mapper.concurrency.RequestGroupLineMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Toufiq on 3/7/2019.
 */
@Service
public class RequestGroupServiceImpl implements RequestGroupService {


    @Autowired
    private RequestGroupHeaderRepo requestGroupHeaderMRepo;

    @Autowired
    private RequestGroupLinesRepo requestGroupLineMRepo;
    @Autowired
    private UserAccessTempltService UserAccessTempltService;

    @Autowired
    private RequestGroupHeaderMapper requestGroupHeaderMapper;
    @Autowired
    private RequestGroupLineMapper requestGroupLineMapper;
    @Autowired
    private MuserService muserService;

    @Transactional(readOnly = true)
    @Override
    public List<RequestGroupHeaderDto> findAll() {
        var requestGroupHeaderMS = this.requestGroupHeaderMRepo.findAll();
        return this.requestGroupHeaderMapper.toResponseDto(requestGroupHeaderMS);
    }

    @Transactional(readOnly = true)
    @Override
    public RequestGroupHeaderDto findByRequestGroupId(Long requestGroupId) {
        var requestGroupHeaderM = this.requestGroupHeaderMRepo.findById(requestGroupId).orElseThrow(() -> new EntityNotFoundException("Invalid RequestGroupHeader Id", null));
        return this.requestGroupHeaderMapper.toResponseDto(requestGroupHeaderM);
    }

    @Transactional(readOnly = true)
    @Override
    public RequestGroupHeaderDto findByUserAccessTempletDtlId(Integer userAccessTmpDtlId) {
        UserAccessTemltDtl tempDetails = this.UserAccessTempltService.findByDtlId(userAccessTmpDtlId);
        if (tempDetails != null && tempDetails.getRequestGroupHeader() != null && tempDetails.getRequestGroupHeader().getId() != null) {
            return this.requestGroupHeaderMapper.toResponseDto(tempDetails.getRequestGroupHeader());
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserExecutable> findUserExecutableListByUserAccessTempletDtlId(Integer userAccessTmpDtlId) {
        UserAccessTemltDtl tempDetails = this.UserAccessTempltService.findByDtlId(userAccessTmpDtlId);
        if (tempDetails != null && tempDetails.getRequestGroupHeader() != null && tempDetails.getRequestGroupHeader().getId() != null) {
            return tempDetails.getRequestGroupHeader()
                    .getRequestGroupLines()
                    .stream().map(requestGroupLineMapper::toResponseDto)
                    .filter(RequestGroupLineDto::getIsActive)
                    .filter(requestGroupLineDto -> requestGroupLineDto
                            .getExecutables().getExecType().equals(MenuType.REPORT))
                    .map(UserExecutable::new).collect(Collectors.toList());
        }
        return null;
    }

    public RequestGroupHeader findByRequestGroupIdInner(Long requestGroupId) {
        return this.requestGroupHeaderMRepo.findById(requestGroupId).orElseThrow(() -> new EntityNotFoundException("Invalid RequestGroupHeader Id", null));

    }

    @Transactional
    @Override
    public RequestGroupHeaderDto saveRequestGroup(RequestGroupHeaderDto requestGroupHeaderMDTO, String userName) {
        RequestGroupHeader entity = new RequestGroupHeader();
        Muser muser = this.muserService.findByUserName(userName);
        if (requestGroupHeaderMDTO.getId() == null) {
            entity = this.requestGroupHeaderMapper.toEntity(requestGroupHeaderMDTO);
            entity.getColumn().setCreatedBy(muser);
        } else {
            entity = this.requestGroupHeaderMRepo.findById(requestGroupHeaderMDTO.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Request GroupHeader not found with id: " + requestGroupHeaderMDTO.getId())
            );
            this.requestGroupHeaderMapper.toEntity(requestGroupHeaderMDTO, entity);
            entity.getColumn().setLastUpdateBy(muser.getId());
        }
        var requestGroupHeaderM = this.requestGroupHeaderMRepo.save(entity);
        return this.requestGroupHeaderMapper.toResponseDto(requestGroupHeaderM);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RequestGroupLineDto> findByRequestGroupHeaderId(Long aLong) {
        var requestGroupHeader = findByRequestGroupIdInner(aLong);

        var requestGroupLineMS = this.requestGroupLineMRepo.findByRequestGroupHeader(requestGroupHeader);
        return this.requestGroupLineMapper.toResponseDto(requestGroupLineMS);
    }

    @Transactional(readOnly = true)
    @Override
    public RequestGroupLineDto findByRequestLineId(Long aLong) {
        var requestGroupLineM = this.requestGroupLineMRepo.findById(aLong).orElseThrow(() -> new EntityNotFoundException("Invalid RequestGroupLine Id", null));
        return this.requestGroupLineMapper.toResponseDto(requestGroupLineM);
    }

    @Transactional(readOnly = true)
    public RequestGroupLine findByRequestLineIdInner(Long aLong) {

        return this.requestGroupLineMRepo.findById(aLong).orElseThrow(() -> new EntityNotFoundException("Invalid RequestGroupLine Id", null));
    }

    @Transactional
    @Override
    public RequestGroupLineDto saveRequestGroupLine(RequestGroupLineDto requestGroupLineMDTO, String userName) {
        RequestGroupLine entity = new RequestGroupLine();
        Muser muser = this.muserService.findByUserName(userName);
        if (requestGroupLineMDTO.getId() == null) {
            entity = this.requestGroupLineMapper.toEntity(requestGroupLineMDTO);
            CommonColumn commonColumn = new CommonColumn();
            commonColumn.setCreatedBy(muser);
            entity.setColumn(commonColumn);
        } else {
            entity = this.requestGroupLineMRepo.findById(requestGroupLineMDTO.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Request GroupLine not found with id: " + requestGroupLineMDTO.getId())
            );
            this.requestGroupLineMapper.toEntity(requestGroupLineMDTO, entity);
            entity.getColumn().setLastUpdateBy(muser.getId());
        }
        var save = this.requestGroupLineMRepo.save(entity);
        return this.requestGroupLineMapper.toResponseDto(save);
    }

    @Transactional
    @Override
    public void deleteByRequestGroupLineId(Long aLong) {
        this.requestGroupLineMRepo.deleteById(aLong);
    }

}