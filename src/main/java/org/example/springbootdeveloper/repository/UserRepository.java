package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    // email로 사용자 정보를 찾아오는 메서드 추가!(커스텀 메서드)
    // 스프링데이터 JPA(JpaRepository)는 메서드 규칙에 맞게 메서드를 선언하면 자동으로 쿼리를 생성해준다.
    /*
    * From users
    * WHERE email = #{email}
    * */
}
