package WeatherApp.view;

import WeatherApp.controller.WeatherController;
import WeatherApp.model.Weather;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.nio.file.Watchable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private JLabel icon;
    private JComboBox dayDaily;
    private JComboBox monthDaily;
    private JComboBox yearDaily;
    private JLabel currentClouds;
    private JTable weatherTable;
    private JLabel weatherIcon;
    private JLabel hourlyTemp;
    private JLabel hourlyHumidity;
    private JLabel hourlyVisibility;
    private JLabel hourlyPressure;
    private JLabel hourlyWindSpeed;
    private JLabel hourlyCloud;
    private JLabel preasureLabel;
    private JLabel windSpeedLabel;
    private JLabel cloudLabel;
    private JLabel visibilityLabel;
    private JLabel humidityLabel;
    private JLabel temperatureField;
    private JButton checkWeatherButton;
    private JLabel iconLabel;
    private JButton checkDailyWeatherButton;
    DefaultTableModel model;

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

        ImageIcon icon = new ImageIcon(getIconPath(weather));
        weatherIcon.setIcon(icon);

        model = new DefaultTableModel();
        model.addColumn("Hour");
        model.addColumn("Temperature");
        model.addColumn("Humidity");
        model.addColumn("Visibility");
        model.addColumn("Pressure");
        model.addColumn("Wind Speed");
        model.addColumn("Clouds");

        weatherTable.setModel(model);

        checkWeatherButton.addActionListener(e -> setHourlyWeather());
        checkDailyWeatherButton.addActionListener(e -> setDailyWeather());
    }

    private String getIconPath(Weather weather){
        return new StringBuilder().append("src/main/java/WeatherApp/view/icon/")
                .append(weather.icon)
                .append(".png")
                .toString();
    }

    private void setDailyWeather() {
        int day = dayDaily.getSelectedIndex()+1;
        int month = monthDaily.getSelectedIndex()+1;
        int year = yearDaily.getSelectedIndex()+2017;

        WeatherController controller = WeatherController.getInstance();
        List<Weather> weatherList = controller.getWeatherFromDay(year,month,day);
        if( weatherList.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "No data available for this day.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        DefaultTableModel tableModel = (DefaultTableModel) weatherTable.getModel();

        int rowCount = tableModel.getRowCount();
        for( int i = rowCount-1; i >= 0; i-- ) {
            tableModel.removeRow(i);
        }

        Object[] row = new Object[7];
        for(Weather weather: weatherList)
        {
            String hour = weather.hour + ":" + weather.minute;
            if( weather.minute == 0 )
                hour += "0";
            row[0] = hour;
            row[1] = weather.temperature;
            row[2] = weather.humidity;
            row[3] = weather.visibilty;
            row[4] = weather.pressure;
            row[5] = weather.windSpeed;
            row[6] = weather.clouds;
            tableModel.addRow(row);
        }
    }

    private void setHourlyWeather() {
        int day = dayHourly.getSelectedIndex()+1;
        int month = monthHourly.getSelectedIndex()+1;
        int year = yearHourly.getSelectedIndex()+2017;
        int hour = hourHourly.getSelectedIndex();
        int minute = minuteHourly.getSelectedIndex()*30;

        WeatherController controller = WeatherController.getInstance();
        Weather weather = controller.getWeatherFromMinute(year,month,day,hour,minute);

        if( weather == null ) {
            JOptionPane.showMessageDialog(null, "No data available for this hour.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int currentTemperature = weather.temperature;
        int humidity = weather.humidity;
        int visibility = weather.visibilty;
        int pressure = weather.pressure;
        double wind = weather.windSpeed;
        int clouds = weather.clouds;
        hourlyTemp.setText(currentTemperature+ " °C");
        hourlyHumidity.setText(humidity + " %");
        hourlyVisibility.setText(visibility + " m");
        hourlyPressure.setText(pressure + " hPa");
        hourlyWindSpeed.setText(wind + " m/s");
        hourlyCloud.setText(clouds + " %");

        ImageIcon icon = new ImageIcon(getIconPath(weather));
        iconLabel.setIcon(icon);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WeatherMainView");
        WeatherMainView view = new WeatherMainView();
        frame.setContentPane(view.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
