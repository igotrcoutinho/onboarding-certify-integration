package io.smilego.onboarding.certify.service.api.presentation.dto.certify.status;

import java.util.Arrays;

public enum FaceMatchStatus {

    DISABLED(0), OK(1), NOK(2);

    private final int value;

    private FaceMatchStatus(int value) {
        this.value = value;
    }

    public static FaceMatchStatus of(int value) {
        return Arrays.stream(FaceMatchStatus.values())
            .filter(s -> s.value() == value)
            .findFirst().get();
    }

    public int value() {
        return this.value;
    }

}
