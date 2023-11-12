package com.example.weathertestapp.domain.model;

public record SearchWeatherUiModel(String fullLocation, String icon, double temperature,
                                   String condition, double wind, double humidity, String localtime,
                                   String locationLatLon) {
}
