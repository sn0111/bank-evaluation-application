package com.tech.bank.util;

import com.tech.bank.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EvaluationJobs {

    private final UserDAO dao;

    private final CacheManager cacheManager;

    public EvaluationJobs(UserDAO dao, CacheManager cacheManager) {
        this.dao = dao;
        this.cacheManager = cacheManager;
    }

    @Scheduled(fixedRate = 600000)
    public void reloadUsers() {
        log.info("Reload users with updated list");
        Cache cache = cacheManager.getCache("all-users");
        assert cache != null;
        cache.clear();
        dao.getAllUsers();
    }
}
