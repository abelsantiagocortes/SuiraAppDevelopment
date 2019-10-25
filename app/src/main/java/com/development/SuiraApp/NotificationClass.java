package com.development.SuiraApp;


class NotificationClass implements Comparable<NotificationClass> {
    public String name;
    public String description;
    public String opportunityId;
    public String userId;
    public String type;
    public boolean seen;



    public NotificationClass(String name, String description, String opportunityId, String userId, String type, boolean seen) {
        this.name = name;
        this.description = description;
        this.opportunityId = opportunityId;
        this.userId = userId;
        this.type = type;
        this.seen = seen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean getSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
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




    public NotificationClass() {
    }



    public int compareTo(NotificationClass noti) {
        if(this.seen ==noti.getSeen()){
            return 0;
        }
        if(this.seen){
            return -1;
        }
        return 1;
    }
}



