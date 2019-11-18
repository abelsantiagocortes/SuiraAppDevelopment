package com.development.SuiraApp.Model;

public class Aplication {

    private String applicantId;
    private String opportunityId;
    private double porcentaje;
    private String nombre;


    public Aplication() {
    }


    public void setNombre(String name) {
        this.nombre = name;
    }



    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }



    public Aplication(String applicantId, String opportunityId) {
        this.applicantId = applicantId;
        this.opportunityId = opportunityId;
    }


    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getNombre() {
        return this.nombre;
    }

}
