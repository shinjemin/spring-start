package com.sparta.models;

import com.sparta.dto.CommentRequestDto;
import com.sparta.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;



    @Column(nullable = false)
    private Long memoId;

    @Column(nullable = false)
    private String username;


    @Column(nullable = false)
    private String contents;



    public Comment(CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        this.memoId = requestDto.getMemoId();
        this.contents = requestDto.getContents();
        this.username = userDetails.getUsername();
    }
    public void update(CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        this.memoId = requestDto.getMemoId();
        this.contents = requestDto.getContents();
        this.username = userDetails.getUsername();
    }
}
