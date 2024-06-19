package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.FoodPrefer;

public interface FoodPreferRepository extends JpaRepository<FoodPrefer, Long> {
}
