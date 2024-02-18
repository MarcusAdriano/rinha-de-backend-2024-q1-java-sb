package io.github.marcusadriano.rinhaconcorrencia.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    private UUID id;

    @Column("user_id")
    private Long userId;

    @Column
    private Long amount;

    @Column
    private String description;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String ttype;
}
