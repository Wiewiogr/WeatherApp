package WeatherApp.controller;

import WeatherApp.db.WeatherDataContext;
import WeatherApp.db.WeatherDataContextFactory;
import WeatherApp.model.Weather;

import java.util.List;

/**
 * Created by wiewiogr on 08.06.17.
 */
public class WeatherController {
    private static WeatherController controller = new WeatherController(
            WeatherDataContextFactory.getFactory("mongo").createDataContext()
    );
    public static WeatherController getInstance(){
        return controller;
    }

    private WeatherDataContext dataContext;// = new MongoWeatherDataContext();

    private WeatherController(WeatherDataContext dataContext){
        this.dataContext = dataContext;
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
