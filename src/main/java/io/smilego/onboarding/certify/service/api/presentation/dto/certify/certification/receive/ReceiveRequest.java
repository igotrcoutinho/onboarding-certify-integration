package io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.smilego.onboarding.certify.service.api.infrastructure.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

public class ReceiveRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cpf;

    private String cnpj;

    @NonNull
    private String costumerId;

    @NonNull
    private OnboardingType eOnboardingType;

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

    public String getCnpj() {
        return cnpj;
    }

    public String getCostumerId() {
        return costumerId;
    }

    public OnboardingType geteOnboardingType() {
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

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setCostumerId(String costumerId) {
        this.costumerId = costumerId;
    }

    public void seteOnboardingType(OnboardingType eOnboardingType) {
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

        private DocumentType documentType;

        @NonNull
        private String number;

        @NonNull
        private String issuingAgency;

        @NonNull
        private IssuingState issuingState;

        @NonNull
        private String imageFront;

        @NonNull
        private String imageBack;

        public DocumentType getDocumentType() {
            return documentType;
        }

        public String getNumber() {
            return number;
        }

        public String getIssuingAgency() {
            return issuingAgency;
        }

        public IssuingState getIssuingState() {
            return issuingState;
        }

        public String getImageFront() {
            return imageFront;
        }

        public String getImageBack() {
            return imageBack;
        }

        public void setDocumentType(DocumentType documentType) {
            this.documentType = documentType;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setIssuingAgency(String issuingAgency) {
            this.issuingAgency = issuingAgency;
        }

        public void setIssuingState(IssuingState issuingState) {
            this.issuingState = issuingState;
        }

        public void setImageFront(String imageFront) {
            this.imageFront = imageFront;
        }

        public void setImageBack(String imageBack) {
            this.imageBack = imageBack;
        }

        @JsonProperty("documentType")
        private Integer getDocumentTypeId() {
            return documentType.getType();
        }

        @Override
        public String toString() {
            return "documentType=[" + documentType + "], number="
                    + number + ", issuingAgency=" + issuingAgency + ", issuingState=" + issuingState + " imageFront=" + StringUtils.truncate(imageFront, 100);
        }

        public static enum DocumentType {

            FACE(0),
            CARTEIRA_IDENTIDADE(1),
            CARTEIRA_PROFISSIONAL(2),
            PASSAPORTE(3),
            CARTEIRA_RESERVISTA(4),
            ;

            private Integer type;

            private DocumentType(Integer type) {
                this.type = type;
            }

            public static DocumentType of(Integer type){
                return Arrays.stream(DocumentType.values())
                        .filter(s -> s.getType() == type)
                        .findFirst().get();
            }

            public Integer getType() {
                return this.type;
            }

        }

        public static enum IssuingState {

            AC(1),
            AL(2),
            AM(3),
            AP(4),
            BA(5),
            CE(6),
            DF(7),
            ES(8),
            GO(9),
            MA(10),
            MT(11),
            MS(12),
            MG(13),
            PA(14),
            PB(15),
            PR(16),
            PE(17),
            PI(18),
            RJ(19),
            RN(20),
            RS(21),
            RO(22),
            RR(23),
            SC(24),
            SP(25),
            SE(26),
            TO(27),
            ;

            private Integer id;
            private IssuingState(Integer id) {this.id = id;}

            public static IssuingState of(Integer id){
                return Arrays.stream(IssuingState.values())
                        .filter(s -> s.getId() == id)
                        .findFirst().get();
            }

            public Integer getId() {
                return this.id;
            }
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

    public static enum OnboardingType {
        CNPJ(1),
        CPF(2),
        ;

        private Integer type;

        private OnboardingType(Integer type) {
            this.type = type;
        }

        public static OnboardingType of(Integer type){
            return Arrays.stream(OnboardingType.values())
                    .filter(s -> s.getType() == type)
                    .findFirst().get();
        }

        public Integer getType() {
            return this.type;
        }

    }

}
