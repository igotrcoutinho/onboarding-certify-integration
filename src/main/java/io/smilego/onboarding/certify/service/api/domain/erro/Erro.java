package io.smilego.onboarding.certify.service.api.domain.erro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Erro {

    private static final Set<Integer> errosDocumentacaoInvalidaCapturaFace = new HashSet<>(Arrays.asList(
        500, 502, 503, 504, 505, 507, 508, 511, 512, 514, 20506, 20807
    ));

    private static final Set<Integer> errosDocumentacaoInvalidaCapturaDocumentos = new HashSet<>(Arrays.asList(
            50016, 50019
    ));

    private static final Set<Integer> errosInconsistencia = new HashSet<>(Arrays.asList(
        20001, 20002, 20004, 20005, 20006, 20009, 20010, 20019, 20023, 20047, 20048, 20505,
        20507, 20508, 20811, 50003, 50006, 50009, 50014, 60001
    ));

    private static final Set<Integer> errosTemporarios = new HashSet<>(Arrays.asList(
        510, 10201, 10501, 10502, 10506, 10700, 20502, 20503, 20504, 30017, 40009, 42900,
        50001, 50002, 99999
    ));

    private static final Set<Integer> errosProcessoFinalizado = new HashSet<>(Arrays.asList(
        50004, 50005, 50006
    ));

    public static boolean isCapturaDocumentosInvalida(int codigo) {
        return errosDocumentacaoInvalidaCapturaDocumentos.contains(codigo);
    }

    public static boolean isCapturaFaceInvalida(int codigo) {
        return errosDocumentacaoInvalidaCapturaFace.contains(codigo);
    }

    public static boolean isInconsistente(int codigo) {
        return errosInconsistencia.contains(codigo);
    }

    public static boolean isTemporario(int codigo) {
        return errosTemporarios.contains(codigo);
    }

    public static boolean isProcessoFinalizado(Integer codigo) {
        return errosProcessoFinalizado.contains(codigo);
    }

}
