package com.sparta.controller;

import com.sparta.domain.Memo;
import com.sparta.domain.MemoRepository;
import com.sparta.domain.MemoRequestDto;
import com.sparta.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Long a = (long)0;
        if(memo.getPw().equals(requestDto.getPw())){
            memoRepository.deleteById(id);
            return id;
        }else{
            return a;
        }
    }

    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Long a = (long)0;
        if(memo.getPw().equals(requestDto.getPw())){
            memoService.update(id, requestDto);
            return id;
        }else{
            return a;
        }

    }
    @GetMapping("/api/memos/{id}")
    public Memo getMemos2(@PathVariable Long id) {
        Memo memo =  memoRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("아이디가 존재하지 않습니다."));
        return memo;
    }
}