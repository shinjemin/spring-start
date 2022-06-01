package com.sparta.service;

import com.sparta.models.Contents;
import com.sparta.models.ContentsRequestDto;
import com.sparta.repository.ContentsRepository;
import com.sparta.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ContentsService {

    private final com.sparta.repository.ContentsRepository ContentsRepository;

    @Transactional
    public Long update(Long id, ContentsRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Contents Contents = ContentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(Contents.getName().equals(userDetails.getUsername())) {
            Contents.update(requestDto, userDetails);
            return Contents.getId();
        }else {
            return (long)0;
        }
    }
    @Transactional
    public Long deleteContents(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Contents contents =  ContentsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        if(contents.getName().equals(userDetails.getUsername())) {
            ContentsRepository.deleteById(id);
            return id;
        }else {
            return (long)0;
        }
    }
   @Transactional
    public Contents createContents(@RequestBody ContentsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Contents Contents = new Contents(requestDto, userDetails);
        return ContentsRepository.save(Contents);
    }
    public Contents getContents(@PathVariable Long id) {
        Contents contents =  ContentsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        return contents;
    }
}