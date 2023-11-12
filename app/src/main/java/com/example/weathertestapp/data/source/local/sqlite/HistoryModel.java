package com.example.weathertestapp.data.source.local.sqlite;

public record HistoryModel(Long Id, String fullLocation, String condition, double wind, double humidity, String localtime) { }
