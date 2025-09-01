package com.example.weather.model;

public class Measurement {
    private final String station;
    private final double value;

    public Measurement(String station, double value) {
        this.station = station;
        this.value = value;
    }

    public String getStation() {
        return station;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return station + "=" + value;
    }
}
