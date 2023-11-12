package com.example.weathertestapp.data.repository;

import static com.example.weathertestapp.data.source.remote.ApiConstants.API_KEY;
import com.example.weathertestapp.data.dto.WeatherResponse;
import com.example.weathertestapp.data.source.remote.WeatherService;
import com.example.weathertestapp.domain.repository.SearchRepository;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;

public class SearchRepositoryImpl implements SearchRepository {

    private final WeatherService weatherService;

    @Inject
    public SearchRepositoryImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public Single<WeatherResponse> searchWeatherByCityName(String city) {
        return weatherService.getCurrentWeatherForLocation(API_KEY, city);
    }
}
