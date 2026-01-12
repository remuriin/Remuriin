package Practice_Swing;
import javax.swing.JFrame;

import Practice_Swing.UI_Panels.Menu_Panel;

public class CinemaTest
{
    final static int SCREEN_W = 1536;
    final static int SCREEN_H = 864;
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Cinema Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(new Menu_Panel());
        frame.setVisible(true);
    }
}
