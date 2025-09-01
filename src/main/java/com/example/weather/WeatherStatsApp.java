package com.example.weather;

import com.example.weather.model.StationStats;
import com.example.weather.service.WeatherStatisticsCalculator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.Map;

public class WeatherStatsApp {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Usage: java WeatherStatsApp <input-file> [output-json-file]");
            System.exit(1);
        }

        Path inputFile = Path.of(args[0]);
        Path outputFile = args.length > 1 ? Path.of(args[1]) : Path.of("weather_stats.json");

        WeatherStatisticsCalculator calculator = new WeatherStatisticsCalculator();
        Map<String, StationStats> stats = calculator.calculateStatistics(inputFile);

        // zapis JSON do pliku
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile.toFile(), stats);

        // opcjonalnie wypis JSON w terminalu
       // String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stats);
       // System.out.println(jsonString);

        System.out.println("JSON zapisany do pliku: " + outputFile.toAbsolutePath());

        // plain output
    //    StringBuilder sb = new StringBuilder("{");
    //    stats.forEach((station, s) -> sb.append(station).append("=").append(s.toPlainString()).append(", "));
    //    if (!stats.isEmpty()) sb.setLength(sb.length() - 2); // usuń ostatni ", "
    //    sb.append("}");
    //    System.out.println(sb);
    }
}
