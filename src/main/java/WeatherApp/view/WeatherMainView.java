package WeatherApp.view;

import javax.swing.*;

/**
 * Created by wiewiogr on 09.06.17.
 */
public class WeatherMainView {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel hourlyWeather;
    private JPanel dailyWeather;

    private void createUIComponents() {
        dailyWeather = new DailyWeatherView();
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WeatherMainView");
        frame.setContentPane(new WeatherMainView().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
