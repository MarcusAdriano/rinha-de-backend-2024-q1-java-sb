package io.github.marcusadriano.rinhaconcorrencia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateTransactionResponse {

    @JsonProperty("saldo")
    private Long balance;

    @JsonProperty("limite")
    private Long limit;
}
