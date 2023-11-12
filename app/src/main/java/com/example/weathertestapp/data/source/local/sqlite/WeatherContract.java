package com.example.weathertestapp.data.source.local.sqlite;

import android.provider.BaseColumns;

public final class WeatherContract {

    public static class WeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "weather";
        public static final String COLUMN_NAME_CONDITION = "condition";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_HUMIDITY = "humidity";
        public static final String COLUMN_NAME_WIND = "wind";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WeatherEntry.TABLE_NAME + " (" +
                    BaseColumns._ID + " INTEGER PRIMARY KEY," +
                    WeatherEntry.COLUMN_NAME_CONDITION + " TEXT," +
                    WeatherEntry.COLUMN_NAME_LOCATION + " TEXT," +
                    WeatherEntry.COLUMN_NAME_TIME + " TEXT," +
                    WeatherEntry.COLUMN_NAME_HUMIDITY + " REAL," +
                    WeatherEntry.COLUMN_NAME_WIND + " REAL)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WeatherEntry.TABLE_NAME;

}
