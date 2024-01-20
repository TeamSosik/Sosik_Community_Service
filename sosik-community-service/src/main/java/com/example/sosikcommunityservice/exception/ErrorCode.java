package com.example.sosikcommunityservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾지 못했습니다."),
    UNAUTHORIZED_UPDATE(HttpStatus.NOT_FOUND, "권한이 없어 수정할 수 없습니다.");

    private HttpStatus status;
    private String message;
}
