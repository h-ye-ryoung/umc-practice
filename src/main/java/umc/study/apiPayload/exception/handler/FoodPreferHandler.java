package umc.study.apiPayload.exception.handler;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.domain.FoodPrefer;

public class FoodPreferHandler extends GeneralException {

    public FoodPreferHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
