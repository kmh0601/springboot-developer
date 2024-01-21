package org.example.springbootdeveloper.dto;

import lombok.Getter;
import lombok.Setter;

// 회원 정보를 추가하는 서비스 메서드를 작성하기 위해 사용자 정보를 담고 있는 객체 작성
// 서비스 <-> 컨트롤러 를 오가는 객체
@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;
}
