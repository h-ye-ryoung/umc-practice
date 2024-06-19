package umc.study.converter;

import umc.study.domain.FoodPrefer;
import umc.study.domain.mapping.UserFoodPrefer;

import java.util.List;
import java.util.stream.Collectors;

public class UserPreferConverter {
    public static List<UserFoodPrefer> toUserFoodPreferList(List<FoodPrefer> foodPreferList){

        return foodPreferList.stream()
                .map(foodCategory ->
                        UserFoodPrefer.builder()
                                .foodPrefer(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }


}
