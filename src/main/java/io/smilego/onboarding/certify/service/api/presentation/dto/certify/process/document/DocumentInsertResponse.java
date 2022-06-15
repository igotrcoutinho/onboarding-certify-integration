package io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.smilego.onboarding.certify.service.api.domain.properties.annotation.Alias;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.status.TypedStatus;

import java.io.Serializable;
import java.time.LocalDate;

public class DocumentInsertResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("Id")
    private String id;

    @JsonIgnore
    private TypedStatus typed;

    @JsonProperty("Document")
    private DocumentInformation information;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypedStatus getType() {
        return typed;
    }

    public void setType(TypedStatus typed) {
        this.typed = typed;
    }

    @JsonProperty("Typed")
    private void setType(int typed) {
        this.typed = TypedStatus.of(typed);
    }

    public DocumentInformation getInformation() {
        return information;
    }

    public void setInformation(DocumentInformation information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "id=" + id + ", type=" + typed + ", information=[" + information + "]";
    }

    public static class DocumentInformation implements Serializable {
        private static final long serialVersionUID = 1L;

        @JsonProperty("Code")
        @Alias(value = "pessoa.cpf", root = true)
        private String code;

        @JsonProperty("Name")
        @Alias(value = "pessoa.nome", root = true)
        private String name;

        @JsonProperty("RG")
        @Alias(value = "pessoa.rg.numero", root = true)
        private String rg;

        @JsonProperty("BirthDate")
        @JsonFormat(pattern="dd/MM/yyyy")
        @Alias(value = "pessoa.dataNascimento", root = true)
        private LocalDate birthdate;

        @JsonProperty("PlaceOfBirth")
        @Alias(value = "pessoa.cidadeNatal", root = true)
        private String placeOfBirth;

        @JsonProperty("Category")
        @Alias(value = "pessoa.cnh.categoria", root = true)
        private String category;

        @JsonProperty("ExpeditionCity")
        @Alias(value = "pessoa.cnh.localExpedicao", root = true)
        private String expeditionCity;

        @JsonProperty("ExpeditionState")
        @Alias(value = "pessoa.rg.orgaoEmissor", root = true)
        private String expeditionState;

        @JsonProperty("ExpeditionDate")
        @JsonFormat(pattern="dd/MM/yyyy")
        @Alias(value = "pessoa.rg.dataEmissao", root = true)
        private LocalDate expeditionDate;

        @JsonProperty("ExpirationDate")
        @JsonFormat(pattern="dd/MM/yyyy")
        @Alias(value = "pessoa.cnh.validade", root = true)
        private LocalDate expirationDate;

        @JsonProperty("FatherName")
        @Alias(value = "pessoa.filiacao.pai.nome", root = true)
        private String fatherName;

        @JsonProperty("MotherName")
        @Alias(value = "pessoa.filiacao.mae.nome", root = true)
        private String motherName;

        @JsonProperty("FirstLicenseDate")
        @JsonFormat(pattern="dd/MM/yyyy")
        @Alias(value = "pessoa.cnh.primeiraHabilitacao", root = true)
        private LocalDate firstLicenseDate;

        @JsonProperty("RegistrationNumber")
        @Alias(value = "pessoa.cnh.registro", root = true)
        private String registrationNumber;

        @JsonProperty("Renach")
        @Alias(value = "pessoa.cnh.renach", root = true)
        private String renach;

        @JsonProperty("SecurityCode")
        @Alias(value = "pessoa.cnh.codigoSeguranca", root = true)
        private String securityCode;

        @JsonProperty("MirrorNumber")
        @Alias(value = "pessoa.cnh.numeroEspelho", root = true)
        private String mirrorNumber;

        @JsonProperty("CRM")
        @Alias(value = "pessoa.crm.numero", root = true)
        private String crm;

        @JsonProperty("Observation")
        @Alias(value = "pessoa.cnh.observacoes", root = true)
        private String observation;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRg() {
            return rg;
        }

        public void setRg(String rg) {
            this.rg = rg;
        }

        public LocalDate getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
        }

        public String getPlaceOfBirth() {
            return placeOfBirth;
        }

        public void setPlaceOfBirth(String placeOfBirth) {
            this.placeOfBirth = placeOfBirth;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getExpeditionCity() {
            return expeditionCity;
        }

        public void setExpeditionCity(String expeditionCity) {
            this.expeditionCity = expeditionCity;
        }

        public String getExpeditionState() {
            return expeditionState;
        }

        public void setExpeditionState(String expeditionState) {
            this.expeditionState = expeditionState;
        }

        public LocalDate getExpeditionDate() {
            return expeditionDate;
        }

        public void setExpeditionDate(LocalDate expeditionDate) {
            this.expeditionDate = expeditionDate;
        }

        public LocalDate getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(LocalDate expirationDate) {
            this.expirationDate = expirationDate;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public String getMotherName() {
            return motherName;
        }

        public void setMotherName(String motherName) {
            this.motherName = motherName;
        }

        public LocalDate getFirstLicenseDate() {
            return firstLicenseDate;
        }

        public void setFirstLicenseDate(LocalDate firstLicenseDate) {
            this.firstLicenseDate = firstLicenseDate;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public void setRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }

        public String getRenach() {
            return renach;
        }

        public void setRenach(String renach) {
            this.renach = renach;
        }

        public String getSecurityCode() {
            return securityCode;
        }

        public void setSecurityCode(String securityCode) {
            this.securityCode = securityCode;
        }

        public String getMirrorNumber() {
            return mirrorNumber;
        }

        public void setMirrorNumber(String mirrorNumber) {
            this.mirrorNumber = mirrorNumber;
        }

        public String getCrm() {
            return crm;
        }

        public void setCrm(String crm) {
            this.crm = crm;
        }

        public String getObservation() {
            return observation;
        }

        public void setObservation(String observation) {
            this.observation = observation;
        }

        @Override
        public String toString() {
            return "birthdate=" + birthdate + ", category=" + category + ", code=" + code
                    + ", crm=" + crm + ", expeditionCity=" + expeditionCity + ", expeditionDate=" + expeditionDate
                    + ", expeditionState=" + expeditionState + ", expirationDate=" + expirationDate + ", fatherName="
                    + fatherName + ", firstLicenseDate=" + firstLicenseDate + ", mirrorNumber=" + mirrorNumber
                    + ", motherName=" + motherName + ", name=" + name + ", observation=" + observation
                    + ", placeOfBirth=" + placeOfBirth + ", registrationNumber=" + registrationNumber + ", renach="
                    + renach + ", rg=" + rg + ", securityCode=" + securityCode;
        }

    }
}
