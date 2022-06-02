package com.sparta.controller;

import com.sparta.models.Comment;
import com.sparta.dto.CommentRequestDto;
import com.sparta.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final com.sparta.service.CommentService commentService;
    @GetMapping("/api/get/comment/{id}")
    public Comment getComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.getComment(requestDto);
    }
    @PostMapping("/api/comment")
    public Comment createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(requestDto,userDetails);
    }

    // 게시글 수정
    @PutMapping("/api/comment/{id}")
    public Long updateComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.update(requestDto,userDetails);
    }

    @DeleteMapping("/api/comment/{id}")
    public Long deleteComment(@RequestBody CommentRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(requestDto,userDetails);
    }
}

