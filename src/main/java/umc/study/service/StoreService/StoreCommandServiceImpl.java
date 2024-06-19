package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodPreferHandler;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.converter.StoreConverter;
import umc.study.converter.UserConverter;
import umc.study.converter.UserPreferConverter;
import umc.study.domain.FoodPrefer;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.domain.mapping.UserFoodPrefer;
import umc.study.repository.FoodPreferRepository;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository;
import umc.study.repository.UserRepository;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements  StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store joinStore(StoreRequestDTO.JoinDto request, Long region_id) {

        Region region = regionRepository.findById(region_id)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.REGION_ID_NOT_FOUND));


        Store newStore = StoreConverter.toStore(request, region);

        return storeRepository.save(newStore);
    }
}
