package Practice_Swing;

import javax.swing.JFrame;

public class CinemaTest
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Cinema Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
