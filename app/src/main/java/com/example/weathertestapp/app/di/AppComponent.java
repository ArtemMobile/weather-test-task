package com.example.weathertestapp.app.di;

import android.content.Context;
import com.example.weathertestapp.presentation.MainActivity;
import com.example.weathertestapp.presentation.history.HistoryFragment;
import com.example.weathertestapp.presentation.home.HomeFragment;
import com.example.weathertestapp.presentation.search.SearchFragment;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {DataModule.class, RepositoryModule.class})
public interface AppComponent {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }


    void inject(MainActivity mainActivity);

    void inject(HomeFragment homeFragment);

    void inject(SearchFragment searchFragment);

    void inject(HistoryFragment historyFragment);
}
