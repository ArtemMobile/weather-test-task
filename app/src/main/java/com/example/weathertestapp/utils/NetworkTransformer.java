package com.example.weathertestapp.utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NetworkTransformer<T> implements SingleTransformer<T, T> {
    @Override
    public @NonNull SingleSource<T> apply(@NonNull Single<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}