package com.aye.backendservice.applicationEvent.listener;

import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserAccessService;
import com.aye.backendservice.applicationEvent.UserAccessCacheGenerateEvent;
import com.aye.backendservice.applicationEvent.UserAccessCacheSyncEvent;
import com.aye.backendservice.applicationEvent.UserAccessTemplateCacheSyncEvent;
import com.aye.backendservice.applicationEvent.UserMenusCacheSyncEvent;
import com.aye.dtoLib.dto.response.userOrg.UserAccessTemltDtlResponse;
import com.aye.entitylib.entity.UserAccess;
import com.aye.entitylib.entity.UserAccessTemltDtl;
import com.aye.entitylib.entity.user.Muser;
import com.aye.mapper.userOrg.UserAccessTemltDtlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.aye.backendservice.utils.RedisKey.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserLoginResponseCacheSyncListener {


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

            log.info("Synced login response cache for user {}", userId);

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

        String templateKey = buildTemplateKey(event.getUserAccessTempId());

        Set<String> userIds = redisTemplate.opsForSet().members(templateKey);

        if (userIds == null || userIds.isEmpty()) {
            return;
        }

        for (String userId : userIds) {
            this.syncUserLoginResponse(Integer.valueOf(userId));
        }
    }


    @Async("asyncExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleUserMenuChange(UserMenusCacheSyncEvent event) {

        List<UserAccessTemltDtl> dtlList = this.userAccessService.findUserAccessTempDtlByMenuId(event.getUserMenuId());
        dtlList.forEach(dtl -> {
            String templateKey = buildTemplateKey(dtl.getUserAccessTemplt().getId());
            Set<String> userIds = redisTemplate.opsForSet().members(templateKey);
            if (userIds == null || userIds.isEmpty()) {
                return;
            }
            for (String userId : userIds) {
                this.syncUserLoginResponse(Integer.valueOf(userId));
            }
        });
    }


    @Async("asyncExecutor")
    @EventListener
    public void handleUserAccessCacheGenerate(UserAccessCacheGenerateEvent event) {

        List<UserAccess> allList = userAccessService.getAllUserAccess();

        Map<Integer, List<Integer>> map = allList.stream()
                .collect(Collectors.groupingBy(
                        ua -> ua.getUserAccessTemplt().getId(),
                        Collectors.mapping(e -> e.getUser().getId(), Collectors.toList())
                ));

        map.forEach((tempId, list) -> {

            String key = this.buildTemplateKey(tempId);

            list.stream()
                    .map(String::valueOf)
                    .forEach(userId ->
                            redisTemplate.opsForSet().add(key, userId)
                    );

            for (Integer userId : list) {
                this.syncUserLoginResponse(userId);
            }
        });
    }

    private String buildTemplateKey(Integer templateId) {
        return USER_ACCESS_TEMPLATE_KEY + ":" + USER_ACCESS_TEMPLATE_HASH_KEY + ":" + templateId;
    }

    private String buildUserKey(Integer userId) {
        return USER_LOGIN_RESPONSE_HASH_KEY + ":" + userId;
    }
}