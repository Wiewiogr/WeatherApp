package WeatherApp.db;

import WeatherApp.model.Weather;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wiewiogr on 08.06.17.
 */
public class MongoWeatherRepository {
    private MongoClient mongoClient;
    private MongoDatabase db;
    private String collectionName = "weatherTest";

    public MongoWeatherRepository() {
        try {
            mongoClient = new MongoClient("85.255.7.108", 27017);
            db = mongoClient.getDatabase("test");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public Weather getWeatherFromMinute(int year, int month, int day, int hour, int minute){
        Weather weather = new Weather();
        if(minute >= 30) minute = 30;
        else minute = 0;
        Document queryDocument = createQueryForMinute(year,
                month, day, hour, minute);
        return getSingleWeatherFromQuery(queryDocument);
    }


    public List<Weather> getWeathersFromDay(int year, int month, int day){
        List<Weather> list = new ArrayList<>();
        Weather weather;

        Document queryDocument;
        for(int i = 0; i < 24 ; i++){
            queryDocument = createQueryForMinute(year,
                    month, day, i, 0);
            weather = getSingleWeatherFromQuery(queryDocument);
            if(weather!= null) {
                list.add(weather);
            }
            queryDocument = createQueryForMinute(year,
                    month, day, i, 30);
            weather = getSingleWeatherFromQuery(queryDocument);
            if(weather!= null) {
                list.add(weather);
            }
        }
        return list;
    }

    private Document createQueryForMinute(int year, int month, int day, int hour, int minute){
        Document document = new Document();
        Document date = new Document();
        date.append("year",year);
        date.append("month",month);
        date.append("day",day);
        date.append("hour",hour);
        date.append("minutes",minute);
        document.append("date",date);
        return document;
    }

    public Weather getCurrentWeather(){
        Weather weather = null;
        LocalDateTime date = LocalDateTime.now();
        while(weather == null) {
            int minute = date.getMinute();
            if (minute >= 30) minute = 30;
            else minute = 0;
            Document queryDocument = createQueryForMinute(date.getYear(),
                    date.getMonthValue(), date.getDayOfMonth(), date.getHour(), minute);
            weather = getSingleWeatherFromQuery(queryDocument);
            date.minusMinutes(30);
        }
        return weather;
    }

    private Weather getSingleWeatherFromQuery(Document query){
        Weather weather = new Weather();
        MongoCollection<Document> collection = db.getCollection(collectionName);
        DocumentToWeatherConverter converter = new DocumentToWeatherConverter();
        for( Document document : collection.find().filter(query)){
            return converter.convert(document);
        }
        return null;
    }
}
