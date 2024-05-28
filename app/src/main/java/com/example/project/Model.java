package com.example.project;
public class Model {
    String destination;
    int fare;

    public Model(String destination, int fare) {
        this.destination = destination;
        this.fare=fare;
    }
    public String getDest(){
        return destination;
    }
    public void setDest(){
        this.destination=destination;
    }
    public int getFare(){
        return fare;
    }public void setFare(){
        this.fare=fare;
    }
}
