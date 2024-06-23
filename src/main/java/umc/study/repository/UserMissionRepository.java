package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.mapping.UserMission;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    boolean existsByUserUserIdAndMissionMissionId(Long userId, Long missionId);
}

