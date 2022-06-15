package io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.get;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.smilego.onboarding.certify.service.api.domain.properties.annotation.Alias;
import io.smilego.onboarding.certify.service.api.domain.properties.annotation.Ignore;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.status.FaceMatchStatus;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.status.LivenessStatus;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.status.OcrStatus;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.status.ProcessStatus;

import java.io.Serializable;

public class GetProcessResponse implements Serializable {
    @Ignore
    private static final long serialVersionUID = 1L;

    @Ignore
    @JsonProperty("Id")
    private String processId;

    @Alias(value = "biometria.score", root = true)
    @JsonProperty("Score")
    private Integer score;

    @Ignore
    @JsonProperty("HasBiometry")
    private Boolean hasBiometry;

    @Ignore
    private ProcessStatus status;

    @Ignore
    private LivenessStatus livenessStatus;

    @Ignore
    private OcrStatus ocrStatus;

    @Ignore
    private FaceMatchStatus faceMatchStatus;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean hasBiometry() {
        return hasBiometry;
    }

    public void setHasBiometry(Boolean hasBiometry) {
        this.hasBiometry = hasBiometry;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }

    public LivenessStatus getLivenessStatus() {
        return livenessStatus;
    }

    public void setLivenessStatus(LivenessStatus livenessStatus) {
        this.livenessStatus = livenessStatus;
    }

    public OcrStatus getOcrStatus() {
        return ocrStatus;
    }

    public void setOcrStatus(OcrStatus ocrStatus) {
        this.ocrStatus = ocrStatus;
    }

    public FaceMatchStatus getFaceMatchStatus() {
        return faceMatchStatus;
    }

    public void setFaceMatchStatus(FaceMatchStatus faceMatchStatus) {
        this.faceMatchStatus = faceMatchStatus;
    }

    @JsonProperty("Status")
    private void setStatus(Integer value) {
        status = ProcessStatus.of(value);
    }

    @JsonProperty("Liveness")
    private void setLivenessStatus(Integer value) {
        livenessStatus = LivenessStatus.of(value);
    }

    @JsonProperty("OCRCode")
    private void setOCRCodeStatus(Integer value) {
        ocrStatus = OcrStatus.of(value);
    }

    @JsonProperty("FaceMatch")
    private void setFaceMatchStatus(Integer value) {
        faceMatchStatus = FaceMatchStatus.of(value);
    }

    public Boolean hasFinished() {
        return isSuccessfully() || isDivergent() || isCanceled() || isError();
    }

    public Boolean isSuccessfully() {
        return ProcessStatus.DONE.equals(status);
    }

    public Boolean isError() {
        return ProcessStatus.ERROR.equals(status);
    }

    public Boolean isDivergent() {
        return ProcessStatus.DIVERGENT.equals(status);
    }

    public Boolean isCanceled() {
        return ProcessStatus.CANCELED.equals(status);
    }

    public Boolean isLivenessOK() {
        return LivenessStatus.DISABLED.equals(livenessStatus) || LivenessStatus.OK.equals(livenessStatus);
    }

    public Boolean isOcrOK() {
        return OcrStatus.DISABLED.equals(ocrStatus) || OcrStatus.OK.equals(ocrStatus);
    }

    public Boolean isFaceMatchOK() {
        return FaceMatchStatus.DISABLED.equals(faceMatchStatus) || FaceMatchStatus.OK.equals(faceMatchStatus);
    }

    @Override
    public String toString() {
        return "faceMatchStatus=" + faceMatchStatus + ", hasBiometry=" + hasBiometry + ", processId=" + processId
                + ", livenessStatus=" + livenessStatus + ", ocrStatus=" + ocrStatus + ", score=" + score + ", status="
                + status + "]";
    }

}
