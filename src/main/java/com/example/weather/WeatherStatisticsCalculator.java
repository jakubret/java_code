package com.example.weather.service;

import com.example.weather.model.Measurement;
import com.example.weather.model.StationStats;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Service class responsible for reading input data and calculating statistics.
 */
public class WeatherStatisticsCalculator {

    /**
     * Reads a file and parses lines into Measurement objects.
     */
    public List<Measurement> readMeasurements(Path filePath) throws IOException {
        List<Measurement> measurements = new ArrayList<>();

        Files.lines(filePath).forEach(line -> {
            String[] parts = line.split(";");
            if (parts.length == 2) {
                try {
                    String station = parts[0].trim();
                    double value = Double.parseDouble(parts[1].trim());
                    measurements.add(new Measurement(station, value));
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
        });

        return measurements;
    }

    public Map<String, StationStats> calculateStatistics(Path filePath) throws IOException {
    Map<String, StationStats> statsMap = new TreeMap<>();
    long[] parsedLines = {0};

    try (var lines = Files.lines(filePath)) {
        lines.forEach(line -> {
    line = line.trim();
    if (line.isEmpty()) return;

    String[] parts = line.split(";");
    if (parts.length != 2) {
        System.err.println("Niepoprawny format: " + line);
        return;
    }

    String station = parts[0].trim();
    String valueStr = parts[1].trim();

    // debug
    System.out.println("Parsing: station='" + station + "', value='" + valueStr + "'");

    try {
        double value = Double.parseDouble(valueStr);
        statsMap.putIfAbsent(station, new StationStats());
        statsMap.get(station).addMeasurement(value);
    } catch (NumberFormatException e) {
        System.err.println("Niepoprawna liczba: " + valueStr + " w linii: " + line);
    }
});

    }

    System.out.println("Liczba sparsowanych pomiarów: " + parsedLines[0]);
    return statsMap;
}

}
