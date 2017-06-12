package WeatherApp.controller;

import WeatherApp.db.MongoWeatherDataContext;
import WeatherApp.db.WeatherDataContext;
import WeatherApp.model.Weather;

import java.util.List;

/**
 * Created by wiewiogr on 08.06.17.
 */
public class WeatherController {
    private static WeatherController controller = new WeatherController();
    public static WeatherController getInstance(){
        return controller;
    }

    private WeatherDataContext repository = new MongoWeatherDataContext();
    private WeatherController(){};

    public Weather getWeatherFromMinute(int year, int month, int day, int hour, int minute) {
        return repository.getWeatherFromMinute(year, month, day, hour, minute);
    }

    public List<Weather> getWeatherFromDay(int year, int month, int day) {
        return repository.getWeathersFromDay(year, month, day);
    }

    public Weather getCurrentWeather() {
        return repository.getCurrentWeather();
    }

}
