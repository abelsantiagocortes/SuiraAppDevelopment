package com.development.SuiraApp.Model;

public class ApplicationClass {
    String opportunityId;
    String applicantId;
    String nombre;
    double porcentaje;

    public ApplicationClass(String opportunityId, String applicantId, String nombre) {
        this.opportunityId = opportunityId;
        this.applicantId = applicantId;
        this.nombre = nombre;
        this.porcentaje = 0;
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

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
