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
    private Timestamp signUpDate;
    private String location;
    private String phone;

    public UserClientClass( String name, String lastName, Timestamp signUpDate) {
        this.name = name;
        this.lastName = lastName;
        this.tag = new HashMap<>();
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


    public Map<String ,TagClass> getTag() {
        return tag;
    }

    public void setTag(Map<String ,TagClass> tag) {
        this.tag = tag;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Timestamp signUpDate) {
        this.signUpDate = signUpDate;
    }


}
