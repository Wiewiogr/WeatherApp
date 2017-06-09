package WeatherApp.view;

import WeatherApp.controller.WeatherController;
import WeatherApp.model.Weather;

import javax.swing.*;
import java.nio.file.Watchable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by wiewiogr on 09.06.17.
 */
public class WeatherMainView {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JPanel hourlyWeather;
    private JPanel dailyWeather;
    private JLabel degrees;
    private JLabel currentTemp;
    private JLabel mainDesc;
    private JLabel currentHumidity;
    private JLabel currentVisibility;
    private JLabel currentWind;
    private JComboBox monthHourly;
    private JComboBox dayHourly;
    private JComboBox yearHourly;
    private JComboBox hourHourly;
    private JComboBox minuteHourly;
    private JLabel iconeLabel;
    private JLabel temperatureField;
    private JLabel humidityLabel;
    private JLabel visibilityLabel;
    private JLabel preasureLabel;
    private JLabel windSpeedLabel;
    private JLabel cloudLabel;
    private JComboBox dayDaily;
    private JComboBox monthDaily;
    private JComboBox yearDaily;
    private JLabel currentClouds;

    public WeatherMainView() {
        WeatherController controller = WeatherController.getInstance();

        Weather weather = controller.getCurrentWeather();
        int currentTemperature = weather.temperature;
        String mainDescription = weather.main;
        int humidity = weather.humidity;
        int visibility = weather.visibilty;
        int clouds = weather.clouds;
        double wind = weather.windSpeed;
        currentTemp.setText(currentTemperature+ "");
        mainDesc.setText(mainDescription);
        currentHumidity.setText(humidity+" %");
        currentVisibility.setText(visibility +" m");
        currentWind.setText(wind + " m/s");
        currentClouds.setText(clouds + " %");
    }

//    private void createUIComponents() {
//        //dailyWeather = new DailyWeatherView();
//        //hourlyWeather = new HourlyWeatherView();
//
//        // TODO: place custom component creation code here
//    }

    public static void main(String[] args) {
//        new WeatherMainView().createUIComponents();
        JFrame frame = new JFrame("WeatherMainView");
        WeatherMainView view = new WeatherMainView();
        frame.setContentPane(view.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
