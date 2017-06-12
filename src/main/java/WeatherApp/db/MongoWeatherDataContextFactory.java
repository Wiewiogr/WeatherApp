package WeatherApp.db;

/**
 * Created by wiewiogr on 12.06.17.
 */
public class MongoWeatherDataContextFactory implements WeatherDataContextFactory {
    @Override
    public WeatherDataContext createDataContext() {
        return new MongoWeatherDataContext();
    }
}
