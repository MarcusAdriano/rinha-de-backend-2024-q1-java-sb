package io.github.marcusadriano.rinhaconcorrencia.service.impl;

import io.github.marcusadriano.rinhaconcorrencia.dto.CreateTransactionRequest;
import io.github.marcusadriano.rinhaconcorrencia.dto.CreateTransactionResponse;
import io.github.marcusadriano.rinhaconcorrencia.exceptions.InsufficientBalance;
import io.github.marcusadriano.rinhaconcorrencia.repository.TransactionRepository;
import io.github.marcusadriano.rinhaconcorrencia.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repo;

    public TransactionServiceImpl(final TransactionRepository repo) {
        this.repo = repo;
    }

    @Transactional
    @Override
    public Mono<CreateTransactionResponse> createTransaction(final CreateTransactionRequest request) {

        return repo.updateUserBalance(request)
            .<CreateTransactionResponse>handle((balance, sink) -> {
                if (balance.getBalance() + balance.getLimit() < 0) {
                    sink.error(new InsufficientBalance());
                    return;
                }
                sink.next(CreateTransactionResponse.builder()
                    .limit(balance.getLimit())
                    .balance(balance.getBalance())
                    .build());
            }).flatMap(response -> repo.insertTransaction(request).thenReturn(response));
    }
}
