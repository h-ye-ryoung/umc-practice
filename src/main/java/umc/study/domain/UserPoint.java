package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserPoint extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //JPA가 통신을 하는 DBMS의 방식을 따른다
        private Long user_point_id;

        @Column(nullable = true)
        private Long current_point;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "userId")
        private User user;
}
