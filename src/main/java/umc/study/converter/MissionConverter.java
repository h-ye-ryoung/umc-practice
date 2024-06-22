package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    public static Mission toMission (MissionRequestDTO.JoinDto request, Store store) {
        return Mission.builder()
                .point_reward(request.getPoint_reward())
                .dead_line(request.getDead_line())
                .mission_description(request.getMissionDescription())
                .owner_number(request.getOwnerNumber())
                .store(store)
                .build();
    }

    public static MissionResponseDTO.JoinResultDTO toJoinResultDTO(Mission mission){
        return MissionResponseDTO.JoinResultDTO.builder()
                .missionId(mission.getMissionId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.ChallengeResultDTO toChallengeResultDTO(UserMission userMission) {
        return MissionResponseDTO.ChallengeResultDTO.builder()
                .userMissionId(userMission.getUserMissionId())
                .userId(userMission.getUser().getUserId())
                .missionId(userMission.getMission().getMissionId())
                .status(userMission.getStatus())
                .build();
    }


}
