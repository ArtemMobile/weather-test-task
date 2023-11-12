package com.example.weathertestapp.presentation.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.weathertestapp.app.WeatherApp;
import com.example.weathertestapp.app.di.AppComponent;
import com.example.weathertestapp.data.source.local.sqlite.HistoryModel;
import com.example.weathertestapp.databinding.FragmentSearchBinding;
import com.example.weathertestapp.domain.model.SearchWeatherUiModel;
import com.example.weathertestapp.presentation.history.HistoryViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class SearchFragment extends Fragment {

    public SearchFragment() {}

    private FragmentSearchBinding fragmentSearchBinding;

    @Inject
    public SearchViewModel searchViewModel;

    @Inject
    public HistoryViewModel historyViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSearchBinding = FragmentSearchBinding.inflate(getLayoutInflater());
        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppComponent component = ((WeatherApp) requireActivity().getApplication()).getComponent();
        component.inject(this);
        super.onViewCreated(view, savedInstanceState);
        applySearchEditor();
        applyWeatherObservers();
        applyClicks();
    }

    private void applyClicks() {
        fragmentSearchBinding.imageViewSaveToHistory.setOnClickListener(view -> {
            historyViewModel.insertWeatherToHistoryUseCase(new HistoryModel(
                    null,
                    fragmentSearchBinding.textViewLocation.getText().toString(),
                    fragmentSearchBinding.textViewConditionAndTemp.getText().toString(),
                    Double.parseDouble(fragmentSearchBinding.textViewWindSpeed.getText().toString()),
                    Double.parseDouble(fragmentSearchBinding.textViewHumidity.getText().toString()),
                    fragmentSearchBinding.textViewLocationAndTime.getText().toString()
            ));
            Toast.makeText(requireContext(), "Saved!", Toast.LENGTH_SHORT).show();
        });
    }

    private void applySearchEditor() {
        fragmentSearchBinding.editTextSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            searchViewModel.getWeatherForCity(textView.getText().toString());
            return false;
        });
    }

    private void applyWeatherObservers() {
        searchViewModel.currentWeatherData.observe(getViewLifecycleOwner(), result -> {
            Boolean pending = result.pending();
            Object data = result.data();
            String error = result.error();
            if (data != null) {
                SearchWeatherUiModel weather = (SearchWeatherUiModel) result.data();
                bindWeather(weather);
                fragmentSearchBinding.imageViewSaveToHistory.setVisibility(View.VISIBLE);
            }
            if (error != null) {
                Log.e("EXCEPTION", error);
                showSnackBar(error);
            }
            showProgressBar(pending);
        });
    }

    @SuppressLint("SetTextI18n")
    private void bindWeather(SearchWeatherUiModel weather) {
        fragmentSearchBinding.textViewLocation.setText(weather.fullLocation());
        fragmentSearchBinding.textViewConditionAndTemp.setText(weather.condition() + ", " + weather.temperature() + "°C");
        fragmentSearchBinding.textViewHumidity.setText(String.valueOf(weather.humidity()));
        fragmentSearchBinding.textViewWindSpeed.setText(String.valueOf(weather.wind()));
        fragmentSearchBinding.textViewLocationAndTime.setText(weather.locationLatLon() + " local time: " + weather.localtime());
        Glide.with(requireContext())
                .load(weather.icon())
                .into(fragmentSearchBinding.imageViewIcon);
        fragmentSearchBinding.imageViewHumidity.setVisibility(View.VISIBLE);
        fragmentSearchBinding.imageViewWind.setVisibility(View.VISIBLE);
    }

    private void showProgressBar(Boolean visible) {
        if (visible)
            fragmentSearchBinding.progressBarLoading.setVisibility(View.VISIBLE);
        else
            fragmentSearchBinding.progressBarLoading.setVisibility(View.GONE);
    }

    private void showSnackBar(String text) {
        Snackbar.make(getView(), text, Snackbar.LENGTH_SHORT).show();
    }
}