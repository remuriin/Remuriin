import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Seat_Map_Display extends JPanel implements ActionListener
{
    private JButton testButton;

    public Seat_Map_Display()
    {
        setBackground(new Color(30, 30, 30));
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // You can draw custom graphics here later
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == testButton)
        {
            JOptionPane.showMessageDialog(this, "Button clicked!");
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame("Half Screen Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Get screen size
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width / 2;
            int height = screenSize.height / 2;

            // Set frame size to half of screen and center it
            frame.setSize(width, height);
            frame.setLocationRelativeTo(null);

            // Add panel
            frame.add(new Seat_Map_Display());

            frame.setVisible(true);
        });

        u1();
    }

    public static void u1()
    {
        testButton = new JButton("Click Me");
        testButton.addActionListener(this);
        add(testButton);
    }
}
