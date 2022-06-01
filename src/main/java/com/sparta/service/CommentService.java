package com.sparta.service;

import com.sparta.repository.CommentRepository;
import com.sparta.models.CommentRequestDto;
import com.sparta.models.Comment;
import com.sparta.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private Long memoId;
    private String username;
    private String pw;
    private String contents;

    @Transactional
    public Long update(Long id, CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Comment comment = commentRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        comment.update(id,requestDto,userDetails);
        return comment.getId();
    }


}
