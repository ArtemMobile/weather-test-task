package com.example.weathertestapp.domain.model;

public class WeatherUiModel {
    private String fullLocation;
    private String icon;
    private double temperature;
    private double wind;
    private double humidity;

    public WeatherUiModel(String fullLocation, String icon, double temperature, double wind, double humidity){
        this.fullLocation = fullLocation;
        this.icon = icon;
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
    }

    public String getFullLocation() {
        return fullLocation;
    }

    public void setFullLocation(String fullLocation) {
        this.fullLocation = fullLocation;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
