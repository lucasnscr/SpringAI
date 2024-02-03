package com.investor.investorcore.model;

import com.investor.investorcore.enums.TypeOfProduct;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("FinancialProducts")
public class FinancialProducts {

    @Id
    private String idFinancialProducts;

    private String name;
    private TypeOfProduct typeOfProduct;
    private String terms;
    private Double interestRate;

    public String getIdFinancialProducts() {
        return idFinancialProducts;
    }

    public void setIdFinancialProducts(String idFinancialProducts) {
        this.idFinancialProducts = idFinancialProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeOfProduct getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(TypeOfProduct typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
}
