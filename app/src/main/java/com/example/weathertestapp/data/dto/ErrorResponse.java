package com.example.weathertestapp.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

public class ErrorResponse implements Serializable {
  @SerializedName("error")
  private Error error;

  public Error getError() {
    return this.error;
  }

  public void setError(Error error) {
    this.error = error;
  }

  public static class Error implements Serializable {
    @SerializedName("code")
    private Integer code;

    @SerializedName("message")
    private String message;

    public Integer getCode() {
      return this.code;
    }

    public void setCode(Integer code) {
      this.code = code;
    }

    public String getMessage() {
      return this.message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }
}
