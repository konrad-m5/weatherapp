import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class GUI extends JFrame {



    public GUI()
    {
            super("Weather App");

            // Configure gui end process
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setSize(500, 600);

            setLocationRelativeTo(null);

            //setLayout(null);
            setLayout(new BorderLayout());

            setResizable(true);

            addGUIComponents();
    }


    private void addGUIComponents() {
        // Top panel for search bar and search button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));


        JTextField searchBar = new JTextField();
        searchBar.setFont(new Font("Calibri", Font.PLAIN, 20));
        searchBar.setPreferredSize(new Dimension(300, 40));
        topPanel.add(searchBar);
        add(topPanel, BorderLayout.NORTH);

        JButton searchButton = new JButton();
        searchButton.setIcon(loadImage("src/assets/search.png", 30, 30));
        searchButton.setPreferredSize(new Dimension(50, 40));
        topPanel.add(searchButton);


        // Center panel for weather icon, temperature, and condition
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        JLabel weatherConditionIcon = new JLabel(loadImage("src/assets/sunny.png", 100, 100));
        weatherConditionIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(weatherConditionIcon);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel temperatureLabel = new JLabel("25°C");
        temperatureLabel.setFont(new Font("Calibri", Font.PLAIN, 60));
        temperatureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(temperatureLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel weatherConditionLabel = new JLabel("Sunny");
        weatherConditionLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        weatherConditionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        weatherConditionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(weatherConditionLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        add(centerPanel, BorderLayout.CENTER);

        // Wind and humidity on the same line
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        //bottomPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));


        JLabel windIcon = new JLabel(loadImage("src/assets/wind.png", 60, 60));
        bottomPanel.add(windIcon);
        JLabel windSpeedValueLabel = new JLabel("Wind: 15 km/h");
        windSpeedValueLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        bottomPanel.add(windSpeedValueLabel);

        // Add space
        bottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        JLabel humidityIcon = new JLabel(loadImage("src/assets/humidity.png", 60, 60));
        bottomPanel.add(humidityIcon);
        JLabel humidityLabel = new JLabel("Humidity: 60%");
        humidityLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        bottomPanel.add(humidityLabel);


        add(bottomPanel, BorderLayout.SOUTH);


    }

    private ImageIcon loadImage(String path, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Image not found at path: " + path);
        return null;
    }

}
