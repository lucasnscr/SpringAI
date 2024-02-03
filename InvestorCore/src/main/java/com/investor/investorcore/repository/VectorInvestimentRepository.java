package com.investor.investorcore.repository;

import com.investor.investorcore.model.VectorInvestiment;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface VectorInvestimentRepository extends ReactiveNeo4jRepository<VectorInvestiment,String> {
}
