package com.example.weathertestapp.domain;

public record WeatherUiState<T>(T data, String error, Boolean pending) {}



