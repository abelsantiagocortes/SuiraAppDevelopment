package com.development.SuiraApp.Model;

public class TagClass {
    private double rating;
    private int count;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TagClass()
    {
        count = 1;
    }

    public TagClass(double pRating , int pCount ){
        rating = pRating;
        count = pCount;

    }

    public double calculateRating(double newRating){
        rating = rating*count;
        count++;
        rating += newRating;
        rating = rating/count;
        return rating;
    }

}
