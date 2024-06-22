package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.UserMissionRepository;
import umc.study.validation.annotation.ExistChallengingMission;

@Component
@RequiredArgsConstructor
public class ChallengingMissionValidator implements ConstraintValidator<ExistChallengingMission, Long> {

    private final UserMissionRepository userMissionRepository;

    @Override
    public void initialize(ExistChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }



    private Long hardCodeUserId = 1L; // 하드 코딩된 유저 ID
    @Override
    public boolean isValid(Long userMissionId, ConstraintValidatorContext context) {
        boolean isValid = userMissionRepository.existsByUserMissionId(userMissionId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_IN_PROGRESS.toString()).addConstraintViolation();
        }

        return isValid;

    }

}
