package com.example.weathertestapp.data.dto;

import java.io.Serializable;

public  class Current implements Serializable {
    private Double feelslike_c;

    private Double uv;

    private String last_updated;

    private Double feelslike_f;

    private Integer wind_degree;

    private Integer last_updated_epoch;

    private Integer is_day;

    private Double precip_in;

    private String wind_dir;

    private Double gust_mph;

    private Double temp_c;

    private Double pressure_in;

    private Double gust_kph;

    private Double temp_f;

    private Double precip_mm;

    private Integer cloud;

    private Double wind_kph;

    private Condition condition;

    private Double wind_mph;

    private Double vis_km;

    private Integer humidity;

    private Double pressure_mb;

    private Double vis_miles;

    public Double getFeelslike_c() {
      return this.feelslike_c;
    }

    public void setFeelslike_c(Double feelslike_c) {
      this.feelslike_c = feelslike_c;
    }

    public Double getUv() {
      return this.uv;
    }

    public void setUv(Double uv) {
      this.uv = uv;
    }

    public String getLast_updated() {
      return this.last_updated;
    }

    public void setLast_updated(String last_updated) {
      this.last_updated = last_updated;
    }

    public Double getFeelslike_f() {
      return this.feelslike_f;
    }

    public void setFeelslike_f(Double feelslike_f) {
      this.feelslike_f = feelslike_f;
    }

    public Integer getWind_degree() {
      return this.wind_degree;
    }

    public void setWind_degree(Integer wind_degree) {
      this.wind_degree = wind_degree;
    }

    public Integer getLast_updated_epoch() {
      return this.last_updated_epoch;
    }

    public void setLast_updated_epoch(Integer last_updated_epoch) {
      this.last_updated_epoch = last_updated_epoch;
    }

    public Integer getIs_day() {
      return this.is_day;
    }

    public void setIs_day(Integer is_day) {
      this.is_day = is_day;
    }

    public Double getPrecip_in() {
      return this.precip_in;
    }

    public void setPrecip_in(Double precip_in) {
      this.precip_in = precip_in;
    }

    public String getWind_dir() {
      return this.wind_dir;
    }

    public void setWind_dir(String wind_dir) {
      this.wind_dir = wind_dir;
    }

    public Double getGust_mph() {
      return this.gust_mph;
    }

    public void setGust_mph(Double gust_mph) {
      this.gust_mph = gust_mph;
    }

    public Double getTemp_c() {
      return this.temp_c;
    }

    public void setTemp_c(Double temp_c) {
      this.temp_c = temp_c;
    }

    public Double getPressure_in() {
      return this.pressure_in;
    }

    public void setPressure_in(Double pressure_in) {
      this.pressure_in = pressure_in;
    }

    public Double getGust_kph() {
      return this.gust_kph;
    }

    public void setGust_kph(Double gust_kph) {
      this.gust_kph = gust_kph;
    }

    public Double getTemp_f() {
      return this.temp_f;
    }

    public void setTemp_f(Double temp_f) {
      this.temp_f = temp_f;
    }

    public Double getPrecip_mm() {
      return this.precip_mm;
    }

    public void setPrecip_mm(Double precip_mm) {
      this.precip_mm = precip_mm;
    }

    public Integer getCloud() {
      return this.cloud;
    }

    public void setCloud(Integer cloud) {
      this.cloud = cloud;
    }

    public Double getWind_kph() {
      return this.wind_kph;
    }

    public void setWind_kph(Double wind_kph) {
      this.wind_kph = wind_kph;
    }

    public Condition getCondition() {
      return this.condition;
    }

    public void setCondition(Condition condition) {
      this.condition = condition;
    }

    public Double getWind_mph() {
      return this.wind_mph;
    }

    public void setWind_mph(Double wind_mph) {
      this.wind_mph = wind_mph;
    }

    public Double getVis_km() {
      return this.vis_km;
    }

    public void setVis_km(Double vis_km) {
      this.vis_km = vis_km;
    }

    public Integer getHumidity() {
      return this.humidity;
    }

    public void setHumidity(Integer humidity) {
      this.humidity = humidity;
    }

    public Double getPressure_mb() {
      return this.pressure_mb;
    }

    public void setPressure_mb(Double pressure_mb) {
      this.pressure_mb = pressure_mb;
    }

    public Double getVis_miles() {
      return this.vis_miles;
    }

    public void setVis_miles(Double vis_miles) {
      this.vis_miles = vis_miles;
    }

  
  }