package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository<Entity-type, primaryKey-type>
public interface UserRepository extends JpaRepository<User, Long> {
    //spring library jpa가 지원해주는 영역
    List<User> findByName(String name);

    List<User> findFirstByName(String name, Sort sort);

    Page<User> findByName(String name, Pageable pageable);


}
