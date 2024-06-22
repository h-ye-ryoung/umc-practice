package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}