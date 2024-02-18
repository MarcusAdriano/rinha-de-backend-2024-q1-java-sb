package io.github.marcusadriano.rinhaconcorrencia.service;

import io.github.marcusadriano.rinhaconcorrencia.dto.CreateTransactionRequest;
import io.github.marcusadriano.rinhaconcorrencia.dto.CreateTransactionResponse;

public interface TransactionService {

    CreateTransactionResponse createTransaction(CreateTransactionRequest request);

}
