package com.investor.investorcore.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("VectorInvestiment")
public class VectorInvestiment {

    @Id
    private String idVectorInvestiment;

    private float[] vector;

    @Relationship(type = "CORRESPONDE_A", direction = Relationship.Direction.OUTGOING)
    private Investiments investiments;

    public String getIdVectorInvestiment() {
        return idVectorInvestiment;
    }

    public void setIdVectorInvestiment(String idVectorInvestiment) {
        this.idVectorInvestiment = idVectorInvestiment;
    }

    public float[] getVector() {
        return vector;
    }

    public void setVector(float[] vector) {
        this.vector = vector;
    }

    public Investiments getInvestiments() {
        return investiments;
    }

    public void setInvestiments(Investiments investiments) {
        this.investiments = investiments;
    }
}
