package io.smilego.onboarding.certify.service.api.presentation.dto.kappta;

public class IdBiometria {
    private final static String NOME_MOTOR = "UNICO";
    public final static String CHAVE_CAMPO_BIOMETRIA_CONSULTA = "biometria.consulta";

    private String motor;
    private String id;
    private boolean tipificacao;

    public IdBiometria(String id, boolean tipificacao) {
        this.motor = NOME_MOTOR;
        this.id = id;
        this.tipificacao = tipificacao;
    }

    public static IdBiometria of(String id, boolean tipificacao) {
        return new IdBiometria(id, tipificacao);
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isTipificacao() {
        return tipificacao;
    }

    public void setTipificacao(boolean tipificacao) {
        this.tipificacao = tipificacao;
    }
}
