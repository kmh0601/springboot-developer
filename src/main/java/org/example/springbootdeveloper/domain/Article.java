package org.example.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", updatable = true)
    private String title;

    @Column(name = "content", updatable = true)
    private String content;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /* 기본 생성자 및 Getter는 애노테이션으로 자동 생성된다! */
//    protected Article() {
//
//    }
//    public Long getId() {
//        return id;
//    }
//    public String getTitle() {
//        return title;
//    }
//    public String getContent() {
//        return content;
//    }
}
