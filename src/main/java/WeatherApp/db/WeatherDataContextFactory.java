package WeatherApp.db;

/**
 * Created by wiewiogr on 12.06.17.
 */
public interface WeatherDataContextFactory {
    WeatherDataContext createDataContext();

    static WeatherDataContextFactory getFactory(String type) {
        if(type.equals("mongo")){
            return new MongoWeatherDataContextFactory();
        } else {
            return null;
        }
    }
}
