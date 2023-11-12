package com.example.weathertestapp.app;

import android.app.Application;
import com.example.weathertestapp.app.di.AppComponent;
import com.example.weathertestapp.app.di.DaggerAppComponent;
import com.example.weathertestapp.data.source.local.sqlite.WeatherDbHelper;

public class WeatherApp extends Application {

    private static AppComponent component;

    public AppComponent getComponent() {
        return component;
    }

    public WeatherApp() {
    }
    public WeatherDbHelper weatherDbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
        weatherDbHelper = new WeatherDbHelper(getApplicationContext());
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.factory().create(getApplicationContext());
    }
}
