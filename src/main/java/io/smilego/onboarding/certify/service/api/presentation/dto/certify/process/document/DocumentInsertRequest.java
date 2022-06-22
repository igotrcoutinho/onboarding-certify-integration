package io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.smilego.onboarding.certify.service.api.infrastructure.util.StringUtils;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveRequest;

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
        RG_FRENTE(TypeSide.FRONT, ReceiveRequest.Document.DocumentType.CARTEIRA_IDENTIDADE),
        RG_VERSO(TypeSide.BACK, ReceiveRequest.Document.DocumentType.CARTEIRA_IDENTIDADE),
        IDENTIDADE_MILITAR_FRENTE(TypeSide.FRONT, ReceiveRequest.Document.DocumentType.CARTEIRA_RESERVISTA),
        IDENTIDADE_MILITAR_VERSO(TypeSide.BACK, ReceiveRequest.Document.DocumentType.CARTEIRA_RESERVISTA),
        PASSAPORTE_FRENTE(TypeSide.FRONT, ReceiveRequest.Document.DocumentType.PASSAPORTE),
        PASSAPORTE_VERSO(TypeSide.BACK, ReceiveRequest.Document.DocumentType.PASSAPORTE),
        CTPS_FRENTE(TypeSide.FRONT, ReceiveRequest.Document.DocumentType.CARTEIRA_PROFISSIONAL),
        CTPS_VERSO(TypeSide.BACK, ReceiveRequest.Document.DocumentType.CARTEIRA_PROFISSIONAL),
        ;

        private TypeSide typeSide;
        private ReceiveRequest.Document.DocumentType documentType;

        private Type(TypeSide typeSide, ReceiveRequest.Document.DocumentType documentType) {
            this.typeSide = typeSide;
            this.documentType = documentType;
        }

        public TypeSide typeSide() {
            return this.typeSide;
        }

        public ReceiveRequest.Document.DocumentType documentType() {
            return this.documentType;
        }
    }

}

