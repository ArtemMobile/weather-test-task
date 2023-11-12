package com.example.weathertestapp.presentation.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.weathertestapp.domain.WeatherUiState;
import com.example.weathertestapp.domain.model.WeatherUiModel;
import com.example.weathertestapp.domain.usecase.GetCurrentWeatherFromCurrentLocationUseCase;
import com.example.weathertestapp.utils.NetworkTransformer;
import javax.inject.Inject;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class HomeViewModel extends ViewModel {

    private final GetCurrentWeatherFromCurrentLocationUseCase getCurrentWeatherFromCurrentLocationUseCase;

    private final MutableLiveData<WeatherUiState> _currentWeatherData = new MutableLiveData<>();
    public LiveData<WeatherUiState> currentWeatherData = _currentWeatherData;

    @Inject
    public HomeViewModel(GetCurrentWeatherFromCurrentLocationUseCase getCurrentWeatherFromCurrentLocationUseCase) {
        this.getCurrentWeatherFromCurrentLocationUseCase = getCurrentWeatherFromCurrentLocationUseCase;
    }

    public void getCurrentWeatherData(String location) {
        getCurrentWeatherFromCurrentLocationUseCase.invoke(location)
                .compose(new NetworkTransformer())
                .subscribe(new SingleObserver<WeatherUiModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        _currentWeatherData.setValue(new WeatherUiState(null, null, true));
                    }

                    @Override
                    public void onSuccess(WeatherUiModel weatherUiModel) {
                        _currentWeatherData.setValue(new WeatherUiState(weatherUiModel, null, false));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _currentWeatherData.setValue(new WeatherUiState(null, e.getMessage(), false));
                    }
                });
    }

}
