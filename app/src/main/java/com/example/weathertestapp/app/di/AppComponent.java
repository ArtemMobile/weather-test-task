package com.example.weathertestapp.app.di;

import com.example.weathertestapp.presentation.MainActivity;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {
     void inject(MainActivity mainActivity);
}
