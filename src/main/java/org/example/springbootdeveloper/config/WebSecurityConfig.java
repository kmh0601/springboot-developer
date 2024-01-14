package org.example.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailsService userService;

    // spring security  기능 비활성화 : 인증, 인가 서비스를 모든 곳에 적용하지 않는다. -> 정적 리소스에 설정함
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests() // 인증, 인가 설정
                .requestMatchers("/login","/singup","/user").permitAll() // 특정 요청과 일치하는 url에 대한 엑세스 설정
                .anyRequest().authenticated()// requestMatchers 설정한 url 외의 요청에 대한 설정 | authenticated = 인증필요
                .and()
                .formLogin() // 폼 기반 로그인
                .loginPage("/login")// 로그인 페이지 경로 설정
                .defaultSuccessUrl("/articles") // 로그인이 완료 되었을 때, 이동할 경로 설정
                .and()
                .logout() // 로그아웃 설정
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true) // 로그아웃 후에 세션을 전체 삭제할지 여부를 설정
                .and()
                .csrf().disable() // CSRF 공격을 방지하기 위해 활성화 하는게 좋음
                .build();
    }

    // 인증 관리자 관련 설정 : 사용자 정보를 가져올 서비스를 재정의 or 인증 방법 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService) // 사용자 정보 서비스 설정 : 반드시 UserDetailsService를 상속받은 객체
                .passwordEncoder(bCryptPasswordEncoder) // 비밀번호 암호화를 위하 인코더 설정
                .and()
                .build();
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
