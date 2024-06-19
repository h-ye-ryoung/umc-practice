package umc.study.converter;

import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;
import umc.study.web.dto.UserRequestDTO;
import umc.study.web.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.JoinDto request, Region region){

        return Store.builder()
                .store_name(request.getStore_name())
                .address(request.getAddress())
                .score(request.getScore())
                .description(request.getDescription())
                .region(region)
                .build();
    }

    public static StoreResponseDTO.JoinResultDTO toJoinResultDTO(Store store){
        return StoreResponseDTO.JoinResultDTO.builder()
                .storeid(store.getStore_id())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
