import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {



    public GUI()
    {
            super("Weather App");

            // Configure gui end process
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setSize(500, 400);

            setLocationRelativeTo(null);

            setLayout(null);

            setResizable(false);

            addGUIComponents();
    }

    private void addGUIComponents()
    {
        // Add GUI components here
        // Search bar
        JTextField searchBar = new JTextField();
        searchBar.setBounds(50, 30, 300, 35);
        searchBar.setFont(new Font("Calibri", Font.PLAIN, 20));
        add(searchBar);

        // Search button
        JButton searchButton = new JButton(loadImage("src/assets/search.png"));
        searchButton.setBounds(370, 30, 80, 35);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(searchButton);

        // Weather icons
        JLabel weatherConditionIcon = new JLabel(loadImage("src/assets/sunny.png"));
        weatherConditionIcon.setBounds(50, 100, 100, 100);
        add(weatherConditionIcon);

        // Temperature label
        JLabel temperatureLabel = new JLabel("25°C");
        temperatureLabel.setBounds(170, 100, 200, 100);
        temperatureLabel.setFont(new Font("Calibri", Font.PLAIN, 60));
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureLabel);

        // Weather condition description
        JLabel weatherConditionLabel = new JLabel("Sunny");
        weatherConditionLabel.setBounds(170, 160, 200, 50);
        weatherConditionLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        weatherConditionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionLabel);

        // Humidity label
        JLabel humidityLabel = new JLabel(loadImage("src/assets/humidity.png"));
        humidityLabel.setBounds(50, 220, 50, 50);
        add(humidityLabel);

        // Humidity value
        JLabel humidityValueLabel = new JLabel("60%");
        humidityValueLabel.setBounds(110, 220, 100, 50);
        humidityValueLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        add(humidityValueLabel);

        // Wind speed label
        JLabel windSpeedLabel = new JLabel(loadImage("src/assets/wind.png"));
        windSpeedLabel.setBounds(250, 220, 50, 50);
        add(windSpeedLabel);

        // Wind speed value
        JLabel windSpeedValueLabel = new JLabel("15 km/h");
        windSpeedValueLabel.setBounds(310, 220, 100, 50);
        windSpeedValueLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        add(windSpeedValueLabel);


    }

    private ImageIcon loadImage(String path)
    {
        try{
            // Load image from path
            BufferedImage img = ImageIO.read(new File(path));

            // Return as ImageIcon
            return new ImageIcon(img);

        }catch (IOException e){
            e.printStackTrace();

        }

        System.out.println("Image not found at path: " + path);
        return null;
    }


}
