package umc.study.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.User;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.UserMission;
import org.springframework.data.domain.Page;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    boolean existsByUserUserIdAndMissionMissionId(Long userId, Long missionId);

    Page<UserMission> findAllByUserAndStatus(User user, MissionStatus status, Pageable pageable);
}

