package io.smilego.onboarding.certify.service.api.presentation.dto.certify.status;

import java.util.Arrays;

public enum OcrStatus {

    DISABLED(0), OK(1), NOK(2);

    private final int value;

    private OcrStatus(int value) {
        this.value = value;
    }

    public static OcrStatus of(int value) {
        return Arrays.stream(OcrStatus.values())
            .filter(s -> s.value() == value)
            .findFirst().get();
    }

    public int value() {
        return this.value;
    }

}
