package umc.study.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodPreferHandler;
import umc.study.converter.UserConverter;
import umc.study.converter.UserPreferConverter;
import umc.study.domain.FoodPrefer;
import umc.study.domain.User;
import umc.study.domain.mapping.UserFoodPrefer;
import umc.study.repository.FoodPreferRepository;
import umc.study.repository.UserRepository;
import umc.study.web.dto.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final FoodPreferRepository foodPreferRepository;

    @Override
    @Transactional
    public User joinuser(UserRequestDTO.JoinDto request) {

        User newUser = UserConverter.toUser(request);
        List<FoodPrefer> foodPreferList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodPreferRepository.findById(category).orElseThrow(() -> new FoodPreferHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<UserFoodPrefer> userFoodPreferList = UserPreferConverter.toUserFoodPreferList(foodPreferList);

        userFoodPreferList.forEach(userFoodPrefer -> {userFoodPrefer.setUser(newUser);});

        return userRepository.save(newUser);
    }
}
