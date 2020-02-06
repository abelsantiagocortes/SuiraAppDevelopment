package com.development.SuiraApp;


class NotificationClass {
    public String name;
    public String description;
    public String opportunityId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NotificationClass(String name, String description, String opportunityId, String userId, String type) {
        this.name = name;
        this.description = description;
        this.opportunityId = opportunityId;
        this.userId = userId;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String userId;
    public String type;


    public NotificationClass() {
    }


}



