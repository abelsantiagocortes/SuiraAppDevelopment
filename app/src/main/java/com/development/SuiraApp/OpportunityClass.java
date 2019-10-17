package com.development.SuiraApp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpportunityClass {

    String name;
    String description;
    Timestamp date;
    Date startDate;
    Date endDate;
    String location;
    double budget;
    List<String> reference;
    List<String> tags;
    String userId;

    public OpportunityClass(String name, String description, Timestamp date, List<String> listTags ) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.tags =  listTags;

    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<String> getReference() {
        return reference;
    }

    public void setReference(List<String> reference) {
        this.reference = reference;
    }
}
