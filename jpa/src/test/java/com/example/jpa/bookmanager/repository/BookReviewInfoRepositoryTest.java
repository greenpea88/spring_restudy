package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Book;
import com.example.jpa.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void crudTest(){
        givenBookReviewInfo();

        System.out.println(">>>"+ bookReviewInfoRepository.findAll());
    }

    @Test
    void crudTest2(){
        givenBookReviewInfo();

        Book result = bookReviewInfoRepository.
                        findById(1L)
                        .orElseThrow(RuntimeException::new)
                .getBook();

        System.out.println(">>>>" + result);
    }

    private Book givenBook(){
        Book book = new Book();
        book.setName("kang");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
//        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);
    }

}