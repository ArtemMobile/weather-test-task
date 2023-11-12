package com.example.weathertestapp.domain.model;


public class HistoryWeatherUiModel {
    private final String fullLocation;
    private final String condition;
    private final double wind;
    private final double humidity;
    private final String localtime;

    public HistoryWeatherUiModel(String fullLocation, String condition, double wind, double humidity, String localtime){
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
