package PacMan_HD;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.*;

public class PacMan_Assets
{
    ArrayList<ArrayList<Image>> pacman = new ArrayList<>();

    ArrayList<Image> pacmanLeft = new ArrayList<>();
    ArrayList<Image> pacmanRight = new ArrayList<>();
    ArrayList<Image> pacmanUp = new ArrayList<>();
    ArrayList<Image> pacmanDown = new ArrayList<>();

    public PacMan_Assets()
    {
        loadSprites();
    }

    private void loadSprites()
    {
        pacmanLeft.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanLeft_frame1.png")).getImage());
        pacmanRight.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanRight_frame1.png")).getImage());
        pacmanUp.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanUp_frame1.png")).getImage());
        pacmanDown.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanDown_frame1.png")).getImage());

        pacmanLeft.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanLeft_frame2.png")).getImage());
        pacmanRight.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanRight_frame2.png")).getImage());
        pacmanUp.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanUp_frame2.png")).getImage());
        pacmanDown.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanDown_frame2.png")).getImage());

        pacmanLeft.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacman_frame3.png")).getImage());
        pacmanRight.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacman_frame3.png")).getImage());
        pacmanUp.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacman_frame3.png")).getImage());
        pacmanDown.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacman_frame3.png")).getImage());

        pacmanLeft.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanLeft_frame2.png")).getImage());
        pacmanRight.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanRight_frame2.png")).getImage());
        pacmanUp.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanUp_frame2.png")).getImage());
        pacmanDown.add(new ImageIcon(getClass().getResource("/PacMan_HD/assets/pacmanDown_frame2.png")).getImage());
        
        pacman.add(pacmanRight);
        pacman.add(pacmanLeft);
        pacman.add(pacmanUp);
        pacman.add(pacmanDown);
    }
}