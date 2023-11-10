package com.example.weathertestapp.app;

import android.app.Application;
import com.example.weathertestapp.app.di.AppComponent;
import com.example.weathertestapp.app.di.DaggerAppComponent;

public class App extends Application {

    private static AppComponent component;

    public AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.create();
    }
}
