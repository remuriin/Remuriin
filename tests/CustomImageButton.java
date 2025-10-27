import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomImageButton extends JPanel implements ActionListener
{
    private JButton imageButton;

    public CustomImageButton()
    {
        setLayout(null);
        setBackground(new Color(25, 25, 25));

        // Load image icon (use your actual path here)
        ImageIcon icon = new ImageIcon("playEXIT-GAME-10-9-2025.png");

        // Resize the icon if needed
        Image scaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        // Create the button with the image
        imageButton = new JButton(icon);

        // Remove the default button border and background
        imageButton.setBorderPainted(false);
        imageButton.setFocusPainted(false);
        imageButton.setContentAreaFilled(false);

        // Optional: tooltip and cursor
        imageButton.setToolTipText("Play");
        imageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Set button position and size
        imageButton.setBounds(100, 100, 64, 64);

        // Add listener
        imageButton.addActionListener(this);

        // Add to panel
        add(imageButton);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == imageButton)
        {
            System.out.println("Play button clicked!");
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame("Custom Image Button");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            frame.add(new CustomImageButton());
            frame.setVisible(true);
        });
    }
}
