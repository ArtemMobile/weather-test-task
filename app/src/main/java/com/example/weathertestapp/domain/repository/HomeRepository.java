package com.example.weathertestapp.domain.repository;

import com.example.weathertestapp.data.dto.WeatherResponse;

import io.reactivex.rxjava3.core.Single;

public interface HomeRepository {
    Single<WeatherResponse> getCurrentWeather(String location);
}
