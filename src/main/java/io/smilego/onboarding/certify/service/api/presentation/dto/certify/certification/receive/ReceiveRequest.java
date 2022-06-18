package io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive;

import io.micronaut.core.annotation.NonNull;
import io.smilego.onboarding.certify.service.api.infrastructure.util.StringUtils;

import java.io.Serializable;

public class ReceiveRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    private String cpf;

    @NonNull
    private String costumerId;

    @NonNull
    private Integer eOnboardingType;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private Document document;

    @NonNull
    private Face face;

    public String getCpf() {
        return cpf;
    }

    public String getCostumerId() {
        return costumerId;
    }

    public Integer geteOnboardingType() {
        return eOnboardingType;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Document getDocument() {
        return document;
    }

    public Face getFace() {
        return face;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCostumerId(String costumerId) {
        this.costumerId = costumerId;
    }

    public void seteOnboardingType(Integer eOnboardingType) {
        this.eOnboardingType = eOnboardingType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "cpf=[" + cpf + "], costumerId="
                + costumerId + ", eOnboardingType=" + eOnboardingType + ", name=" + name + ", email="
                + email + ", " + document.toString();
    }

    public static class Document implements Serializable {

        private static final long serialVersionUID = 1L;

        @NonNull
        private Integer documentType;

        @NonNull
        private String number;

        @NonNull
        private String issuingAgency;

        @NonNull
        private Integer issuingState;

        @NonNull
        private String imageFront;

        @NonNull
        private String imageBack;

        public Integer getDocumentType() {
            return documentType;
        }

        public String getNumber() {
            return number;
        }

        public String getIssuingAgency() {
            return issuingAgency;
        }

        public Integer getIssuingState() {
            return issuingState;
        }

        public String getImageFront() {
            return imageFront;
        }

        public String getImageBack() {
            return imageBack;
        }

        public void setDocumentType(Integer documentType) {
            this.documentType = documentType;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setIssuingAgency(String issuingAgency) {
            this.issuingAgency = issuingAgency;
        }

        public void setIssuingState(Integer issuingState) {
            this.issuingState = issuingState;
        }

        public void setImageFront(String imageFront) {
            this.imageFront = imageFront;
        }

        public void setImageBack(String imageBack) {
            this.imageBack = imageBack;
        }

        @Override
        public String toString() {
            return "documentType=[" + documentType + "], number="
                    + number + ", issuingAgency=" + issuingAgency + ", issuingState=" + issuingState + " imageFront=" + StringUtils.truncate(imageFront, 100);
        }

    }
    public static class Face implements Serializable {

        @NonNull
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(@NonNull String image) {
            this.image = image;
        }

    }

}
