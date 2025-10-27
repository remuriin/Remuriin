package Practice_Swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Menu_Panel extends JPanel implements ActionListener
{
    private final int SCREEN_W = CinemaTest.SCREEN_W;
    private final int SCREEN_H = CinemaTest.SCREEN_H;

    private Image main = ResourceLoader.loadImage("Main.png");

    @Override
    public void actionPerformed(ActionEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gfx = (Graphics2D) g;

        gfx.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
                             java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        gfx.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING,
                             java.awt.RenderingHints.VALUE_RENDER_QUALITY);
        gfx.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                             java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        if (main != null)
        {
            gfx.drawImage(main, 50, 50, SCREEN_W, SCREEN_H, null);
        }
    }
}
