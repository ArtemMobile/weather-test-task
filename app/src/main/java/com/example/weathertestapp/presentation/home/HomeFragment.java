package com.example.weathertestapp.presentation.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.weathertestapp.app.WeatherApp;
import com.example.weathertestapp.app.di.AppComponent;
import com.example.weathertestapp.databinding.FragmentMainBinding;
import com.example.weathertestapp.domain.model.WeatherUiModel;
import com.example.weathertestapp.utils.SnackBarAction;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import javax.inject.Inject;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    private final String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private String currentLocation;

    @Inject
    public HomeViewModel homeViewModel;
    private final ActivityResultLauncher<String[]> locationPermissionsLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(),
            result -> {
                if (result.containsValue(false)) {
                    showSnackBar("Дайте разрешение, чтобы получить данные о погоде", "Запросить", this::requestLocationPermissions);
                } else {
                    initLocationListener();
                }
            });

    private FragmentMainBinding fragmentMainBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentMainBinding = FragmentMainBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
        return fragmentMainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppComponent component = ((WeatherApp) requireActivity().getApplication()).getComponent();
        component.inject(this);
        super.onViewCreated(view, savedInstanceState);
        observeWeather();
        requestLocationPermissions();
    }

    private void requestLocationPermissions(){
        locationPermissionsLauncher.launch(permissions);
    }

    @SuppressLint("MissingPermission")
    private void initLocationListener() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());
        if (locationPermissionsGranted()) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(((OnSuccessListener<Location>) location -> {
                if (location != null) {
                    double lat = ((Location) location).getLatitude();
                    double lon = ((Location) location).getLongitude();
                    currentLocation = lat + "," + lon;
                    homeViewModel.getCurrentWeatherData(currentLocation);
                    setOnRefreshListener();
                }
            }));
        }
    }

    private void observeWeather() {
        homeViewModel.currentWeatherData.observe(getViewLifecycleOwner(), result -> {
            Boolean pending = result.pending();
            Object data = result.data();
            String error = result.error();
            if (data != null) {
                WeatherUiModel weather = (WeatherUiModel) result.data();
                bindWeather(weather);
            }
            if (error != null) {
                showSnackBar("Ошибка, проблемы с интернетом...", "Обновить", () -> homeViewModel.getCurrentWeatherData(currentLocation));
            }
            fragmentMainBinding.getRoot().setRefreshing(pending);
            showProgressBar(pending);
        });
    }

    private void bindWeather(WeatherUiModel weather) {
        fragmentMainBinding.textViewLocation.setText(weather.getFullLocation());
        fragmentMainBinding.textViewConditionAndTemp.setText(weather.getCondition() + ", " + weather.getTemperature() + "°C");
        fragmentMainBinding.textViewHumidity.setText(String.valueOf(weather.getHumidity()));
        fragmentMainBinding.textViewWindSpeed.setText(String.valueOf(weather.getWind()));
        Glide.with(requireContext())
                .load(weather.getIcon())
                .into(fragmentMainBinding.imageViewIcon);
    }

    private Boolean locationPermissionsGranted() {
        return ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void showProgressBar(Boolean visible) {
        if (visible)
            fragmentMainBinding.progressBarLoading.setVisibility(View.VISIBLE);
        else
            fragmentMainBinding.progressBarLoading.setVisibility(View.GONE);
    }

    private void setOnRefreshListener() {
        fragmentMainBinding.getRoot().setOnRefreshListener(() -> homeViewModel.getCurrentWeatherData(currentLocation));
    }

    private void showSnackBar(String text, String actionText, SnackBarAction action) {
        Snackbar snackbar = Snackbar
                .make(getView(), text, Snackbar.LENGTH_INDEFINITE)
                .setAction(actionText, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        action.apply();
                    }
                });
        snackbar.show();
    }
}

