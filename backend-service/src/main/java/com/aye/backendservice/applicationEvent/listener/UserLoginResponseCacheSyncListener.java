package com.aye.backendservice.applicationEvent.listener;

import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserAccessService;
import com.aye.backendservice.applicationEvent.UserAccessCacheSyncEvent;
import com.aye.backendservice.applicationEvent.UserAccessTemplateCacheSyncEvent;
import com.aye.dtoLib.dto.response.userOrg.UserAccessTemltDtlResponse;
import com.aye.entitylib.entity.user.Muser;
import com.aye.mapper.userOrg.UserAccessTemltDtlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserLoginResponseCacheSyncListener {

    private static final String USER_ACCESS_TEMPLATE_KEY = "USER-ACCESS-TEMPLATE";
    private static final String USER_LOGIN_RESPONSE_KEY = "USER-LOGIN-RESPONSE";

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;
    private final MuserService muserService;
    private final UserAccessService userAccessService;
    private final UserAccessTemltDtlMapper tempDtlMapper;

    private void syncUserLoginResponse(Integer userId) {
        try {

            Muser user = muserService.findById(userId);

            List<UserAccessTemltDtlResponse> accessList =
                    tempDtlMapper.toResponseDto(
                            userAccessService.getUserAccess(user)
                    );

            String json = objectMapper.writeValueAsString(accessList);

            redisTemplate.opsForHash().put(
                    USER_LOGIN_RESPONSE_KEY,
                    buildUserKey(userId),
                    json
            );

            log.debug("Synced login response cache for user {}", userId);

        } catch (Exception ex) {

            log.error(
                    "Failed to sync login response cache for user {}",
                    userId,
                    ex
            );
        }
    }

    @Async("asyncExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleUserAccessCacheSync(
            UserAccessCacheSyncEvent event
    ) {

        String templateKey = buildTemplateKey(
                event.getUserAccessTempId()
        );
        redisTemplate.opsForSet().add(
                templateKey,
                String.valueOf(event.getUserId())
        );

        this.syncUserLoginResponse(event.getUserId());
    }

    @Async("asyncExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleUserAccessTemplateDetailChange(UserAccessTemplateCacheSyncEvent event) {

        String templateKey = buildTemplateKey(
                event.getUserAccessTempId()
        );

        Set<String> userIds =
                redisTemplate.opsForSet().members(templateKey);

        if (userIds == null || userIds.isEmpty()) {
            return;
        }

        for (String userId : userIds) {
            this.syncUserLoginResponse(
                    Integer.valueOf(userId)
            );
        }
    }

    private String buildTemplateKey(Integer templateId) {
        return USER_ACCESS_TEMPLATE_KEY + ":" + "TEMPLATE:" + templateId;
    }

    private String buildUserKey(Integer userId) {
        return "USER:" + userId;
    }
}