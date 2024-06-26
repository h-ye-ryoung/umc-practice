package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private static final Logger logger = LoggerFactory.getLogger(StoreQueryServiceImpl.class);

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {

        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }

    @Override
    public Page<Review> getUserReviewList(Long userId, Integer page) {

        User user = userRepository.findById(userId).get();

        Page<Review> UserPage = reviewRepository.findAllByUser(user, PageRequest.of(page, 10));
        return UserPage;
    }

    @Override
    public Page<Mission> getStoreMissionList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).get();

        Page<Mission> StorePage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));

        return StorePage;
    }

    @Override
    public Page<UserMission> getUserInProgressMissions(Long userId, Integer page) {

        User user = userRepository.findById(userId).get();

        Page<UserMission> userMissionPage = userMissionRepository
                .findAllByUserAndStatus(user, MissionStatus.IN_PROGRESS, PageRequest.of(page, 10));

        return userMissionPage;
    }




}
