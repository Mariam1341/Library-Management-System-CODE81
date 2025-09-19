package com.code81.library.service.impl;


import com.code81.library.entity.UserActivityLog;
import com.code81.library.repository.UserActivityLogRepository;
import com.code81.library.service.UserActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserActivityLogServiceImpl implements UserActivityLogService {

    private final UserActivityLogRepository logRepository;

    @Override
    public void log(String performedBy, String action, String details) {
        UserActivityLog log = UserActivityLog.builder()
                .performedBy(performedBy)
                .action(action)
                .details(details)
                .timestamp(LocalDateTime.now())
                .build();

        logRepository.save(log);
    }
}