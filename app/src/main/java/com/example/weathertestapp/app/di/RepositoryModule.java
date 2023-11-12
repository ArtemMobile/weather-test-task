package com.example.weathertestapp.app.di;

import com.example.weathertestapp.data.repository.HomeRepositoryImpl;
import com.example.weathertestapp.data.repository.SearchRepositoryImpl;
import com.example.weathertestapp.domain.repository.HomeRepository;
import com.example.weathertestapp.domain.repository.SearchRepository;

import dagger.Binds;
import dagger.Module;

@Module
interface RepositoryModule {
    @Binds
    public HomeRepository provideHomeRepository(HomeRepositoryImpl bookmarksRepositoryImpl);

    @Binds
    public SearchRepository provideSearchRepository(SearchRepositoryImpl searchRepositoryImpl);

}
