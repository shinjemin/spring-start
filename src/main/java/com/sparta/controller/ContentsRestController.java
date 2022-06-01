package com.sparta.controller;

import com.sparta.models.Contents;
import com.sparta.models.ContentsRequestDto;

import com.sparta.security.UserDetailsImpl;
import com.sparta.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContentsRestController {

    private final com.sparta.repository.ContentsRepository ContentsRepository;
    private final com.sparta.service.ContentsService ContentsService;

    // 게시글 전체 조회
    @GetMapping("/api/get/contents")
    public List<Contents> getContents() {
        return ContentsRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 특정 조회
    @GetMapping("/api/get/contents/{id}")
    public Contents getContents2(@PathVariable Long id) {
        return ContentsService.getContents(id);
    }

    // 게시글 생성
    @PostMapping("/api/contents")
    public Contents createContents(@RequestBody ContentsRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ContentsService.createContents(requestDto,userDetails);
    }

    // 게시글 수정
    @PutMapping("/api/contents/{id}")
    public Long updateContents(@PathVariable Long id, @RequestBody ContentsRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ContentsService.update(id,requestDto,userDetails);
    }

    @DeleteMapping("/api/contents/{id}")
    public Long deleteContents(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ContentsService.deleteContents(id,userDetails);
    }

}
