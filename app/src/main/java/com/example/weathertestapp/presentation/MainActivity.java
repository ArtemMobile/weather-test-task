package com.example.weathertestapp.presentation;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.weathertestapp.R;
import com.example.weathertestapp.app.App;
import com.example.weathertestapp.app.di.AppComponent;
import com.example.weathertestapp.databinding.ActivityMainBinding;
import com.example.weathertestapp.domain.model.WeatherUiModel;
import com.example.weathertestapp.utils.NetworkTransformer;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppComponent component = ((App) this.getApplication()).getComponent();
        component.inject(this);
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        initBottomNavView();

        setContentView(activityMainBinding.getRoot());
    }

    private void initBottomNavView() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(activityMainBinding.bottomNavigationView, navController);
    }
}
