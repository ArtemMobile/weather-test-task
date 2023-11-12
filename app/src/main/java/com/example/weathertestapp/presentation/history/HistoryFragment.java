package com.example.weathertestapp.presentation.history;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.weathertestapp.databinding.FragmentHistoryBinding;

public class HistoryFragment extends Fragment {

    public HistoryFragment() {}

    private FragmentHistoryBinding fragmentHistoryBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentHistoryBinding = FragmentHistoryBinding.inflate(getLayoutInflater());
        return fragmentHistoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}