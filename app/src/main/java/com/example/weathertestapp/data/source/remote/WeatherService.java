package com.example.weathertestapp.data.source.remote;

import static com.example.weathertestapp.data.source.remote.ApiConstants.CURRENT_ENDPOINT;
import static com.example.weathertestapp.data.source.remote.ApiConstants.KEY;
import static com.example.weathertestapp.data.source.remote.ApiConstants.Q;
import com.example.weathertestapp.data.dto.WeatherResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET(CURRENT_ENDPOINT)
    Single<WeatherResponse> getCurrentWeatherForLocation(
            @Query(KEY) String key,
            @Query(Q) String q
    );
}
