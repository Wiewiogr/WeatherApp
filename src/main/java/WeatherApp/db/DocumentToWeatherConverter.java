package WeatherApp.db;

import WeatherApp.model.Weather;
import org.bson.Document;

/**
 * Created by wiewiogr on 08.06.17.
 */
public class DocumentToWeatherConverter {

    public Weather convert(Document document){
        Weather weather = new Weather();
        weather.clouds = ((Integer) document.get("clouds"));
        weather.description = (String) document.get("description");
        weather.main = (String) document.get("main");
        weather.icon = (String) document.get("icon");
        weather.humidity = ((Integer) document.get("humidity"));
        weather.pressure = ((Integer) document.get("pressure"));
        weather.temperature = ((Integer) document.get("temperature"));
        weather.visibilty = ((Integer) document.get("visibilty"));
        weather.windDegree = ((Integer) document.get("windDegree"));
        weather.windSpeed = ((Double) document.get("windSpeed"));
        Document date = (Document)document.get("date");
        weather.year = (Integer)date.get("year");
        weather.month = (Integer)date.get("month");
        weather.day = (Integer)date.get("day");
        weather.hour = (Integer)date.get("hour");
        weather.minute = (Integer)date.get("minutes");
        return weather;
    }
}
