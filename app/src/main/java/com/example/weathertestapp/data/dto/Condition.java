package com.example.weathertestapp.data.dto;

import java.io.Serializable;

public class Condition implements Serializable {

      private Integer code;

      private String icon;

      private String text;

      public Integer getCode() {
        return this.code;
      }

      public void setCode(Integer code) {
        this.code = code;
      }

      public String getIcon() {
        return this.icon;
      }

      public void setIcon(String icon) {
        this.icon = icon;
      }

      public String getText() {
        return this.text;
      }

      public void setText(String text) {
        this.text = text;
      }
    }