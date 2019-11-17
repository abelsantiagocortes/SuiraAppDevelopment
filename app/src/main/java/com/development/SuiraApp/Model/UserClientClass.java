package com.development.SuiraApp.Model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserClientClass implements Serializable {

    private String name;
    private String lastName;
    private Map<String , TagClass> tag;
    private String linkFacebook;

    public UserClientClass( String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.tag = new HashMap<>();
    }
    public UserClientClass(){

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


    public Map<String ,TagClass> getTag() {
        return tag;
    }

    public void setTag(Map<String ,TagClass> tag) {
        this.tag = tag;
    }
    

    public String getLinkFacebook(){return linkFacebook;}

    public void setLinkFacebook(String linkFacebook) {
        this.linkFacebook = linkFacebook;
    }
}
