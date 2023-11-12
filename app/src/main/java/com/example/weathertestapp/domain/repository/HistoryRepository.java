package com.example.weathertestapp.domain.repository;

import com.example.weathertestapp.data.source.local.sqlite.HistoryModel;

import java.util.List;

public interface HistoryRepository {
    List<HistoryModel> getHistoryData();
    void insertToHistory(HistoryModel historyModel);
}
