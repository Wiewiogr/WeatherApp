package WeatherApp.db;

import WeatherApp.model.Weather;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wiewiogr on 08.06.17.
 */
public class DocumentToWeatherConverterTest {

    String json = "{ \"_id\" : ObjectId(\"59393c4346e0fb0001efe114\")," +
            " \"main\" : \"Clouds\"," +
            " \"description\" : \"scattered clouds\"," +
            " \"icon\" : \"03d\"," +
            " \"temperature\" : 20," +
            " \"humidity\" : 42," +
            " \"pressure\" : 1020," +
            " \"visibilty\" : 10000," +
            " \"windSpeed\" : 5.1," +
            " \"windDegree\" : 240," +
            " \"clouds\" : 40," +
            " \"date\" : { \"year\" : 2017, \"month\" : 6, \"day\" : 8, \"hour\" : 12, \"minutes\" : 0 }" +
            " }\n";
    DocumentToWeatherConverter converter = new DocumentToWeatherConverter();
    @Test
    public void convert(){
        Document document = Document.parse(json);
        Weather weather = converter.convert(document);
        Assert.assertEquals("Clouds", weather.main);
        Assert.assertEquals("scattered clouds", weather.description);
        Assert.assertEquals("03d", weather.icon);
        Assert.assertEquals(20, weather.temperature);
        Assert.assertEquals(42, weather.humidity);
        Assert.assertEquals(1020, weather.pressure);
        Assert.assertEquals(10000, weather.visibilty);
        Assert.assertEquals(5.1, weather.windSpeed, 0.01);
        Assert.assertEquals(240, weather.windDegree);
        Assert.assertEquals(40, weather.clouds);
        Assert.assertEquals(0, weather.minute);
        Assert.assertEquals(12, weather.hour);
        Assert.assertEquals(2017, weather.year);
        Assert.assertEquals(6, weather.month);
        Assert.assertEquals(8, weather.day);
    }
}