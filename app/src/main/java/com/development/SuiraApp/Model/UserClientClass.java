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
    private String location;
    private String phone;
    private String linkFacebook;
    private String linkInstagram;
    private String linkSpotify;
    private String linkYoutube;
    private String description;

    public UserClientClass(){

    }

    public UserClientClass( String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.tag = new HashMap<>();
    }

    public UserClientClass() {
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


    public String getLocation() {
        return location;
    }


    public String getPhone() {
        return phone;
    }

    public String getLinkFacebook() {
        return linkFacebook;
    }

    public String getLinkInstagram() {
        return linkInstagram;
    }

    public String getLinkSpotify() {
        return linkSpotify;
    }

    public String getLinkYoutube() {
        return linkYoutube;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLinkFacebook(String linkFacebook) {
        this.linkFacebook = linkFacebook;
    }

    public void setLinkInstagram(String linkInstagram) {
        this.linkInstagram = linkInstagram;
    }

    public void setLinkSpotify(String linkSpotify) {
        this.linkSpotify = linkSpotify;
    }

    public void setLinkYoutube(String linkYoutube) {
        this.linkYoutube = linkYoutube;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
