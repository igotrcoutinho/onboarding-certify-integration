package io.smilego.onboarding.certify.service.api.infrastructure.service;

import io.smilego.onboarding.certify.service.api.domain.properties.domain.Property;
import io.smilego.onboarding.certify.service.api.infrastructure.exception.DownloadFileException;
import io.smilego.onboarding.certify.service.api.infrastructure.log.ApplicationLogger;
import io.smilego.onboarding.certify.service.api.infrastructure.log.Log;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertRequest.Type;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class DocumentServiceImpl implements DocumentService {
    @Inject
    private FileService arquivoService;

    @Inject
    private ApplicationLogger logger;

    private static final Map<String, DocumentInsertRequest.Type> documentosSuportados = Stream.of(new Object[][] {
        { "pessoa.documento.rg.frente", DocumentInsertRequest.Type.RG_FRENTE },
        { "pessoa.documento.rg.verso", DocumentInsertRequest.Type.RG_VERSO },
        { "pessoa.documento.cnh.frente", DocumentInsertRequest.Type.CNH_FRENTE },
        { "pessoa.documento.cnh.verso", DocumentInsertRequest.Type.CNH_VERSO },
        { "pessoa.documento.identidadeMilitar.frente", DocumentInsertRequest.Type.IDENTIDADE_MILITAR_FRENTE },
        { "pessoa.documento.identidadeMilitar.verso", DocumentInsertRequest.Type.IDENTIDADE_MILITAR_VERSO },
        { "pessoa.documento.ctps.frente", DocumentInsertRequest.Type.CTPS },
        { "pessoa.documento.ctps.verso", DocumentInsertRequest.Type.CTPS },
        { "pessoa.documento.ctps.ultimoRegistro", DocumentInsertRequest.Type.CTPS },
        { "pessoa.documento.comprovanteRenda", DocumentInsertRequest.Type.COMPROVANTE_RENDA },
        { "pessoa.documento.comprovanteResidencia", DocumentInsertRequest.Type.COMPROVANTE_ENDEREÇO },
        { "pessoa.documento.rne.frente", DocumentInsertRequest.Type.OUTROS },
        { "pessoa.documento.rne.verso", DocumentInsertRequest.Type.OUTROS },
        { "pessoa.documento.passaporte.frente", DocumentInsertRequest.Type.PASSAPORTE },
        { "pessoa.documento.passaporte.verso", DocumentInsertRequest.Type.PASSAPORTE },
        { "pessoa.documento.conselhoClasse.frente", DocumentInsertRequest.Type.IDENTIDADE_CLASSE },
        { "pessoa.documento.conselhoClasse.verso", DocumentInsertRequest.Type.IDENTIDADE_CLASSE }
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (DocumentInsertRequest.Type) data[1]));

    @Override
    public List<DocumentInsertRequest> convert(List<Property> properties) {
        List<DocumentInsertRequest> requests = new ArrayList<>();

        for (Property property: properties) {
            String key = property.getKey();
            String value = property.getValue();

            if (!documentosSuportados.containsKey(key)) {
                continue;
            }

            try {
                Type type = documentosSuportados.get(key);

                String image = arquivoService.downloadAsBase64(Integer.parseInt(value))
                        .orElseThrow(() -> new DownloadFileException("Não foi possível efetuar o download do arquivo id '" + value + "'"));

                DocumentInsertRequest request = DocumentInsertRequest.of(type, image);

                requests.add(request);
            } catch (DownloadFileException e) {
                logger.error(Log.Builder.of()
                    .setHeader("Erro durante o download do documento")
                    .addRow("key", key)
                    .addRow("id", value)
                    .build(),
                    e
                );
            }
        }

        return requests;
    }
}
