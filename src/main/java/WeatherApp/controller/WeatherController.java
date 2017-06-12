package WeatherApp.controller;

import WeatherApp.db.WeatherDataContext;
import WeatherApp.db.WeatherDataContextFactory;
import WeatherApp.model.Weather;

import java.util.List;

/**
 * Created by wiewiogr on 08.06.17.
 */
public class WeatherController {
    public static WeatherDataContextFactory factory;
    private static WeatherController controller;
    public static WeatherController getInstance(){
        if(controller == null){
            controller = new WeatherController();
        }
        return controller;
    }

    private WeatherDataContext dataContext;

    private WeatherController(){
        this.dataContext = factory.createDataContext();
    };

    public Weather getWeatherFromMinute(int year, int month, int day, int hour, int minute) {
        return dataContext.getWeatherFromMinute(year, month, day, hour, minute);
    }

    public List<Weather> getWeatherFromDay(int year, int month, int day) {
        return dataContext.getWeathersFromDay(year, month, day);
    }

    public Weather getCurrentWeather() {
        return dataContext.getCurrentWeather();
    }

}
