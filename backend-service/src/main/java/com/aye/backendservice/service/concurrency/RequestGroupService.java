package com.aye.backendservice.service.concurrency;


import com.aye.dto.response.concurrency.UserExecutable;
import com.aye.dto.response.user.RequestGroupHeaderDto;
import com.aye.dto.response.user.RequestGroupLineDto;

import java.util.List;

/**
 * Created by Toufiq on 11/6/2019.
 */
public interface RequestGroupService {

    List<RequestGroupHeaderDto> findAll();

    RequestGroupHeaderDto findByRequestGroupId(Long requestGroupId);

    RequestGroupHeaderDto findByUserAccessTempletDtlId(Integer templtDtlId);

    List<UserExecutable> findUserExecutableListByUserAccessTempletDtlId(Integer userAccessTmpDtlId);

    RequestGroupHeaderDto saveRequestGroup(RequestGroupHeaderDto requestGroupHeaderMDTO, String userName);


    List<RequestGroupLineDto> findByRequestGroupHeaderId(Long aLong);

    RequestGroupLineDto findByRequestLineId(Long aLong);

    RequestGroupLineDto saveRequestGroupLine(RequestGroupLineDto requestGroupLineMDTO, String userName);

    void deleteByRequestGroupLineId(Long aLong);
}
