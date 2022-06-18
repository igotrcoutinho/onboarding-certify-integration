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
        { "pessoa.documento.rg.frente", Type.RG_FRENTE },
        { "pessoa.documento.rg.verso", Type.RG_VERSO },
        { "pessoa.documento.cnh.frente", Type.CNH_FRENTE },
        { "pessoa.documento.cnh.verso", Type.CNH_VERSO },
        { "pessoa.documento.identidadeMilitar.frente", Type.IDENTIDADE_MILITAR_FRENTE },
        { "pessoa.documento.identidadeMilitar.verso", Type.IDENTIDADE_MILITAR_VERSO },
        { "pessoa.documento.ctps.frente", Type.CTPS_FRENTE},
        { "pessoa.documento.ctps.verso", Type.CTPS_FRENTE},
        { "pessoa.documento.ctps.ultimoRegistro", Type.CTPS_FRENTE},
        { "pessoa.documento.comprovanteRenda", Type.COMPROVANTE_RENDA },
        { "pessoa.documento.comprovanteResidencia", Type.COMPROVANTE_ENDERECO},
        { "pessoa.documento.rne.frente", Type.OUTROS_FRENTE },
        { "pessoa.documento.rne.verso", Type.OUTROS_VERSO },
        { "pessoa.documento.passaporte.frente", Type.PASSAPORTE_FRENTE},
        { "pessoa.documento.passaporte.verso", Type.PASSAPORTE_VERSO},
        { "pessoa.documento.conselhoClasse.frente", Type.IDENTIDADE_CLASSE_FRENTE},
        { "pessoa.documento.conselhoClasse.verso", Type.IDENTIDADE_CLASSE_VERSO}
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
                String documentNumber = getDocumentNumber(properties, key);

                String image = arquivoService.downloadAsBase64(Integer.parseInt(value))
                        .orElseThrow(() -> new DownloadFileException("Não foi possível efetuar o download do arquivo id '" + value + "'"));

                DocumentInsertRequest request = DocumentInsertRequest.of(type, image, documentNumber);

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

    private String getDocumentNumber(List<Property> properties, String key){
        for (Property property: properties) {
            if(property.getKey().equals(key)){
                return property.getValue();
            }
        }
        return "";
    }



}
