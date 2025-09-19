package com.code81.library.service;

public interface UserActivityLogService {
    void log(String performedBy, String action, String details);
}
