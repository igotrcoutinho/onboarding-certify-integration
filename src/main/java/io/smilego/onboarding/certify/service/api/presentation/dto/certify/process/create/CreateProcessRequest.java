package io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.smilego.onboarding.certify.service.api.domain.properties.annotation.Alias;
import io.smilego.onboarding.certify.service.api.domain.properties.annotation.Ignore;
import io.smilego.onboarding.certify.service.api.domain.properties.annotation.Translate;
import io.smilego.onboarding.certify.service.api.infrastructure.util.StringUtils;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.mapping.GenderMapper;

import java.io.Serializable;
import java.time.LocalDate;

public class CreateProcessRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NonNull
    @JsonProperty("subject")
    private Propose propose;

    @NonNull
    @Alias(value = "pessoa.face", root = true)
    @JsonProperty("imagebase64")
    private String faceImage;

    @NonNull
    private boolean onlySelfie;

    private boolean withMask;

    public Propose getInformation() {
        return propose;
    }

    public void setInformation(Propose information) {
        this.propose = information;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public boolean getOnlySelfie() {
        return onlySelfie;
    }

    public void setOnlySelfie(boolean onlySelfie) {
        this.onlySelfie = onlySelfie;
    }

    public boolean getWithMask() {
        return withMask;
    }

    public void setWithMask(boolean withMask) {
        this.withMask = withMask;
    }

    @Override
    public String toString() {
        return "information=[" + propose + "], onlySelfie="
                + onlySelfie + ", withMask=" + withMask + ", faceImage=" + StringUtils.truncate(faceImage, 100);
    }

    public static class Propose implements Serializable {
        private static final long serialVersionUID = 1L;

        @NonNull
        @Alias(value = "pessoa.cpf", root = true)
        @JsonProperty("Code")
        private String document;

        @NonNull
        @Alias(value = "pessoa.nome", root = true)
        @JsonProperty("Name")
        private String name;

        @Alias(value = "pessoa.sexo", root = true)
        @Translate(GenderMapper.class)
        @JsonProperty("Gender")
        private String gender;

        @Alias(value = "pessoa.dataNascimento", root = true)
        @JsonFormat(pattern="dd-MM-yyyy")
        @JsonProperty("BirthDate")
        private LocalDate birthdate;

        @Alias(value = "pessoa.email", root = true)
        @JsonProperty("Email")
        private String email;

        @Ignore
        @JsonProperty("Phone")
        private String phone;

        public String getDocument() {
            return document;
        }

        public void setDocument(String document) {
            this.document = document;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public LocalDate getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "document=" + document + ", birthdate=" + birthdate + ", name=" + name +
                    ", gender="+ gender + ", phone=" + phone + ", email=" + email;
        }

    }
}
