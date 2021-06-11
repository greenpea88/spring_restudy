package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.User;
import com.example.jpa.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest //spring context를 로딩해서 사용하겠다
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;

//    @BeforeEach
//    void insertData(){
//
//    }

    @Test
//    @Transactional
    void crud(){
        //table에 저장
//        userRepository.save(new User());
        //.findAll() : 전체 조회
//        System.out.println(">>> " + userRepository.findAll());
        userRepository.findAll().forEach(System.out::println);

        List<User> userList = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        userList.forEach(System.out::println);

        List<User> users = userRepository.findAllById(Lists.newArrayList(1L,3L,5L));

        User user1 = new User("jack","jack@fastcampus.com");
        User user2 = new User("steve","steve@fastcampus.com");

        userRepository.saveAll(Lists.newArrayList(user1,user2));
        List<User> users1 = userRepository.findAll();
        users1.forEach(System.out::println);

//        User user = userRepository.getOne(1L);
//        System.out.println(user);
//        Optional<User> user = userRepository.findById(1L);
        User user = userRepository.findById(1L).orElse(null);
        System.out.println(user);
    }

    @Test
    void crud2(){
        userRepository.save(new User("martin","martin@fastcampus.com"));
        userRepository.save(new User("martin2","martin2@fastcampus.com"));

        userRepository.flush();

        userRepository.findAll().forEach(System.out::println);

//        long count = userRepository.count();
//        System.out.println(count);
        //delete는 null 값을 받으면 안되지만 임시로 작성함
        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
        userRepository.findAll().forEach(System.out::println);

        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L,2L)));
    }

    @Test
    void page(){

        userRepository.save(new User("martin","martin@fastcampus.com"));
        userRepository.save(new User("martin2","martin2@fastcampus.com"));

        //page는 0부터 시작함
        Page<User> users = userRepository.findAll(PageRequest.of(0,3));

        System.out.println("page : " + users);
        //전체 element의 총 갯수
        System.out.println("total elements : "+users.getTotalElements());
        //전체 page의 수
        System.out.println("total pages : " + users.getTotalPages());
        //현재 페이지에 가져온 element의 수
        System.out.println("number of elements : "+users.getNumberOfElements());
        System.out.println("sort : "+users.getSort());
        //paging을 할 때 나누는 사이즈
        System.out.println("size : "+users.getSize());

        //users 내부에 있는 user에 대한 정보 가져오
        users.getContent().forEach(System.out::println);
    }

    @Test
    void example(){
        userRepository.save(new User("martin","martin@fastcampus.com"));
        userRepository.save(new User("martin2","martin2@fastcampus.com"));
        userRepository.save(new User("kang","kang@naver.com"));

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")//matching하는 곳에서 무시
                .withMatcher("email",endsWith()); //입력받은 값으로 끝나는 값 찾기

        //probe를 넣어줌 -> 실제 entity가 아닌 가짜 entity
        Example<User> example = Example.of(new User("ma","fastcampus.com"),matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void select(){
        userRepository.save(new User("martin","martin@fastcampus.com"));
        userRepository.save(new User("martin","martin2@fastcampus.com"));
        userRepository.save(new User("kang","kang@naver.com"));

        System.out.println(userRepository.findByName("martin"));

        System.out.println(userRepository.findFirstByName("martin",Sort.by(Sort.Order.desc("id"))));

        System.out.println(userRepository.findByName("martin",PageRequest.of(0,1,Sort.by(Sort.Order.desc("id")))).getContent());
    }

    @Test
    void userHistoryTest(){
        userRepository.save(new User("martin","martin@fastcampus.com"));

        User user = new User();
        user.setEmail("martin-new@gmail.com");
        user.setName("martin-new");

        userRepository.save(user);
        user.setName("martin-new-new");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

//        List<UserHistory> result = userHistoryRepository.findBy
    }
}