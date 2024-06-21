package umc.study.web.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import umc.study.validation.annotation.ExistStores;

import java.time.LocalDate;

public class MissionRequestDTO {
    @Getter
    public static class JoinDto {

        @ExistStores
        private Long storeId;

        @NotNull
        private Integer point_reward;
        private LocalDate dead_line;
        private String missionDescription;
        @NotNull
        private Long ownerNumber;
    }
}

