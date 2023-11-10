package com.example.weathertestapp.domain.usecase;

import android.annotation.SuppressLint;
import com.example.weathertestapp.domain.model.WeatherUiModel;
import com.example.weathertestapp.domain.repository.HomeRepository;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;

public class GetCurrentWeatherFromCurrentLocationUseCase {

    private final HomeRepository homeRepository;

    @Inject
    public GetCurrentWeatherFromCurrentLocationUseCase(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @SuppressLint("CheckResult")
    public Single<WeatherUiModel> invoke(String location) {
        return homeRepository.getCurrentWeather(location).map(response -> new WeatherUiModel(
                response.getLocation().getName() + ", " + response.getLocation().getRegion() + ", " + response.getLocation().getCountry(),
                "https:" + response.getCurrent().getCondition().getIcon(),
                response.getCurrent().getTemp_c(),
                response.getCurrent().getCondition().getText(),
                response.getCurrent().getWind_kph(),
                response.getCurrent().getHumidity()
        ));
    }
}
