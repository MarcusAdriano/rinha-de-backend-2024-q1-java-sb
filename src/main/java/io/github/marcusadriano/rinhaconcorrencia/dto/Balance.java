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
public class Balance {

    @JsonProperty("total")
    private Long balance;

    @JsonProperty("limite")
    private Long limit;

    @Builder.Default
    @JsonProperty("data_extrato")
    private LocalDateTime createdAt = LocalDateTime.now();
}
