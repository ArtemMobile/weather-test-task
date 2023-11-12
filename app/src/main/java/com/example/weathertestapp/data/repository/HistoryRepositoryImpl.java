package com.example.weathertestapp.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.example.weathertestapp.data.source.local.sqlite.HistoryModel;
import com.example.weathertestapp.data.source.local.sqlite.WeatherContract;
import com.example.weathertestapp.data.source.local.sqlite.WeatherDbHelper;
import com.example.weathertestapp.domain.repository.HistoryRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;

public class HistoryRepositoryImpl implements HistoryRepository {

    private WeatherDbHelper weatherDbHelper;
    private  SQLiteDatabase dbWrite;
    private  SQLiteDatabase dbRead;

    @Inject
    public HistoryRepositoryImpl(WeatherDbHelper weatherDbHelper){
        this.weatherDbHelper = weatherDbHelper;
        this.dbWrite = weatherDbHelper.getWritableDatabase();
        this.dbRead = weatherDbHelper.getReadableDatabase();
    }
    @Override
    public Observable<List<HistoryModel>> getHistoryData(String searchParam) {
        String[] projection = {
                BaseColumns._ID,
                WeatherContract.WeatherEntry.COLUMN_NAME_LOCATION,
                WeatherContract.WeatherEntry.COLUMN_NAME_TIME,
                WeatherContract.WeatherEntry.COLUMN_NAME_CONDITION,
                WeatherContract.WeatherEntry.COLUMN_NAME_HUMIDITY,
                WeatherContract.WeatherEntry.COLUMN_NAME_WIND,
        };
        String selection = WeatherContract.WeatherEntry.COLUMN_NAME_LOCATION + " LIKE ?";
        String[] selectionArgs = {"%" +  searchParam  + "%"};

        String sortOrder = BaseColumns._ID + " DESC";
        Cursor cursor = dbRead.query(
                WeatherContract.WeatherEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        List items = new ArrayList<HistoryModel>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(WeatherContract.WeatherEntry._ID));
            String fullLocation = cursor.getString(cursor.getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_NAME_LOCATION));
            String condition = cursor.getString(cursor.getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_NAME_CONDITION));
            Float wind = cursor.getFloat(cursor.getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_NAME_WIND));
            Float humidity = cursor.getFloat(cursor.getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_NAME_HUMIDITY));
            String localtime = cursor.getString(cursor.getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_NAME_TIME));
            items.add(new HistoryModel(itemId, fullLocation, condition, wind, humidity, localtime));
        }
        cursor.close();
        return Observable.fromArray(items);
    }

    @Override
    public void insertToHistory(HistoryModel historyModel) {
        ContentValues values = new ContentValues();
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_CONDITION, historyModel.condition());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_HUMIDITY, historyModel.humidity());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_WIND, historyModel.wind());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_TIME, historyModel.localtime());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_LOCATION, historyModel.fullLocation());
        dbWrite.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, values);
    }
}
