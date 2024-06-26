package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.domain.enums.Gender;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

public class StoreRequestDTO {
    @Getter
    public static class JoinDto {
        @NotBlank
        String store_name;
        @Size(max = 12)
        String address;
        String description;
        Float Score;
    }

}
