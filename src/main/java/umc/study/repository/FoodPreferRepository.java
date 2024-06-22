package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.FoodPrefer;

@Repository
public interface FoodPreferRepository extends JpaRepository<FoodPrefer, Long> {
}
