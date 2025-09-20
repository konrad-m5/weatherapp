import javax.swing.*;

public class Control
{

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new GUI().setVisible(true);
            }

        });



    }

}
