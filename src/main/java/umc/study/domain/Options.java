package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.mapping.UserSignupAgree;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Options extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long options_id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private Boolean select_or_not;

    @OneToMany(mappedBy = "options", cascade = CascadeType.ALL)
    private List<UserSignupAgree> userSignupAgreeList = new ArrayList<>();
}
