package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.repository.UserMissionRepository;
import umc.study.validation.annotation.ExistChallengingMission;
import umc.study.web.dto.MissionRequestDTO.ChallengeDTO;

@Component
@RequiredArgsConstructor
public class ChallengingMissionValidator implements ConstraintValidator<ExistChallengingMission, ChallengeDTO> {

    private final UserMissionRepository userMissionRepository;


    @Override
    public void initialize(ExistChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ChallengeDTO request, ConstraintValidatorContext context) {
        Long userId = request.getUserId();
        Long missionId = request.getMissionId();

        return !userMissionRepository.existsByUserUserIdAndMissionMissionId(userId, missionId);
    }
}

