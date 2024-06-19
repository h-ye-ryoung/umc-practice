package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.ReviewHandler;
import umc.study.apiPayload.exception.handler.UserHandler;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;
import umc.study.repository.UserRepository;
import umc.study.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Review joinReview(ReviewRequestDTO.JoinDto request, Long store_id) {
        Store store = storeRepository.findById(store_id)
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.STORE_ID_NOT_FOUND));

        //유저id 하드코딩 (임의설정)
        Long userId = 1L;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));


        Review newReview = ReviewConverter.toReview(request, store, user);
        return reviewRepository.save(newReview);
    }
}
