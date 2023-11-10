package com.example.weathertestapp.app.di;

import com.example.weathertestapp.data.repository.HomeRepositoryImpl;
import com.example.weathertestapp.domain.repository.HomeRepository;
import dagger.Binds;
import dagger.Module;

@Module
interface RepositoryModule {
    @Binds
    public HomeRepository provideHomeRepository(HomeRepositoryImpl bookmarksRepositoryImpl);

}
