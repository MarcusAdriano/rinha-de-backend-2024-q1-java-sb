package io.github.marcusadriano.rinhaconcorrencia.service;

import io.github.marcusadriano.rinhaconcorrencia.dto.StatementResponse;
import reactor.core.publisher.Mono;

public interface StatementService {

    Mono<StatementResponse> getStatement(Long userId);

}
