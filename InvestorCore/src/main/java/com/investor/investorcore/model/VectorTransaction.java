package com.investor.investorcore.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class VectorTransaction {

    @Id
    private String idVectorTransaction;

    private float[] vector;

    @Relationship(type = "CORRESPONDE_A", direction = Relationship.Direction.OUTGOING)
    private Transactions transactions;

    public String getIdVectorTransaction() {
        return idVectorTransaction;
    }

    public void setIdVectorTransaction(String idVectorTransaction) {
        this.idVectorTransaction = idVectorTransaction;
    }

    public float[] getVector() {
        return vector;
    }

    public void setVector(float[] vector) {
        this.vector = vector;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }
}
