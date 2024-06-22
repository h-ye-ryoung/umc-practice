package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.*;
import umc.study.apiPayload.exception.handler.MissionHandler;
import umc.study.converter.MissionConverter;
import umc.study.converter.ReviewConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.*;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.*;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;
import umc.study.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService{

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Mission joinMission(MissionRequestDTO.JoinDto request) {


        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.STORE_ID_NOT_FOUND));


        Mission newMission = MissionConverter.toMission(request, store);
        store.getMissionList().add(newMission); // Store 엔티티의 missionList에 추가

        return missionRepository.save(newMission);
    }

    @Override
    @Transactional
    public UserMission challengeMission(MissionRequestDTO.ChallengeDTO request) {

        // 하드 코딩된 유저 ID
        Long hardcodedUserId = 1L;

        // 미션이 없을때
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_ID__NOT_FOUND));

        // 미션이 도전중일 때
//        UserMission userMission = userMissionRepository.findById(request.getUserMissionId())
//                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_ALREADY_IN_PROGRESS));
        boolean isAlreadyInProgress = userMissionRepository.existsByUserMissionId(request.getUserMissionId());
        if (isAlreadyInProgress) {
            throw new MissionHandler(ErrorStatus.MISSION_ALREADY_IN_PROGRESS);
        }


        // 유저가 없을때
        User user = userRepository.findById(hardcodedUserId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));

        UserMission newUserMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();

        // Mission 엔티티의 userMissionList에 UserMission 추가
        mission.getUserMissionList().add(newUserMission);



        return userMissionRepository.save(newUserMission);
    }
}
