import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JFrame_Test
{
    private static boolean isFullscreen = false; // Track fullscreen state
    private static Rectangle windowedBounds;     // Remember last window size/position

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("F11 Fullscreen Toggle Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center window
        frame.setLayout(new BorderLayout());

        // Example label that adjusts on resize
        JLabel label = new JLabel("Press F11 to toggle fullscreen", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        frame.add(label, BorderLayout.CENTER);

        // Listen for resize events (including minimize, maximize, restore)
        frame.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                int width = frame.getWidth();
                int height = frame.getHeight();
                label.setText("Window size: " + width + " x " + height + " | Press F11 to toggle fullscreen");
            }
        });

        // Listen for F11 key press to toggle fullscreen
        frame.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_F11)
                {
                    GraphicsDevice device = GraphicsEnvironment
                            .getLocalGraphicsEnvironment()
                            .getDefaultScreenDevice();

                    if (!isFullscreen)
                    {
                        // Save current bounds before going fullscreen
                        windowedBounds = frame.getBounds();
                        frame.dispose(); // Must dispose before changing undecorated state
                        frame.setUndecorated(true);
                        frame.setVisible(true);
                        device.setFullScreenWindow(frame);
                        isFullscreen = true;
                    }
                    else
                    {
                        // Exit fullscreen
                        device.setFullScreenWindow(null);
                        frame.dispose();
                        frame.setUndecorated(false);
                        frame.setBounds(windowedBounds);
                        frame.setVisible(true);
                        isFullscreen = false;
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}
