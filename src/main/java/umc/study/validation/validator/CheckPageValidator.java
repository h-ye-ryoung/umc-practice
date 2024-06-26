package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.ExistStores;

@Component
@RequiredArgsConstructor
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {
    @Override
    public void initialize(CheckPage constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        if (page == null || page < 0) {
            return false;
        }
        return true;
    }
}
