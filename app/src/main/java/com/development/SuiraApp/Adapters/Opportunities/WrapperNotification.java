package com.development.SuiraApp.Adapters.Opportunities;

import com.development.SuiraApp.Model.NotificationClass;

//esta clase existe para poder contener una notificationClass con su key y poder ordenaras
public class WrapperNotification implements Comparable<WrapperNotification>{
    public NotificationClass not;
    public String key;

    public WrapperNotification(NotificationClass nc , String k){
        this.not = nc;
        this.key = k;
    }

    public void setKey(String k){
        this.key = k;

    }
    public void setNot(NotificationClass nc){
        this.not = nc;
    }

    public NotificationClass getNot(){
        return this.not;
    }

    public String getKey(){
        return this.key;
    }



    public int compareTo(WrapperNotification wn) {
        if(this.not.getSeen() ==wn.not.getSeen()){
            return 0;
        }
        if(this.not.getSeen()){
            return -1;
        }
        return 1;
    }



}
