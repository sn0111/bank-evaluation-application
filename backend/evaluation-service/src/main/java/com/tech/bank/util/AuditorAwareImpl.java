package com.tech.bank.util;

import com.tech.bank.servie.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private UserService service;

    @Override
    public Optional<String> getCurrentAuditor() {
        if(SecurityContextHolder.getContext().getAuthentication()!=null){
            String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            return Optional.of(service.getUser(userEmail));
        }
        return Optional.of("");
    }


}