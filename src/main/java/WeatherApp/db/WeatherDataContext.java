package WeatherApp.db;

import WeatherApp.model.Weather;

import java.util.List;

/**
 * Created by wiewiogr on 12.06.17.
 */
public interface WeatherDataContext {

    Weather getWeatherFromMinute(int year, int month, int day, int hour, int minute);
    List<Weather> getWeathersFromDay(int year, int month, int day);
    Weather getCurrentWeather();
}


