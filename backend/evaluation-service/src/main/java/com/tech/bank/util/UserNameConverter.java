package com.tech.bank.util;

import com.tech.bank.dao.UserDAO;
import com.tech.bank.data.dto.CommentsDTO;
import com.tech.bank.data.entity.CommentsEntity;
import com.tech.bank.data.entity.UserEntity;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserNameConverter extends AbstractConverter<CommentsEntity, CommentsDTO> {

    @Autowired
    private UserDAO userDAO;

    @Override
    protected CommentsDTO convert(CommentsEntity commentsEntity) {
        try {
            UserEntity user = userDAO.getAllUsers()
                    .stream()
                    .filter(e -> Objects.equals(e.getUserId(), commentsEntity.getCreatedBy()))
                    .findFirst()
                    .orElseThrow(() -> new BankEvaluationException("User not found"));
            CommentsDTO dto = new CommentsDTO();
            dto.setCreatedBy(user.getUserEmail());
            return dto;
        } catch (BankEvaluationException e) {
            throw new RuntimeException(e);
        }
    }
}
