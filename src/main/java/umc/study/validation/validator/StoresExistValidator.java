package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.FoodPreferRepository;
import umc.study.repository.StoreRepository;
import umc.study.validation.annotation.ExistCategories;
import umc.study.validation.annotation.ExistStores;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StoresExistValidator implements ConstraintValidator<ExistStores, Long> {
    private final StoreRepository storeRepository;

    @Override
    public void initialize(ExistStores constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {

        boolean isValid = storeRepository.existsById(storeId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_ID_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;

    }
}
