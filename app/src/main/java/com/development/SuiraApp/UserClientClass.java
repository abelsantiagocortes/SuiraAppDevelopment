package com.development.SuiraApp;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserClientClass implements Serializable {

    private String name;
    private String lastName;
    private List<String> tag;
    private Timestamp signUpDate;
    private String linkFacebook;

    public UserClientClass( String name, String lastName, Timestamp signUpDate) {
        this.name = name;
        this.lastName = lastName;
        this.tag = new ArrayList<>();
        this.signUpDate = signUpDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Timestamp signUpDate) {
        this.signUpDate = signUpDate;
    }

    public String getLinkFacebook(){return linkFacebook;}

    public void setLinkFacebook(String linkFacebook) {
        this.linkFacebook = linkFacebook;
    }
}
