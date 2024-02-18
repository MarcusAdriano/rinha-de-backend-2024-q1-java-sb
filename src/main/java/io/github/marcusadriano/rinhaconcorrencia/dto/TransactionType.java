package io.github.marcusadriano.rinhaconcorrencia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TransactionType {

    @JsonProperty("c")
    DEBIT("d"),

    @JsonProperty("d")
    CREDIT("c");

    private final String value;

    TransactionType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
