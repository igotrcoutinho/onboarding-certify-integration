package io.smilego.onboarding.certify.service.api.presentation.dto.certify.status;

import java.util.Arrays;

public enum TypedStatus {

    FAILED(0), SUCCESS(1), DISABLED(2);

    private final int value;

    private TypedStatus(int value) {
        this.value = value;
    }

    public static TypedStatus of(int value) {
        return Arrays.stream(TypedStatus.values())
            .filter(s -> s.value() == value)
            .findFirst().get();
    }

    public int value() {
        return this.value;
    }

}
