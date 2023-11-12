package com.example.weathertestapp.domain.usecase;

import com.example.weathertestapp.data.source.local.sqlite.HistoryModel;
import com.example.weathertestapp.domain.repository.HistoryRepository;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;

public class SearchHistoryWeatherUseCase {
    private final HistoryRepository historyRepository;

    @Inject
    public SearchHistoryWeatherUseCase(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public Observable<List<HistoryModel>> invoke(String searchParam) {
        return historyRepository.getHistoryData(searchParam);
    }
}
