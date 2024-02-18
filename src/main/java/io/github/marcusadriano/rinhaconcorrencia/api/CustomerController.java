package io.github.marcusadriano.rinhaconcorrencia.api;

import io.github.marcusadriano.rinhaconcorrencia.dto.CreateTransactionRequest;
import io.github.marcusadriano.rinhaconcorrencia.dto.CreateTransactionResponse;
import io.github.marcusadriano.rinhaconcorrencia.dto.StatementResponse;
import io.github.marcusadriano.rinhaconcorrencia.exceptions.InsufficientBalance;
import io.github.marcusadriano.rinhaconcorrencia.service.StatementService;
import io.github.marcusadriano.rinhaconcorrencia.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private final StatementService statementService;
    private final TransactionService transactionService;

    public CustomerController(final StatementService statementService, final TransactionService transactionService) {
        this.statementService = statementService;
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/{id}/extrato", produces = "application/json")
    public Mono<ResponseEntity<StatementResponse>> getStatement(@PathVariable final Long id) {
        return statementService.getStatement(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/{id}/transacoes", consumes = "application/json", produces = "application/json")
    public Mono<ResponseEntity<CreateTransactionResponse>> createTransaction(@PathVariable final Long id, @RequestBody final CreateTransactionRequest request) {

        if (!StringUtils.hasText(request.getDescription()) || request.getDescription().length() > 10) {
            return Mono.just(ResponseEntity.unprocessableEntity().build());
        }

        request.setUserId(id);
        return transactionService.createTransaction(request)
            .map(ResponseEntity::ok)
            .onErrorReturn(InsufficientBalance.class, ResponseEntity.unprocessableEntity().build())
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
