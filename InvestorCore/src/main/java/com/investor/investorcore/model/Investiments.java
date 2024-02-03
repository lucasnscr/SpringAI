package com.investor.investorcore.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;

@Node("Investiments")
public class Investiments {

    @Id
    private String idInvestiments;
    @Relationship(type = "ASSOCIADO_A", direction = Relationship.Direction.OUTGOING)
    private Account account;
    private String typeInvestiments;
    private Double valueInvestiments;
    private LocalDate dateInvestiment;
    private Double profitExpected;

    public String getIdInvestiments() {
        return idInvestiments;
    }

    public void setIdInvestiments(String idInvestiments) {
        this.idInvestiments = idInvestiments;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTypeInvestiments() {
        return typeInvestiments;
    }

    public void setTypeInvestiments(String typeInvestiments) {
        this.typeInvestiments = typeInvestiments;
    }

    public Double getValueInvestiments() {
        return valueInvestiments;
    }

    public void setValueInvestiments(Double valueInvestiments) {
        this.valueInvestiments = valueInvestiments;
    }

    public LocalDate getDateInvestiment() {
        return dateInvestiment;
    }

    public void setDateInvestiment(LocalDate dateInvestiment) {
        this.dateInvestiment = dateInvestiment;
    }

    public Double getProfitExpected() {
        return profitExpected;
    }

    public void setProfitExpected(Double profitExpected) {
        this.profitExpected = profitExpected;
    }
}
