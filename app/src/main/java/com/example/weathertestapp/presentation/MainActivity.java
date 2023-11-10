package com.example.weathertestapp.presentation;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.weathertestapp.R;
import com.example.weathertestapp.app.WeatherApp;
import com.example.weathertestapp.app.di.AppComponent;
import com.example.weathertestapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppComponent component = ((WeatherApp) this.getApplication()).getComponent();
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
