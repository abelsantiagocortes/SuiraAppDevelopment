package com.development.SuiraApp.Model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpportunityClass implements Serializable {

    String name;
    String publisherId;
    String description;
    String endDate;
    String location;
    List<String> reference;
    List<String> tags;
    double budget;



    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public OpportunityClass() {
    }

    public OpportunityClass(String name, String description,  List<String> listTags , String id ) {
        this.publisherId = id;
        this.name = name;
        this.description = description;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
