package com.investor.investorcore.repository;

import com.investor.investorcore.model.Transactions;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface TransactionsRepository extends ReactiveNeo4jRepository<Transactions,String> {
}
