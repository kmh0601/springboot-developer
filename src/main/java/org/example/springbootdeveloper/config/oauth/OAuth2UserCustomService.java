package org.example.springbootdeveloper.config.oauth;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.User;
import org.example.springbootdeveloper.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws
            OAuth2AuthenticationException {
        // 요청을 바탕으로 유저 정보를 담은 객체 반환
        OAuth2User user = super.loadUser(userRequest); // loadUser() -> 리소스 서버에서 보내주는 사용자 정보를 불러오는 메서드 -> 상속한 부모클래스의 메서드
        saveOrUpdate(user);
        return user;
    }

    // 유저가 있으면 업데이트, 없으면 유저 생성해서 리포지토리에 저장 => 직관적인 네이밍
    private User saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes(); //OAuth2User 객체의 attributes 는 Map<String,Object>형태임을 알 수 있다.
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        User user = userRepository.findByEmail(email)
                .map(entity -> entity.update(name)) // entity 가 존재한다면 update()메서드로 nickname 업데이트
                .orElse(User.builder() // 존재하지 않는다면 새로운 User 인스턴스 생성
                        .email(email)
                        .nickname(name)
                        .build());
        return userRepository.save(user); // 결과적으로 업데이트 되거나 새롭게 생성된 유저를 리포지토리에 저장함
    }
}
