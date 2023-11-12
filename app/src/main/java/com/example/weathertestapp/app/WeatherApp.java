package com.example.weathertestapp.app;

import android.app.Application;
import com.example.weathertestapp.app.di.AppComponent;
import com.example.weathertestapp.app.di.DaggerAppComponent;

public class WeatherApp extends Application {

    private static AppComponent component;

    public AppComponent getComponent() {
        return component;
    }

    public WeatherApp() {}

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.factory().create(getApplicationContext());
    }
}
