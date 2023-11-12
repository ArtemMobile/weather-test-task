package com.example.weathertestapp.domain.model;

public class SearchWeatherUiModel {
    private final String fullLocation;
    private final String icon;
    private final String condition;
    private final double temperature;
    private final double wind;
    private final double humidity;
    private final String localtime;
    private final String locationLatLon;

    public SearchWeatherUiModel(String fullLocation, String icon, double temperature, String condition, double wind, double humidity, String localtime, String locationLatLon){
        this.fullLocation = fullLocation;
        this.icon = icon;
        this.temperature = temperature;
        this.condition = condition;
        this.wind = wind;
        this.humidity = humidity;
        this.localtime = localtime;
        this.locationLatLon = locationLatLon;
    }

    public String getFullLocation() {
        return fullLocation;
    }

    public String getLocaltime() {
        return localtime;
    }

    public String getLocationLatLon() {
        return locationLatLon;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }

    public String getIcon() {
        return icon;
    }

    public double getWind() {
        return wind;
    }

    public double getHumidity() {
        return humidity;
    }
}
