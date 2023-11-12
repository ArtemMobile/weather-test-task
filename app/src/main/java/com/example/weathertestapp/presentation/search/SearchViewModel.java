package com.example.weathertestapp.presentation.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weathertestapp.data.dto.ErrorResponse;
import com.example.weathertestapp.data.source.local.sqlite.HistoryModel;
import com.example.weathertestapp.domain.WeatherUiState;
import com.example.weathertestapp.domain.model.SearchWeatherUiModel;
import com.example.weathertestapp.domain.usecase.InsertWeatherToHistoryUseCase;
import com.example.weathertestapp.domain.usecase.SearchWeatherByCityNameUseCase;
import com.example.weathertestapp.utils.NetworkTransformer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.HttpException;

public class SearchViewModel {

    private final SearchWeatherByCityNameUseCase searchWeatherByCityNameUseCase;

    private final MutableLiveData<WeatherUiState> _currentWeatherData = new MutableLiveData<>();

    public LiveData<WeatherUiState> currentWeatherData = _currentWeatherData;

    @Inject
    public SearchViewModel(SearchWeatherByCityNameUseCase searchWeatherByCityNameUseCase, InsertWeatherToHistoryUseCase insertWeatherToHistoryUseCase) {
        this.searchWeatherByCityNameUseCase = searchWeatherByCityNameUseCase;
    }

    public void getWeatherForCity(String city) {
        searchWeatherByCityNameUseCase.invoke(city)
                .compose(new NetworkTransformer())
                .subscribe(new SingleObserver<SearchWeatherUiModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        _currentWeatherData.setValue(new WeatherUiState(null, null, true));
                    }

                    @Override
                    public void onSuccess(SearchWeatherUiModel searchWeatherUiModel) {
                        _currentWeatherData.setValue(new WeatherUiState(searchWeatherUiModel, null, false));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e instanceof HttpException) {
                            try {
                                String error = ((HttpException) e).response().errorBody().string().toString();
                                Type type = new TypeToken<ErrorResponse>() {
                                }.getType();
                                ErrorResponse errorBody = new Gson().fromJson(error, type);
                                _currentWeatherData.setValue(new WeatherUiState(null, errorBody.getError().getMessage(), false));
                            } catch (IOException ex) {
                                _currentWeatherData.setValue(new WeatherUiState(null, e.getMessage(), false));
                            }
                        }
                    }
                });

    }
}
