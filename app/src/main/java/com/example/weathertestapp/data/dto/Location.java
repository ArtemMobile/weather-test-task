package com.example.weathertestapp.data.dto;

import java.io.Serializable;

public class Location implements Serializable {
    private String localtime;

    private String country;

    private Integer localtime_epoch;

    private String name;

    private Double lon;

    private String region;

    private Double lat;

    private String tz_id;

    public String getLocaltime() {
      return this.localtime;
    }

    public void setLocaltime(String localtime) {
      this.localtime = localtime;
    }

    public String getCountry() {
      return this.country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    public Integer getLocaltime_epoch() {
      return this.localtime_epoch;
    }

    public void setLocaltime_epoch(Integer localtime_epoch) {
      this.localtime_epoch = localtime_epoch;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Double getLon() {
      return this.lon;
    }

    public void setLon(Double lon) {
      this.lon = lon;
    }

    public String getRegion() {
      return this.region;
    }

    public void setRegion(String region) {
      this.region = region;
    }

    public Double getLat() {
      return this.lat;
    }

    public void setLat(Double lat) {
      this.lat = lat;
    }

    public String getTz_id() {
      return this.tz_id;
    }

    public void setTz_id(String tz_id) {
      this.tz_id = tz_id;
    }
  }