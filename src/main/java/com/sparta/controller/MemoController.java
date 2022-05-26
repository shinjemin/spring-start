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
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByIdDesc();
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.delete(id, requestDto);
    }


    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
           return memoService.update(id, requestDto);


    }
    @GetMapping("/api/memos/{id}")
    public Memo getMemos2(@PathVariable Long id) {
        return memoService.get(id);
    }
}