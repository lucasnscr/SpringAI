package com.investor.investorcore.repository;

import com.investor.investorcore.model.VectorTransaction;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface VectorTransactionRepository extends ReactiveNeo4jRepository<VectorTransaction,String> {
}
