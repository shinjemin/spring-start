package com.sparta.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long memoId;
    private String username;
    private String contents;
}
