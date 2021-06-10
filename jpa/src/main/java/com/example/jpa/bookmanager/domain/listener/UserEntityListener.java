package com.example.jpa.bookmanager.domain.listener;

import com.example.jpa.bookmanager.domain.User;
import com.example.jpa.bookmanager.domain.UserHistory;
import com.example.jpa.bookmanager.repository.UserHistoryRepository;
import com.example.jpa.bookmanager.repository.UserRepository;
import com.example.jpa.bookmanager.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

//@Component //EntityListener는 Spring bean을 주입받지 못함
public class UserEntityListener {
//    @Autowired
//    private UserHistoryRepository userHistoryRepository;

    @PrePersist
    @PreUpdate
    public void prePresistAndPreUpdate(Object o){
        //Bean을 가지고올 수 있게 됨
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
