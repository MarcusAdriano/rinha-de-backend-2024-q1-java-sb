package io.github.marcusadriano.rinhaconcorrencia.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private Long balance;

    @Column("balance_limit")
    private Long limit;
}
