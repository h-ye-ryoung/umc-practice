package umc.study.service.StoreService;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    Page<Review> getReviewList(Long StoreId, Integer page);
    Page<Review> getUserReviewList(Long userId, Integer page);
    Page<Mission> getStoreMissionList(Long storeId, Integer page);
}
