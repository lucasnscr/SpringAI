package com.investor.investorcore.repository;

import com.investor.investorcore.model.Investiments;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface InvestimentsRepository extends ReactiveNeo4jRepository<Investiments, String> {
}
