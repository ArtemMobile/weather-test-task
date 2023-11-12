package com.example.weathertestapp.data.repository;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.example.weathertestapp.data.source.local.sqlite.HistoryModel;
import com.example.weathertestapp.data.source.local.sqlite.WeatherContract;
import com.example.weathertestapp.data.source.local.sqlite.WeatherDbHelper;
import com.example.weathertestapp.domain.repository.HistoryRepository;
import java.util.List;
import javax.inject.Inject;

public class HistoryRepositoryImpl implements HistoryRepository {

    private WeatherDbHelper weatherDbHelper;
    private  SQLiteDatabase db;

    @Inject
    public HistoryRepositoryImpl(WeatherDbHelper weatherDbHelper){
        this.weatherDbHelper = weatherDbHelper;
        this.db = weatherDbHelper.getWritableDatabase();
    }
    @Override
    public List<HistoryModel> getHistoryData() {
        return null;
    }

    @Override
    public void insertToHistory(HistoryModel historyModel) {
        ContentValues values = new ContentValues();
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_CONDITION, historyModel.getCondition());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_HUMIDITY, historyModel.getHumidity());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_WIND, historyModel.getWind());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_TIME, historyModel.getLocaltime());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_LOCATION, historyModel.getFullLocation());
        db.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, values);
    }
}
