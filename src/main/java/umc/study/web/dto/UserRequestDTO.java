package umc.study.web.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.SocialType;
import umc.study.domain.enums.UserStatus;
import umc.study.validation.annotation.ExistCategories;

import java.time.LocalDate;
import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class JoinDto {
        @NotBlank
        String username;
        @Size(min = 5, max = 12)
        String address_1;
        @Size(min = 5, max = 12)
        String address_2;
        @NotNull
        Gender gender;
        String email;
        @ExistCategories
        List<Long> preferCategory;
    }
}
