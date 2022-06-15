package io.smilego.onboarding.certify.service.api.presentation.dto.kappta;

public class Parametro {

    public static final String CODIGO_PARAMETRO_TENTATIVA_UNICA = "TENTATIVA_UNICA";

    private String codigo;

    private String valor;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "{" +
                "codigo='" + codigo + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }

}
