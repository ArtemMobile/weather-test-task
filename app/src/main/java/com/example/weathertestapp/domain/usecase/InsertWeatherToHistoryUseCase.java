package com.example.weathertestapp.domain.usecase;

import com.example.weathertestapp.data.source.local.sqlite.HistoryModel;
import com.example.weathertestapp.domain.repository.HistoryRepository;
import javax.inject.Inject;

public class InsertWeatherToHistoryUseCase {

    private final HistoryRepository historyRepository;

    @Inject
    public InsertWeatherToHistoryUseCase(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void invoke(HistoryModel historyModel) {
        historyRepository.insertToHistory(historyModel);
    }
}
