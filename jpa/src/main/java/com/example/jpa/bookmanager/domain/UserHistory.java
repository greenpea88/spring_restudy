package com.example.jpa.bookmanager.domain;

import com.example.jpa.bookmanager.domain.listener.Auditable;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@EntityListeners(value = MyEntityListener.class)
//@EntityListeners(value = AuditingEntityListener.class)
public class UserHistory extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String name;
    private String email;
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
}
