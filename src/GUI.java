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
        searchBar.setFont(new Font("Serif", Font.PLAIN, 20));
        add(searchBar);

        // Search button
        JButton searchButton = new JButton(loadImage("src/assets/search.png"));
        searchButton.setBounds(370, 30, 80, 35);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(searchButton);

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
