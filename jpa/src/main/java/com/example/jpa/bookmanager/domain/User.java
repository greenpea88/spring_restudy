package com.example.jpa.bookmanager.domain;

import com.example.jpa.bookmanager.domain.listener.Auditable;
import com.example.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity //객체를 entity로 선언해줌
@Table
//@EntityListeners(value = {MyEntityListener.class, UserEntityListener.class})
//@EntityListeners(value = {AuditingEntityListener.class, UserEntityListener.class})
@EntityListeners(value = UserEntityListener.class)
public class User extends BaseEntity{
    @Id //Entity로 설정하면 primary key값을 필요로 함
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동적으로 증가함
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;

    @Column
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

}