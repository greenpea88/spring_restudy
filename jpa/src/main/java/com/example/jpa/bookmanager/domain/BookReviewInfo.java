package com.example.jpa.bookmanager.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BookReviewInfo extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

//    private Long bookId;
    @OneToOne //1:1 연관관계 mapping
    private Book book;

    private float averageReviewScore;
    private int reviewCount;
}
