package io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.smilego.onboarding.certify.service.api.infrastructure.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

// TODO: Implements a converter to document by types and properties
public class DocumentInsertRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Type type;

    @JsonProperty("imagebase64")
    private String image;

    public DocumentInsertRequest() { }

    public DocumentInsertRequest(Type type, String image) {
        this.type = type;
        this.image = image;
    }

    public static DocumentInsertRequest of(Type type, String image) {
        return new DocumentInsertRequest(type, image);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("type")
    private String getTypeId() {
        return type.id();
    }

    @Override
    public String toString() {
        return "type=" + type + ", imagem=" + StringUtils.truncate(image, 100);
    }

    public static enum Type {
        FACE("1"),
        RG("2"),
        RG_FRENTE("501"),
        RG_VERSO("502"),
        CPF("3"),
        CNH("4"),
        CNH_FRENTE("401"),
        CNH_VERSO("402"),
        CRM_FRENTE("503"),
        CRM_VERSO("504"),
        IDENTIDADE_MILITAR_FRENTE("505"),
        IDENTIDADE_MILITAR_VERSO("506"),
        COMPROVANTE_RENDA("5"),
        COMPROVANTE_ENDEREÇO("6"),
        IMPOSTO_RENDA("7"),
        CERTIDÃO_CASAMENTO("8"),
        CERTIDÃO_ÓBITO("9"),
        PASSAPORTE("12"),
        CARTÃO_CNPJ("13"),
        CONTRATO_SOCIAL("14"),
        CARTEIRA_TRABALHO("20"),
        PAC("21"),
        CTPS("22"),
        IDENTIDADE_CLASSE("24"),
        CERTIDÃO_NASCIMENTO("25"),
        TAD("107"),
        ASSINATURA_DIGITAL("112"),
        TERMO_CONSENTIMENTO("114"),
        VOUCHER("115"),
        PROPOSTA("300"),
        PROPOSTA_CONTRATO("301"),
        PROPOSTA_SEGURO("302"),
        PROPOSTA_GARANTIA("303"),
        OUTROS_5("994"),
        OUTROS_4("995"),
        OUTROS_3("996"),
        OUTROS_2("997"),
        OUTROS_1("998"),
        OUTROS("999");

        private String id;

        private Type(String id) {
            this.id = id;
        }

        public static Type of(String id) {
            return Arrays.stream(Type.values())
                .filter(s -> s.id() == id)
                .findFirst().get();
        }
    
        public String id() {
            return this.id;
        }

    }

}

