package com.example.weather;

import com.example.weather.model.StationStats;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StationStatsTest {

    @Test
public void testPerformanceLargeDataset() {
    StationStats stats = new StationStats();

    int n = 1_000_000;
    double sum = 0.0;

    long start = System.currentTimeMillis();

    for (int i = 1; i <= n; i++) {
        stats.addMeasurement(i);
        sum += i;
    }

    long duration = System.currentTimeMillis() - start;
    System.out.println("Processed " + n + " measurements in " + duration + " ms");

    double expectedMean = sum / n;

    assertEquals(1.0, stats.getMin(), 0.0001);
    assertEquals((double) n, stats.getMax(), 0.0001);
    assertEquals(expectedMean, stats.getMean(), 0.0001);

    assert(duration < 2000);
}

}
