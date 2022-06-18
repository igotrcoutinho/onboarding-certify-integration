package io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.smilego.onboarding.certify.service.api.infrastructure.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

// TODO: Implements a converter to document by types and properties
public class DocumentInsertRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String number;
    private Type type;

    @JsonProperty("imagebase64")
    private String image;

    public DocumentInsertRequest() { }

    public DocumentInsertRequest(Type type, String image, String number) {
        this.type = type;
        this.image = image;
        this.number = number;
    }

    public static DocumentInsertRequest of(Type type, String image, String number) {
        return new DocumentInsertRequest(type, image, number);
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("type")
    private String getTypeId() {
        return type.id();
    }

    public String getTypeKey() {
        return type.key();
    }

    @Override
    public String toString() {
        return "type=" + type + ", imagem=" + StringUtils.truncate(image, 100);
    }

    public static enum TypeSide {
        FRONT,
        BACK,
        ;
    }
    public static enum Type {
        FACE("1", "", TypeSide.FRONT),
        RG("2", "pessoa.documento.rg.numero", TypeSide.FRONT),
        RG_FRENTE("501", "pessoa.documento.rg.numero", TypeSide.FRONT),
        RG_VERSO("501", "pessoa.documento.rg.numero", TypeSide.BACK),
        CPF("3", "pessoa.documento.cpf.numero", TypeSide.FRONT),
        CNH("4", "pessoa.documento.cnh.numero", TypeSide.FRONT),
        CNH_FRENTE("401", "pessoa.documento.cnh.numero", TypeSide.FRONT),
        CNH_VERSO("401", "pessoa.documento.cnh.numero", TypeSide.BACK),
        CRM_FRENTE("503", "", TypeSide.FRONT),
        CRM_VERSO("503", "", TypeSide.BACK),
        IDENTIDADE_MILITAR_FRENTE("505", "pessoa.documento.identidadeMilitar.numero", TypeSide.FRONT),
        IDENTIDADE_MILITAR_VERSO("505", "pessoa.documento.identidadeMilitar.numero", TypeSide.BACK),
        COMPROVANTE_RENDA("5", "pessoa.documento.comprovanteRenda.numero", TypeSide.FRONT),
        COMPROVANTE_ENDERECO("6", "pessoa.documento.comprovanteResidencia.numero", TypeSide.FRONT),
        IMPOSTO_RENDA("7", "", TypeSide.FRONT),
        CERTIDÃO_CASAMENTO("8", "", TypeSide.FRONT),
        CERTIDÃO_ÓBITO("9", "", TypeSide.FRONT),
        PASSAPORTE_FRENTE("12", "pessoa.documento.passaporte.numero", TypeSide.FRONT),
        PASSAPORTE_VERSO("12", "pessoa.documento.passaporte.numero", TypeSide.BACK),
        CARTÃO_CNPJ("13", "", TypeSide.FRONT),
        CONTRATO_SOCIAL("14", "", TypeSide.FRONT),
        CARTEIRA_TRABALHO("20", "", TypeSide.FRONT),
        PAC("21", "", TypeSide.FRONT),
        CTPS_FRENTE("22", "pessoa.documento.ctps", TypeSide.FRONT),
        CTPS_VERSO("22", "pessoa.documento.ctps", TypeSide.BACK),
        IDENTIDADE_CLASSE_FRENTE("24", "pessoa.documento.conselhoClasse.numero", TypeSide.FRONT),
        IDENTIDADE_CLASSE_VERSO("24", "pessoa.documento.conselhoClasse.numero", TypeSide.BACK),
        CERTIDÃO_NASCIMENTO("25", "", TypeSide.FRONT),
        TAD("107", "", TypeSide.FRONT),
        ASSINATURA_DIGITAL("112", "", TypeSide.FRONT),
        TERMO_CONSENTIMENTO("114", "", TypeSide.FRONT),
        VOUCHER("115", "", TypeSide.FRONT),
        PROPOSTA("300", "", TypeSide.FRONT),
        PROPOSTA_CONTRATO("301", "", TypeSide.FRONT),
        PROPOSTA_SEGURO("302", "", TypeSide.FRONT),
        PROPOSTA_GARANTIA("303", "", TypeSide.FRONT),
        OUTROS("999", "pessoa.documento.outros.numero", TypeSide.FRONT),
        OUTROS_FRENTE("999", "pessoa.documento.outros.numero", TypeSide.FRONT),
        OUTROS_VERSO("999", "pessoa.documento.outros.numero", TypeSide.BACK),
        ;

        private String id;
        private String key;
        private TypeSide typeSide;

        private Type(String id, String key, TypeSide typeSide) {
            this.id = id;
            this.key = key;
            this.typeSide = typeSide;
        }

        public static Type of(String id) {
            return Arrays.stream(Type.values())
                .filter(s -> s.id() == id)
                .findFirst().get();
        }
    
        public String id() {
            return this.id;
        }
        public String key() {
            return this.key;
        }

        public TypeSide typeSide() {
            return this.typeSide;
        }

    }

}

