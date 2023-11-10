package com.example.weathertestapp.data.source.remote;

import android.util.Log;
import androidx.annotation.NonNull;
import javax.inject.Inject;
import okhttp3.logging.HttpLoggingInterceptor;

public class LoggingInterceptor implements HttpLoggingInterceptor.Logger {
    @Inject
    public LoggingInterceptor (){

    }

    @Override
    public void log(@NonNull String s) {
        Log.i("OkHttp", s);
    }
}
