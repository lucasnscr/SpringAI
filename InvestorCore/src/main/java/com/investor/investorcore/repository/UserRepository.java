package com.investor.investorcore.repository;

import com.investor.investorcore.model.User;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface UserRepository extends ReactiveNeo4jRepository<User, String> {

}
