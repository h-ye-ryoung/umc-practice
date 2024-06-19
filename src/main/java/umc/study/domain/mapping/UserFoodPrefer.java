package umc.study.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.FoodPrefer;
import umc.study.domain.User;
import umc.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserFoodPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_prefer_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_prefer_id")
    private FoodPrefer foodPrefer;

    public void setUser(User user){
        if(this.user != null)
            user.getUserFoodPreferList().remove(this);
        this.user = user;
        user.getUserFoodPreferList().add(this);
    }

    public void setFoodPrefer(FoodPrefer foodPrefer){
        this.foodPrefer = foodPrefer;
    }

}
