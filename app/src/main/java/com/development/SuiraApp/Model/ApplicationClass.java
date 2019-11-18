package com.development.SuiraApp.Model;

public class ApplicationClass {
    String opportunityId;
    String applicantId;

    public ApplicationClass(String opportunityId, String applicantId) {
        this.opportunityId = opportunityId;
        this.applicantId = applicantId;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setPublisherId(String opportunityId) {
        this.opportunityId= opportunityId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
}
