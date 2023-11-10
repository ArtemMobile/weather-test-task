package com.example.weathertestapp.data.dto;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;

public class WeatherResponse implements Serializable {
  private Current current;

  private Location location;

  public Current getCurrent() {
    return this.current;
  }

  public void setCurrent(Current current) {
    this.current = current;
  }

  public Location getLocation() {
    return this.location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }
}
