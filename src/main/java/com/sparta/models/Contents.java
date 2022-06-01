package com.sparta.models;

import com.sparta.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Contents extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable = false 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contents;



    // 게시글 생성
    public Contents(ContentsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.title = requestDto.getTitle();
        this.name = userDetails.getUsername();
        this.contents = requestDto.getContents();
    }

    // 게시글 수정
    public void update(ContentsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.title = requestDto.getTitle();
        this.name = userDetails.getUsername();
        this.contents = requestDto.getContents();
    }
}