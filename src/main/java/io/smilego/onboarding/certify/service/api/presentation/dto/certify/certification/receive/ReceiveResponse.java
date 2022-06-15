package io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReceiveResponse {

    private String id;

    private String costumerId;

    private String certificationRequestType;//TODO procurar saber

    private String certificationRequestStatus;//TODO procurar saber

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(String costumerId) {
        this.costumerId = costumerId;
    }

    public String getCertificationRequestType() {
        return certificationRequestType;
    }

    public void setCertificationRequestType(String certificationRequestType) {
        this.certificationRequestType = certificationRequestType;
    }

    public String getCertificationRequestStatus() {
        return certificationRequestStatus;
    }

    public void setCertificationRequestStatus(String certificationRequestStatus) {
        this.certificationRequestStatus = certificationRequestStatus;
    }

}
