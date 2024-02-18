package io.github.marcusadriano.rinhaconcorrencia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("valor")
    private Long value;

    @JsonProperty("tipo")
    private TransactionType type;


}
