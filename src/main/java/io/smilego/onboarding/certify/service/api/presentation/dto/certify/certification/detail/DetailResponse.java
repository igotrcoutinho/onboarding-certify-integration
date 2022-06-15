package io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.detail;

import io.micronaut.core.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class DetailResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String customerId;

    private String certificationRequestType;

    private String status; //TODO procurar saber quais status - Retorna Int dentro de String

    private String cpf;

    private String name;

    private String email;

    private String gender; //TODO procurar saber os generos

    private String nacionality;

    private String fathersName;

    private String mothersName;

    private String street;

    private String number;

    private String complement;

    private String district;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    private String addressType; //TODO procurar saber os tipos

    private String face;

    private String fingerPrintFormat; //TODO ACEITA NULL - Procurar saber o tipo

    private String position; //TODO ACEITA NULL - Procurar saber o tipo

    private String fingerprint; //TODO ACEITA NULL - Procurar saber o tipo

    private String documentType; //TODO procurar saber os tipos

    private String documentNumber;

    private String issuingAgency;

    private String issuingState; //TODO procurar saber os States

    private String documentImageFront; //TODO procurar saber se eh base64

    private String documentImageBack; //TODO procurar saber se eh base64

    private Date createdDate; //TODO verificar o formato json ": "2022-05-02T11:33:13.27"

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCertificationRequestType() {
        return certificationRequestType;
    }

    public String getStatus() {
        return status;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getNacionality() {
        return nacionality;
    }

    public String getFathersName() {
        return fathersName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddressType() {
        return addressType;
    }

    public String getFace() {
        return face;
    }

    public String getFingerPrintFormat() {
        return fingerPrintFormat;
    }

    public String getPosition() {
        return position;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getIssuingAgency() {
        return issuingAgency;
    }

    public String getIssuingState() {
        return issuingState;
    }

    public String getDocumentImageFront() {
        return documentImageFront;
    }

    public String getDocumentImageBack() {
        return documentImageBack;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCertificationRequestType(String certificationRequestType) {
        this.certificationRequestType = certificationRequestType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public void setFingerPrintFormat(String fingerPrintFormat) {
        this.fingerPrintFormat = fingerPrintFormat;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setIssuingAgency(String issuingAgency) {
        this.issuingAgency = issuingAgency;
    }

    public void setIssuingState(String issuingState) {
        this.issuingState = issuingState;
    }

    public void setDocumentImageFront(String documentImageFront) {
        this.documentImageFront = documentImageFront;
    }

    public void setDocumentImageBack(String documentImageBack) {
        this.documentImageBack = documentImageBack;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "cpf=[" + cpf + "], name="
                + name + ", email="
                + email;
    }

}
