package com.example.weathertestapp.domain.usecase;

import com.example.weathertestapp.domain.repository.HistoryRepository;
import javax.inject.Inject;

public class DeleteWeatherFromHistoryUseCase {

    private final HistoryRepository historyRepository;

    @Inject
    public DeleteWeatherFromHistoryUseCase(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void invoke(Long id) {
        historyRepository.deleteFromHistory(id);
    }
}
