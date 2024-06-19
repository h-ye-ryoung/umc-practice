package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


public class ReviewRequestDTO {
    @Getter
    public static class JoinDto {
        @NotBlank
        private String body;

        @NotNull
        private Float score;
    }
}

