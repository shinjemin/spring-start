package com.sparta.service;

import com.sparta.repository.CommentRepository;
import com.sparta.dto.CommentRequestDto;
import com.sparta.models.Comment;
import com.sparta.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;



    @Transactional
    public Long update(CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Comment comment = commentRepository.findById(requestDto.getMemoId()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        if(comment.getUsername().equals(userDetails.getUsername())) {
            comment.update(requestDto, userDetails);
            return comment.getId();
        }else {
            return (long)0;
        }
    }
    @Transactional
    public Comment getComment(CommentRequestDto requestDto) {
        Comment comment =  commentRepository.findById(requestDto.getMemoId()).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        return comment;
    }

    @Transactional
    public Comment createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Comment comment = new Comment(requestDto, userDetails);
        return commentRepository.save(comment);
    }
    @Transactional
    public Long deleteComment(@RequestBody CommentRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Comment comment =  commentRepository.findById(requestDto.getMemoId()).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        if(comment.getUsername().equals(userDetails.getUsername())) {
            commentRepository.deleteById(requestDto.getMemoId());
            return requestDto.getMemoId();
        }else {
            return (long)0;
        }
    }

}
