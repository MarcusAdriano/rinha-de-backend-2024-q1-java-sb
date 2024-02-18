package io.github.marcusadriano.rinhaconcorrencia.repository;

import io.github.marcusadriano.rinhaconcorrencia.dto.Balance;
import io.github.marcusadriano.rinhaconcorrencia.dto.CreateTransactionRequest;
import io.github.marcusadriano.rinhaconcorrencia.dto.TransactionType;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class TransactionRepository {

    private static final String INSERT_TRANSACTION = """
        INSERT INTO transactions
        (user_id, description, amount, ttype)
        VALUES (:user_id, :description, :amount, :ttype);
    """;

    private static final String UPDATE_USER_BALANCE = """
        UPDATE users
        SET balance = balance + :amount
        WHERE id = :user_id
        RETURNING balance, balance_limit;
    """;

    private final DatabaseClient db;

    public TransactionRepository(final DatabaseClient db) {
        this.db = db;
    }

    public Mono<Long> insertTransaction(final CreateTransactionRequest req) {
        return db.sql(INSERT_TRANSACTION)
            .bind("user_id", req.getUserId())
            .bind("description", req.getDescription())
            .bind("amount", req.getValue())
            .bind("ttype", req.getType().getValue())
            .fetch().rowsUpdated();
    }

    public Mono<Balance> updateUserBalance(final CreateTransactionRequest req) {

        final Long transactionValue;
        if (req.getType().equals(TransactionType.DEBIT)) {
            transactionValue = -req.getValue();
        } else {
            transactionValue = req.getValue();
        }

        return db.sql(UPDATE_USER_BALANCE)
            .bind("user_id", req.getUserId())
            .bind("amount", transactionValue)
            .fetch()
            .one()
            .map(row -> {
                final var balance = new Balance();
                balance.setBalance((Long) row.getOrDefault("balance", 0L));
                balance.setLimit((Long) row.getOrDefault("balance_limit", 0L));
                return balance;
            });
    }
}
