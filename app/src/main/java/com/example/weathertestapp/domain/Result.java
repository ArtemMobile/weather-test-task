package com.example.weathertestapp.domain;

public sealed class Result permits Result.Error, Result.Pending, Result.Success {
    static final class Success<T> extends Result{

        private T data;

        public T getData() {
            return data;

        }

        public void setData(T data) {
            this.data = data;
        }

        public Success(T data){
            this.data = data;
        }
    }
    static final class Error extends Result{
        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public Error(String error){
            this.error = error;
        }
    }
    static final class Pending extends Result{ }
}





