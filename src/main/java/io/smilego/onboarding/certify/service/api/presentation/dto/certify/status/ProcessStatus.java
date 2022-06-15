package io.smilego.onboarding.certify.service.api.presentation.dto.certify.status;

import java.util.Arrays;

public enum ProcessStatus {

    WAITING_DOCUMENTS_0(0),
    WAITING_DOCUMENTS_1(1),
    DIVERGENT(2),
    DONE(3),
    CANCELED(4),
    ERROR(5),
    SENDING_MESSAGE(6),
    WAINTING_MESSAGE_CONFIRMATION(7),
    RESENDING_MESSAGE(8);

    private final int value;

    private ProcessStatus(int value) {
        this.value = value;
    }

    public static ProcessStatus of(int value) {
        return Arrays.stream(ProcessStatus.values())
            .filter(s -> s.value() == value)
            .findFirst().get();
    }

    public int value() {
        return this.value;
    }

}
