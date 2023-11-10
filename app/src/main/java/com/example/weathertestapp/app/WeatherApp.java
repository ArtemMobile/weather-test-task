package com.example.weathertestapp.app;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.weathertestapp.app.di.AppComponent;
import com.example.weathertestapp.app.di.DaggerAppComponent;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class WeatherApp extends Application {

    private static AppComponent component;

    public AppComponent getComponent() {
        return component;
    }

    public WeatherApp() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();

    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.create();
    }

}
