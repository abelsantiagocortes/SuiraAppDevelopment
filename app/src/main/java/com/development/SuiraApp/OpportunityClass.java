package com.development.SuiraApp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpportunityClass {

    String name;
    String publisherId;
    String description;
    Timestamp date;
    Date startDate;
    Date endDate;
    String location;
    List<String> reference;
    List<String> tags;


    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public OpportunityClass(String name, String description, Timestamp date, List<String> listTags , String id ) {
        this.publisherId = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.tags =  listTags;


    }

    public String getPublisherId() {
        return publisherId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public List<String> getReference() {
        return reference;
    }

    public void setReference(List<String> reference) {
        this.reference = reference;
    }
}
