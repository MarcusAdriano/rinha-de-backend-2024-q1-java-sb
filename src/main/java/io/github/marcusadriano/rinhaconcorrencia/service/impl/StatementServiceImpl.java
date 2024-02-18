package io.github.marcusadriano.rinhaconcorrencia.service.impl;

import io.github.marcusadriano.rinhaconcorrencia.dto.StatementResponse;
import io.github.marcusadriano.rinhaconcorrencia.repository.StatementRepository;
import io.github.marcusadriano.rinhaconcorrencia.service.StatementService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StatementServiceImpl implements StatementService {

    private final StatementRepository statementRepository;

    public StatementServiceImpl(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }

    @Override
    public Mono<StatementResponse> getStatement(final Long userId) {
        return statementRepository.getStatement(userId);
    }
}
