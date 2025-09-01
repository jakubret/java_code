# WeatherStats

A simple Java application that reads a CSV file with weather station measurements, calculates **min**, **mean**, and **max** temperature per station, and outputs the results as **plain text** or **JSON**.

---

## Format pliku wejściowego

CSV powinien mieć format:

| StationName | Temperature |
|-------------|------------|
| Hamburg     | 12.0       |
| Istanbul    | 23.0       |
| ...         | ...        |

- Oddzielone średnikiem (`;`)
- Temperatura w formacie dziesiętnym (`12.3`)

---

## Budowa i uruchomienie

### 1. Budowa projektu

Używając Maven:

```bash
mvn clean compile
```

### 2. Uruchomienie aplikacji
```bash
mvn exec:java -Dexec.args="ścieżka/do/input.csv [json]"
```
- Pierwszy argument: plik CSV z danymi
- Drugi argument (opcjonalny): jeśli podasz json, wynik zostanie zapisany w pliku JSON (weather_stats.json domyślnie)
Przykład:
```bash
mvn exec:java -Dexec.args="bigfile.csv json"
```
- Zostanie utworzony plik weather_stats.json z tymi samymi danymi
- Przykładowy wynik JSON
```json
{
  "Aachen": {
    "min": 10.5,
    "max": 23.8,
    "mean": 17.2
  },
  "Berlin": {
    "min": 12.0,
    "max": 25.4,
    "mean": 18.7
  }
}
```
Plain output (terminal)
{Aachen=10.5/17.2/23.8, Berlin=12.0/18.7/25.4, ...}
### Struktura projektu
src/main/java/com/example/weather/WeatherStatsApp.java
src/main/java/com/example/weather/service/WeatherStatisticsCalculator.java
src/main/java/com/example/weather/model/StationStats.java
- WeatherStatsApp.java – główna klasa aplikacji
- WeatherStatisticsCalculator.java – serwis liczący statystyki
- StationStats.java – model przechowujący min/mean/max 
### Wymagania
- Java 17+
- Maven
