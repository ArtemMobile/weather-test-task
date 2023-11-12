package com.example.weathertestapp.domain.usecase;

import android.annotation.SuppressLint;

import com.example.weathertestapp.data.dto.WeatherResponse;
import com.example.weathertestapp.domain.model.SearchWeatherUiModel;
import com.example.weathertestapp.domain.repository.SearchRepository;
import java.text.SimpleDateFormat;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;

public class SearchWeatherByCityNameUseCase {

    private final SearchRepository searchRepository;

    @Inject
    public SearchWeatherByCityNameUseCase(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @SuppressLint("SimpleDateFormat")
    public Single<SearchWeatherUiModel> invoke(String city) {
        return searchRepository.searchWeatherByCityName(city).map(response -> new SearchWeatherUiModel(
                response.getLocation().getName() + ", " + response.getLocation().getRegion() + ", " + response.getLocation().getCountry(),
                "https:" + response.getCurrent().getCondition().getIcon(),
                response.getCurrent().getTemp_c(),
                response.getCurrent().getCondition().getText(),
                response.getCurrent().getWind_kph(),
                response.getCurrent().getHumidity(),
                new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(response.getLocation().getLocaltime())),
                response.getLocation().getLat() + ","  + response.getLocation().getLon()
        ));
    }
}
