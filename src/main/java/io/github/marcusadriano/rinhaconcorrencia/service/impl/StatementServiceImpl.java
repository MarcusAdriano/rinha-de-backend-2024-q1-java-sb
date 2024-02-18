package io.github.marcusadriano.rinhaconcorrencia.service.impl;

import io.github.marcusadriano.rinhaconcorrencia.dto.StatementResponse;
import io.github.marcusadriano.rinhaconcorrencia.repository.StatementRepository;
import io.github.marcusadriano.rinhaconcorrencia.service.StatementService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StatementServiceImpl implements StatementService {

    private final StatementRepository repo;

    public StatementServiceImpl(StatementRepository repo) {
        this.repo = repo;
    }

    @Override
    public Mono<StatementResponse> getStatement(final Long userId) {

        final var transactions = repo.transactions(userId);
        final var balance = repo.balance(userId);

        return balance.flatMap(b -> transactions.collectList().map(t -> StatementResponse.builder()
            .balance(b)
            .lastTransactions(t)
            .build()));
    }
}
