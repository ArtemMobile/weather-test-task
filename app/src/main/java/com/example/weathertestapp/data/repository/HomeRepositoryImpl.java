package com.example.weathertestapp.data.repository;

import static com.example.weathertestapp.data.source.remote.ApiConstants.API_KEY;
import static com.example.weathertestapp.data.source.remote.ApiConstants.KEY;

import com.example.weathertestapp.data.dto.WeatherResponse;
import com.example.weathertestapp.data.source.remote.WeatherService;
import com.example.weathertestapp.domain.repository.HomeRepository;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;

public class HomeRepositoryImpl implements HomeRepository {

    private final WeatherService weatherService;

    @Inject
    public HomeRepositoryImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public Single<WeatherResponse> getCurrentWeather(String location) {
        return weatherService.getCurrentWeatherForLocation(API_KEY, location);
    }
}
