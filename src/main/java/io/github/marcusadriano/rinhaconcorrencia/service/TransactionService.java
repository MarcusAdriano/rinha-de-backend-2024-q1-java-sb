package io.github.marcusadriano.rinhaconcorrencia.service;

import io.github.marcusadriano.rinhaconcorrencia.dto.CreateTransactionRequest;
import io.github.marcusadriano.rinhaconcorrencia.dto.CreateTransactionResponse;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Mono<CreateTransactionResponse> createTransaction(CreateTransactionRequest request);

}
