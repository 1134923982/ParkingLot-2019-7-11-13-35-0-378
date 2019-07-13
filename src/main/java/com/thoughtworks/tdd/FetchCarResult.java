package com.thoughtworks.tdd;

public class FetchCarResult {
    private Car car;
    private String message;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
