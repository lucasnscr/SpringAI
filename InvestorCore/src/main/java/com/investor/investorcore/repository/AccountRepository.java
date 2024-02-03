package com.investor.investorcore.repository;

import com.investor.investorcore.model.Account;
import com.investor.investorcore.model.FinancialProducts;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface AccountRepository extends ReactiveNeo4jRepository<Account,String> {
}
