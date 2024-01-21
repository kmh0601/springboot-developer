package org.example.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 리프레시 토큰은 데이터베이스에도 저장하는 정보이므로 엔티티와 리포지터리를 추가해야 한다!
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "refresh_token", nullable = false)
    private String refresh_token;

    public RefreshToken(Long userId, String refreshToken) {
        this.userId = userId;
        this.refresh_token = refreshToken;
    }

    public RefreshToken update(String newRefreshToken) {
        this.refresh_token = newRefreshToken;
        return this;
    }
}
