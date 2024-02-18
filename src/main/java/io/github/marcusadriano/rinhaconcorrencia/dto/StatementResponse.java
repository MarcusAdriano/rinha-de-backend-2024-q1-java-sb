package io.github.marcusadriano.rinhaconcorrencia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatementResponse {

    @JsonProperty("saldo")
    private Balance balance;
    @JsonProperty("last_transactions")
    private List<Transaction> lastTransactions;
}
