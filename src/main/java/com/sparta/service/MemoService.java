package com.sparta.service;

import com.sparta.domain.Memo;
import com.sparta.domain.MemoRepository;
import com.sparta.domain.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;
    private String username;
    private String contents;
    private String pw;
    private String title;

    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(memo.getPw().equals(requestDto.getPw())) {
            memo.update(requestDto);
            return memo.getId();
        }else {
            return (long)0;
        }

    }

    @Transactional
    public Long delete(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(memo.getPw().equals(requestDto.getPw())){
            memoRepository.deleteById(id);
            return id;
        }
        else {
            return (long)0;
        }
    }
    @Transactional
    public Memo get(@PathVariable Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        return memo;
    }


}