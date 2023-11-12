package com.example.weathertestapp.domain.repository;

import com.example.weathertestapp.data.source.local.sqlite.HistoryModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface HistoryRepository {
    Observable<List<HistoryModel>> getHistoryData(String searchParam);
    void insertToHistory(HistoryModel historyModel);
    void deleteFromHistory(Long id);
}
