package WeatherApp.db;

import WeatherApp.model.Weather;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by wiewiogr on 08.06.17.
 */
public class MongoWeatherContextTest {
    MongoWeatherRepository context = new MongoWeatherRepository();

    @Test
    public void get_current_weather(){
        Weather weather = context.getCurrentWeather();
        LocalDateTime now = LocalDateTime.now();


        int minutes = (now.getMinute()/30) * 30;

        Assert.assertEquals(now.getHour(), weather.hour);
        Assert.assertEquals(minutes, weather.minute);
    }

    @Test
    public void get_weather_from_minute(){
        Weather weather = context.getWeatherFromMinute(2017,
                6, 8, 16, 0);
        Assert.assertEquals(16, weather.hour);
        Assert.assertEquals(0, weather.minute);
        Assert.assertEquals(2017, weather.year);
        Assert.assertEquals(8, weather.day);
        Assert.assertEquals(6, weather.month);
    }

    @Test
    public void get_weather_from_day(){
        List<Weather> list = context.getWeathersFromDay(2017,
                6, 8);
        for(Weather weather : list) {
            Assert.assertEquals(2017, weather.year);
            Assert.assertEquals(8, weather.day);
            Assert.assertEquals(6, weather.month);
        }
    }
}