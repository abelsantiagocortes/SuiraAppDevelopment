package com.development.SuiraApp.Model;


public class NotificationClass implements Comparable<NotificationClass> {
    private String name;
    private String description;
    private String opportunityId;
    private String userId;
    private String type;
    private String publisherName;
    private String publisherId;
    private boolean seen;

    public NotificationClass(String name, String description, String opportunityId, String userId, String type, boolean seen, String publisherName , String publisherIdd) {
        this.name = name;
        this.description = description;
        this.opportunityId = opportunityId;
        this.userId = userId;
        this.type = type;
        this.seen = seen;
        this.publisherName = publisherName;
        this.publisherId = publisherIdd;
    }

    public void print(){
        System.out.println("Name: "+this.name);
        System.out.println("Description: "+this.description);
        System.out.println("Oportunity Id: "+this.opportunityId);
        System.out.println("User Id: "+this.userId);
        System.out.println("type: "+this.type);
        System.out.println("Seen: "+this.seen);
        System.out.println("publisherName: "+this.publisherName);
        System.out.println("Publisher Id: "+this.publisherId);

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

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String pName) {
        this.publisherName = pName;
    }


    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String pID) {
        this.publisherId = pID;
    }

    public NotificationClass() {
    }


    /**
     *
     * Camparator function to sort the arraylist depending on "seen" atribute
     * @param noti Notification to be compared with
     * @return 0 if equals, 1 if greater than, -1 otherwise
     */
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



