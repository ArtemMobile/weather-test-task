package com.example.weathertestapp.presentation.history;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.weathertestapp.app.WeatherApp;
import com.example.weathertestapp.app.di.AppComponent;
import com.example.weathertestapp.data.source.local.sqlite.HistoryModel;
import com.example.weathertestapp.databinding.FragmentHistoryBinding;
import com.example.weathertestapp.utils.RxSearch;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class HistoryFragment extends Fragment {

    public HistoryFragment() {}

    private FragmentHistoryBinding fragmentHistoryBinding;

    @Inject
    public HistoryViewModel historyViewModel;

    private HistoryAdapter historyAdapter;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHistoryBinding = FragmentHistoryBinding.inflate(getLayoutInflater());
        return fragmentHistoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppComponent component = ((WeatherApp) requireActivity().getApplication()).getComponent();
        component.inject(this);
        super.onViewCreated(view, savedInstanceState);
        applyHistoryAdapter();
        applyObservers();
        applySearchView();
        historyViewModel.getHistoryRecords("");
    }

    private void applyHistoryAdapter() {
        historyAdapter = new HistoryAdapter(id -> {
            historyViewModel.deleteWeatherFromHistory(id);
            String text;
            if (fragmentHistoryBinding.editTextSearch.getText() != null)
                text = fragmentHistoryBinding.editTextSearch.getText().toString();
            else
                text = "";

            historyViewModel.getHistoryRecords(text);
        });
        fragmentHistoryBinding.recyclerViewHistory.setAdapter(historyAdapter);
        fragmentHistoryBinding.recyclerViewHistory.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void applyObservers() {
        historyViewModel.weatherHistoryData.observe(getViewLifecycleOwner(), result -> {
            Boolean pending = result.pending();
            Object data = result.data();
            String error = result.error();
            if (data != null) {
                List<HistoryModel> historyData = (List<HistoryModel>) result.data();
                historyAdapter.submitList(historyData);
            }
            if (error != null) {
                Log.e("EXCEPTION", error);
                showSnackBar(error);
            }
            showProgressBar(pending);
        });
    }

    private void applySearchView() {
        compositeDisposable.add(RxSearch.fromView(fragmentHistoryBinding.editTextSearch)
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap(Observable::just)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> historyViewModel.getHistoryRecords(query)));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void showProgressBar(Boolean visible) {
        if (visible)
            fragmentHistoryBinding.progressBarLoading.setVisibility(View.VISIBLE);
        else
            fragmentHistoryBinding.progressBarLoading.setVisibility(View.GONE);
    }

    private void showSnackBar(String text) {
        Snackbar.make(Objects.requireNonNull(getView()), text, Snackbar.LENGTH_SHORT).show();
    }
}