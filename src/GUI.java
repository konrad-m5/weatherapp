// Needed imports
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class GUI extends JFrame
{
    private JSONObject weatherData;
    private JTextField searchBar;
    private JLabel weatherConditionIcon;
    private JLabel temperatureLabel;
    private JLabel weatherConditionLabel;
    private JLabel humidityLabel;
    private JLabel windSpeedValueLabel;
    private JButton searchButton;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JLabel windIcon;
    private JLabel humidityIcon;



    // GUI Constructor
    public GUI()
    {

            super("Weather App");

            // Initialize components that need to be accessed in multiple methods



            // Configure gui end process
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setSize(500, 600);

            setLocationRelativeTo(null);

            //setLayout(null);
            setLayout(new BorderLayout());

            setResizable(true);

            addGUIComponents();

    }// End GUI Constructor


    // Function to make the GUI
    private void addGUIComponents()
    {





        // Center panel for weather icon, temperature, and condition
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

	    // Used to add some space
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

	    // Weather image
	    weatherConditionIcon = new JLabel(loadImage("src/assets/sunny.png", 100, 100));
        weatherConditionIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(weatherConditionIcon);

	    // Space
	    centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

	    // Temperature label
         temperatureLabel = new JLabel("25°C");
        temperatureLabel.setFont(new Font("Calibri", Font.PLAIN, 60));
        temperatureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(temperatureLabel);

	    // Space
	    centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	    // Weather label
        weatherConditionLabel = new JLabel("Sunny");
        weatherConditionLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        weatherConditionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        weatherConditionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(weatherConditionLabel);

	    // Space
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	    // Making the whole central panel center
	    // and visibile
        add(centerPanel, BorderLayout.CENTER);

        // Wind and humidity on the same line
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        //bottomPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));


	    // Wind label and wind image
        windIcon = new JLabel(loadImage("src/assets/wind.png", 60, 60));
        bottomPanel.add(windIcon);
        windSpeedValueLabel = new JLabel("Wind: 15 km/h");
        windSpeedValueLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        bottomPanel.add(windSpeedValueLabel);

        // Add space
        bottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));

	    // Humidity label and humitidy image
        humidityIcon = new JLabel(loadImage("src/assets/humidity.png", 60, 60));
        bottomPanel.add(humidityIcon);
        humidityLabel = new JLabel("Humidity: 60%");
        humidityLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        bottomPanel.add(humidityLabel);

	    // Make the sure the bottom panel is at the bottom
	    // Make it visible
        add(bottomPanel, BorderLayout.SOUTH);

        // Top panel for search bar and search button
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));

        // Search bar
        searchBar = new JTextField();
        searchBar.setFont(new Font("Calibri", Font.PLAIN, 20));
        searchBar.setPreferredSize(new Dimension(300, 40));
        topPanel.add(searchBar);
        add(topPanel, BorderLayout.NORTH);

        // Search button
        searchButton = new JButton();
        searchButton.setIcon(loadImage("src/assets/search.png", 30, 30));
        searchButton.setPreferredSize(new Dimension(50, 40));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cityWeatherData();
            }

        });

        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cityWeatherData();
            }
        });

        topPanel.add(searchButton);

    }// End addGUIComponents

    // Used for loading and scaling the image
    private ImageIcon loadImage(String path, int width, int height)
    {

	    try
        {

            BufferedImage img = ImageIO.read(new File(path));

	        // Used to scale the image
	        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);

        }
        catch (IOException e)
        {

            e.printStackTrace();

        }// End try-catch

            // Error call
            System.out.println("Image not found at path: " + path);
            return null;

    }// End loadImage

    // Function to get weather data for the city the user inputted
    public void cityWeatherData()
    {

        // Get location
        String userInput = searchBar.getText();

        if (userInput.replaceAll("\\s", "").length() <= 0)
        {

            return;

        }// end if

        weatherData = WeatherApi.weatherData(userInput);

        if (weatherData == null)
        {

            weatherConditionLabel.setText("sorry that place does not exist");
            temperatureLabel.setText("--");
            humidityLabel.setText("--");
            windSpeedValueLabel.setText("--");
            weatherConditionIcon.setIcon(null); // Optionally clear the icon
            return;

        }
        else
        {

            String weatherCondition = weatherData.get("weatherCondition").toString();

            switch (weatherCondition)
            {
                case "Rain":
                    weatherConditionIcon.setIcon(loadImage("src/assets/rain.png", 100, 100));
                    break;

                case "Snow":
                    weatherConditionIcon.setIcon(loadImage("src/assets/snow.png", 100, 100));
                    break;

                case "Clear":
                    weatherConditionIcon.setIcon(loadImage("src/assets/sunny.png", 100, 100));
                    break;

                case "Cloudy":
                    weatherConditionIcon.setIcon(loadImage("src/assets/cloudy.png", 100, 100));
                    break;

            }

            double temperature = ((Number) weatherData.get("temperature")).doubleValue();
            temperatureLabel.setText(temperature + " C");

            weatherConditionLabel.setText(weatherCondition);

            double humidity = ((Number) weatherData.get("humidity")).doubleValue();
            humidityLabel.setText(humidity + " %");

            long windSpeed = ((Number) weatherData.get("windSpeed")).longValue();
            windSpeedValueLabel.setText(windSpeed + " km/h");

        }// end if else

    }// end cityWeatherData

}// End GUI class
