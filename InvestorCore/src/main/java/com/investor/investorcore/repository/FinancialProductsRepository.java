package com.investor.investorcore.repository;

import com.investor.investorcore.model.FinancialProducts;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface FinancialProductsRepository extends ReactiveNeo4jRepository<FinancialProducts,String> {
}
