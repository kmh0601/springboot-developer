package org.example.springbootdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt") // ymal 파일에서 프로퍼티값을 자바 클래스로 가져와서 사용하는 애너테이션
public class JwtProperties {
    private String issure;
    private String secretKey;
}
