package com.example.weathertestapp.app.di;

import static com.example.weathertestapp.data.source.remote.ApiConstants.BASE_URL;
import android.content.Context;
import com.example.weathertestapp.data.source.local.sqlite.WeatherDbHelper;
import com.example.weathertestapp.data.source.remote.LoggingInterceptor;
import com.example.weathertestapp.data.source.remote.WeatherService;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    public GsonConverterFactory provideGson() {
        return GsonConverterFactory.create();
    }

    @Provides
    public RxJava3CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava3CallAdapterFactory.create();
    }

    @Provides
    public HttpLoggingInterceptor provideOkHttpInterceptor(LoggingInterceptor loggingInterceptor) {
        return new HttpLoggingInterceptor(loggingInterceptor).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .callTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .build();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitInstance(
            OkHttpClient okHttpClient,
            GsonConverterFactory gsonConverterFactory,
            RxJava3CallAdapterFactory rxJava3CallAdapterFactory
    ) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava3CallAdapterFactory);
    }

    @Provides
    @Singleton
    public WeatherService provideUserApiService(Retrofit.Builder retrofit) {
        return retrofit
                .baseUrl(BASE_URL)
                .build()
                .create(WeatherService.class);
    }

    @Provides
    @Singleton
    public WeatherDbHelper provideSqliteDb(Context context) {
        return new WeatherDbHelper(context);
    }
}
