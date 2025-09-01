package com.example.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationStats {
    @JsonProperty
    private double min = Double.MAX_VALUE;
    @JsonProperty
    private double max = Double.MIN_VALUE;

    private double sum = 0.0;
    private int count = 0;

    public void addMeasurement(double value) {
        if (value < min) min = value;
        if (value > max) max = value;
        sum += value;
        count++;
    }

    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getMean() { return count == 0 ? 0.0 : sum / count; }

    public String toPlainString() {
        return String.format("%.1f/%.1f/%.1f", getMin(), getMean(), getMax());
    }

    @Override
    public String toString() {
        return toPlainString();
    }
}
