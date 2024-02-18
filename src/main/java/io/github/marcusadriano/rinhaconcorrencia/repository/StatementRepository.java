package io.github.marcusadriano.rinhaconcorrencia.repository;

import io.github.marcusadriano.rinhaconcorrencia.dto.Balance;
import io.github.marcusadriano.rinhaconcorrencia.dto.StatementResponse;
import io.github.marcusadriano.rinhaconcorrencia.dto.Transaction;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public class StatementRepository {

    private static final String GET_TRANSACTIONS_BY_USER = """
        SELECT t.description, t.amount, t.created_at, t.ttype
        FROM transactions t
        WHERE user_id = :user_id ORDER BY created_at DESC LIMIT :query_limit;
        """;

    private static final String GET_USER_BALANCE = """
        SELECT id, balance, balance_limit
        FROM users
        WHERE id = :user_id;
        """;

    private static final Integer DEFAULT_QUERY_LIMIT = 10;

    private final DatabaseClient db;

    public StatementRepository(final DatabaseClient db) {
        this.db = db;
    }

    public Mono<Balance> balance(final Long userId) {
        return db.sql(GET_USER_BALANCE)
            .bind("user_id", userId)
            .fetch()
            .one()
            .map(row -> {
                final var balance = new Balance();
                balance.setBalance((Long) row.getOrDefault("balance", 0L));
                balance.setLimit((Long) row.getOrDefault("balance_limit", 0L));
                return balance;
            });
    }

    public Flux<Transaction> transactions(final Long userId) {
        return db.sql(GET_TRANSACTIONS_BY_USER)
            .bind("user_id", userId)
            .bind("query_limit", DEFAULT_QUERY_LIMIT)
            .fetch()
            .all()
            .map(row -> new Transaction(
                (String) row.getOrDefault("description", ""),
                (Long) row.getOrDefault("amount", 0L),
                (LocalDateTime) row.getOrDefault("created_at", ""),
                (String) row.getOrDefault("ttype", "")
            ));
    }
}
