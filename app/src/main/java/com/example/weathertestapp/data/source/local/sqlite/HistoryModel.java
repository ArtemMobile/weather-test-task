package com.example.weathertestapp.data.source.local.sqlite;

public class HistoryModel {
    private final String fullLocation;
    private final String condition;
    private final double wind;
    private final double humidity;
    private final String localtime;

    public HistoryModel(String fullLocation,  String condition, double wind, double humidity, String localtime){
        this.fullLocation = fullLocation;
        this.condition = condition;
        this.wind = wind;
        this.humidity = humidity;
        this.localtime = localtime;
    }

    public String getFullLocation() {
        return fullLocation;
    }

    public String getLocaltime() {
        return localtime;
    }

    public String getCondition() {
        return condition;
    }

    public double getWind() {
        return wind;
    }

    public double getHumidity() {
        return humidity;
    }
}
