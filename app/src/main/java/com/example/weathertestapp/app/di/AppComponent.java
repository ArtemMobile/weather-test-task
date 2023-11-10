package com.example.weathertestapp.app.di;

import com.example.weathertestapp.presentation.MainActivity;
import com.example.weathertestapp.presentation.home.HomeFragment;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {DataModule.class, RepositoryModule.class})
public interface AppComponent {
     void inject(MainActivity mainActivity);
     void inject(HomeFragment homeFragment);
}
