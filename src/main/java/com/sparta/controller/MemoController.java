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

    @PostMapping("/api/ memos") //api에서 post 요청시 memoRepository.save를 호출하고 결과 값 리턴
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }

    @GetMapping("/api/get/memos")//api에서 get 요청시 db 모든 정보를 id 내림차순으로 리턴
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByIdDesc();
    }

    @DeleteMapping("/api/memos/{id}")//api에서 delete 요청시 memoService.delete 호출하고 결과 값 리턴
    public Long deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.delete(id, requestDto);
    }


    @PutMapping("/api/memos/{id}") //api에서 put 요청시 memoService.update 호출하고 결과값 리턴
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
           return memoService.update(id, requestDto);


    }
    @GetMapping("/api/get/memos/{id}") //api에서 get 요청시 memoService.get 호출 후 결과 값 리턴
    public Memo getMemos2(@PathVariable Long id) {
        return memoService.get(id);
    }
}