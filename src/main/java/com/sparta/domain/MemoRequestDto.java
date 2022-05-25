package com.sparta.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class MemoRequestDto {
    private String username;
    private String contents;
    private String title;
    private String pw;

}