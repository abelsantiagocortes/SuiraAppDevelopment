package com.development.SuiraApp.Model;

public class ApplicationClass {
    String opportunityId;
    String applicantId;
    String nombre;
    double porcentaje;
    OpportunityClass opp;

    public ApplicationClass(){

    }

    public ApplicationClass(String opportunityId, String applicantId, String nombre) {
        this.opportunityId = opportunityId;
        this.applicantId = applicantId;
        this.nombre = nombre;
        this.porcentaje = 0;
        this.opp = new OpportunityClass();
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public OpportunityClass getOpp() {
        return opp;
    }

    public void setOpp(OpportunityClass opp) {
        this.opp = opp;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
