package com.example.weathertestapp.domain.model;

public class WeatherUiModel {
    private final String fullLocation;
    private final String icon;
    private final String condition;
    private final double temperature;
    private final double wind;
    private final double humidity;

    public WeatherUiModel(String fullLocation, String icon, double temperature, String condition, double wind, double humidity){
        this.fullLocation = fullLocation;
        this.icon = icon;
        this.temperature = temperature;
        this.condition = condition;
        this.wind = wind;
        this.humidity = humidity;
    }

    public String getFullLocation() {
        return fullLocation;
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
