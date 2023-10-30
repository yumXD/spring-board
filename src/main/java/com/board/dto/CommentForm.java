package com.board.dto;

import jakarta.validation.constraints.NotEmpty;

public record CommentForm(
        @NotEmpty(message = "내용은 필수항목입니다.")
        String content
) {
}
