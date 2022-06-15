package io.smilego.onboarding.certify.service.api.presentation.dto.certify.identification;

import java.math.BigDecimal;

public class ScoreResponse {

    private BigDecimal score;

    private Boolean foundOtherRegister;

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Boolean getFoundOtherRegister() {
        return foundOtherRegister;
    }

    public void setFoundOtherRegister(Boolean foundOtherRegister) {
        this.foundOtherRegister = foundOtherRegister;
    }

}
