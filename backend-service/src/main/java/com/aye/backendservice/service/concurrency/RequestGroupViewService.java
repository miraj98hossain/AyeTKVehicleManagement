package com.aye.backendservice.service.concurrency;

import com.aye.dto.response.concurrency.UserExecutable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 04/07/2026
 * @time: 4:29 pm
 */
@Service
@RequiredArgsConstructor
public class RequestGroupViewService {
    private final RequestGroupService requestGroupService;

    List<UserExecutable> findUserExecutableListByUserAccessTempletDtlId(Integer templtDtlId) {

        return this.requestGroupService.findUserExecutableListByUserAccessTempletDtlId(templtDtlId);

    }
}
