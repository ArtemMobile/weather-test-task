package com.example.weathertestapp.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class RxSearch {
    public static Observable<String> fromView(EditText editText) {
        PublishSubject<String> subject = PublishSubject.create();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                {
                    subject.onNext(editable.toString());
                }
            }
        });
        return subject;
    }
}
