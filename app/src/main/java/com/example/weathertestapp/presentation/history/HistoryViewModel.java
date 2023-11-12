package com.example.weathertestapp.presentation.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.weathertestapp.data.source.local.sqlite.HistoryModel;
import com.example.weathertestapp.domain.WeatherUiState;
import com.example.weathertestapp.domain.usecase.DeleteWeatherFromHistoryUseCase;
import com.example.weathertestapp.domain.usecase.InsertWeatherToHistoryUseCase;
import com.example.weathertestapp.domain.usecase.SearchHistoryWeatherUseCase;
import com.example.weathertestapp.utils.DatabaseTransformer;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class HistoryViewModel extends ViewModel {

    private final SearchHistoryWeatherUseCase searchHistoryWeatherUseCase;
    private final InsertWeatherToHistoryUseCase insertWeatherToHistoryUseCase;
    private final DeleteWeatherFromHistoryUseCase deleteWeatherFromHistoryUseCase;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<WeatherUiState> _weatherHistoryData = new MutableLiveData<>();
    public LiveData<WeatherUiState> weatherHistoryData = _weatherHistoryData;

    @Inject
    public HistoryViewModel(SearchHistoryWeatherUseCase searchHistoryWeatherUseCase,
                            InsertWeatherToHistoryUseCase insertWeatherToHistoryUseCase,
                            DeleteWeatherFromHistoryUseCase deleteWeatherFromHistoryUseCase) {
        this.searchHistoryWeatherUseCase = searchHistoryWeatherUseCase;
        this.insertWeatherToHistoryUseCase = insertWeatherToHistoryUseCase;
        this.deleteWeatherFromHistoryUseCase = deleteWeatherFromHistoryUseCase;
    }

    public void deleteWeatherFromHistory(Long id) {
        deleteWeatherFromHistoryUseCase.invoke(id);
    }
    public void insertWeatherToHistoryUseCase(HistoryModel historyModel){
        insertWeatherToHistoryUseCase.invoke(historyModel);
    }

    public void getHistoryRecords(String searchParam) {
        searchHistoryWeatherUseCase.invoke(searchParam).compose(new DatabaseTransformer())
                .subscribe(new Observer<List<HistoryModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        _weatherHistoryData.setValue(new WeatherUiState(null, null, true));
                    }

                    @Override
                    public void onNext(List<HistoryModel> list) {
                        _weatherHistoryData.setValue(new WeatherUiState(list, null, false));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _weatherHistoryData.setValue(new WeatherUiState(null, e.getMessage(), false));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
