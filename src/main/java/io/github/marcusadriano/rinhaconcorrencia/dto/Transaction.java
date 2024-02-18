package io.github.marcusadriano.rinhaconcorrencia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("valor")
    private Long value;

    @JsonProperty("realizada_em")
    private LocalDateTime createdAt;

    @JsonProperty("tipo")
    private String type;
}
