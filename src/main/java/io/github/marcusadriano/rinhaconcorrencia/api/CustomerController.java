package io.github.marcusadriano.rinhaconcorrencia.api;

import io.github.marcusadriano.rinhaconcorrencia.dto.CreateTransactionRequest;
import io.github.marcusadriano.rinhaconcorrencia.dto.CreateTransactionResponse;
import io.github.marcusadriano.rinhaconcorrencia.dto.StatementResponse;
import io.github.marcusadriano.rinhaconcorrencia.service.StatementService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.schema.Server;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private final StatementService statementService;

    public CustomerController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping(value = "/{id}/extrato", produces = "application/json")
    public Mono<ResponseEntity<StatementResponse>> getStatement(@PathVariable final Long id) {
        return statementService.getStatement(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/{id}/transacoes", consumes = "application/json", produces = "application/json")
    public Mono<CreateTransactionResponse> createTransaction(@PathVariable final Long id, @RequestBody final CreateTransactionRequest request) {
        log.info("Request: {}", request);
        return Mono.justOrEmpty(new CreateTransactionResponse());
    }

}
