package com.example.weathertestapp.domain.model;

public record WeatherUiModel(String fullLocation, String icon, double temperature, String condition,
                             double wind, double humidity) {
}
