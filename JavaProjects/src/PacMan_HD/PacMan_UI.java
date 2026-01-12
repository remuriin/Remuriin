package PacMan_HD;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import javax.swing.ImageIcon;

public class PacMan_UI
{
    Graphics2D gfx;
    PacMan_Assets models;
    int playerX;
    int playerY;
    int[][] selectedMap;
    PacMan_MapBuilder build;
    PacMan_Sounds sounds;
    int screenWidth;
    int screenHeight;
    boolean debug;

    Image title = new ImageIcon(getClass().getResource("/PacMan_HD/assets/pac-man-10-9-2025.png")).getImage();

    Image playButton = new ImageIcon(getClass().getResource("/PacMan_HD/assets/PLAY-10-9-2025.png")).getImage();
    Image customMapsButton = new ImageIcon(getClass().getResource("/PacMan_HD/assets/CUSTOM-MAPS-10-11-2025.png")).getImage();
    Image settingsButton = new ImageIcon(getClass().getResource("/PacMan_HD/assets/SETTINGS-10-9-2025.png")).getImage();
    Image exitGameButton = new ImageIcon(getClass().getResource("/PacMan_HD/assets/EXIT-GAME-10-9-2025.png")).getImage();
    Image yesButton = new ImageIcon(getClass().getResource("/PacMan_HD/assets/YES-10-9-2025.png")).getImage();
    Image noButton = new ImageIcon(getClass().getResource("/PacMan_HD/assets/NO-10-9-2025.png")).getImage();

    Image quitTitle = new ImageIcon(getClass().getResource("/PacMan_HD/assets/QUIT-GAME-10-9-2025.png")).getImage();

    Image defaultMapsTitle = new ImageIcon(getClass().getResource("/PacMan_HD/assets/flamingtext_com-90543408.png")).getImage();
    Image customtMapsTitle = new ImageIcon(getClass().getResource("/PacMan_HD/assets/flamingtext_com-57950936.png")).getImage();


    public PacMan_UI(PacMan_Assets models, int playerX, int playerY, 
    int[][] selectedMap, PacMan_MapBuilder build, PacMan_Sounds sounds, 
    int screenWidth, int screenHeight, boolean debug)
    {
        this.models = models;
        this.selectedMap = selectedMap;
        this.build = build;
        this.sounds = sounds;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    protected void drawMenu(Graphics2D gfx, boolean debug)
    {
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, screenWidth, screenHeight);
        
        gfx.drawImage(title, 70, 30, 420, 120, null);

        gfx.setColor(Color.BLUE);
        gfx.fillRoundRect(140, 180, 280, 80, 20, 20);
        gfx.setColor(Color.BLACK);
        gfx.fillRoundRect(146, 186, 269, 68, 15, 15);
        gfx.setColor(Color.BLUE.darker());
        gfx.fillRoundRect(150, 191, 261, 59, 15, 15);
        gfx.drawImage(playButton, 213, 190, 135, 60, null);

        gfx.setColor(Color.BLUE);
        gfx.fillRoundRect(140, 270, 280, 80, 20, 20);
        gfx.setColor(Color.BLACK);
        gfx.fillRoundRect(146, 276, 269, 68, 15, 15);
        gfx.setColor(Color.BLUE.darker());
        gfx.fillRoundRect(150, 281, 261, 59, 15, 15);
        gfx.drawImage(customMapsButton, 140, 280, 280, 60, null);

        gfx.setColor(Color.BLUE);
        gfx.fillRoundRect(140, 360, 280, 80, 20, 20);
        gfx.setColor(Color.BLACK);
        gfx.fillRoundRect(146, 366, 269, 68, 15, 15);
        gfx.setColor(Color.BLUE.darker());
        gfx.fillRoundRect(150, 371, 261, 59, 15, 15);
        gfx.drawImage(settingsButton, 160, 365, 240, 70, null);

        gfx.setColor(Color.BLUE);
        gfx.fillRoundRect(140, 450, 280, 80, 20, 20);
        gfx.setColor(Color.BLACK);
        gfx.fillRoundRect(146, 456, 269, 68, 15, 15);
        gfx.setColor(Color.BLUE.darker());
        gfx.fillRoundRect(150, 461, 261, 59, 15, 15);
        gfx.drawImage(exitGameButton, 146, 455, 268, 70, null);

        if (debug)
        {
            gfx.setColor(new Color(255, 0, 0, 128));
            gfx.fillRoundRect(140, 180, 280, 80, 20, 20);
            gfx.fillRoundRect(140, 270, 280, 80, 20, 20);
            gfx.fillRoundRect(140, 360, 280, 80, 20, 20);
            gfx.fillRoundRect(140, 450, 280, 80, 20, 20);
        }
    }

    protected void drawSelectMap(Graphics2D gfx, boolean debug)
    {
        gfx.setColor(new Color(0, 0, 0, 200));
        gfx.fillRect(0, 0, screenWidth, screenHeight);

        gfx.setColor(Color.BLUE);
        gfx.fillRoundRect(120, 140, 320, 440, 20, 20);
        gfx.setColor(Color.BLUE.darker());
        gfx.fillRoundRect(130, 150, 300, 420, 20, 20);

        gfx.drawImage(defaultMapsTitle, 140, 160, 100, 10, null);
        gfx.drawImage(customtMapsTitle, 140, 310, 100, 10, null);
    }

    protected void drawcustomMaps(Graphics2D gfx, boolean debug)
    {
        gfx.setColor(new Color(0, 0, 0, 200));
        gfx.fillRect(0, 0, screenWidth, screenHeight);
    }

    protected void drawSettings(Graphics2D gfx, boolean debug)
    {
        gfx.setColor(new Color(0, 0, 0, 200));
        gfx.fillRect(0, 0, screenWidth, screenHeight);
    }

    protected void drawGame
    (
        Graphics2D gfx,
        int playerX,
        int playerY,
        int currentSpriteDirection,
        int currentSpriteFrame,
        boolean canMove,
        boolean debug
    )
    {
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, screenWidth, screenHeight);

        gfx.setColor(Color.BLUE);
        for (Shape block : build.wallBlocks)
        {
            gfx.fill(block);
        }

        gfx.setColor(Color.BLUE);
        for (Shape corner : build.corners)
        {
            gfx.fill(corner);
        }

        gfx.setColor(Color.WHITE);
        for (Shape gate : build.gates)
        {
            gfx.fill(gate);
        }

        gfx.setColor(Color.BLACK);
        for (Shape hollow : build.hollows)
        {
            gfx.fill(hollow);
        }

        gfx.setColor(Color.BLACK);
        for (Shape hollowCorner : build.hollowCorners)
        {
            gfx.fill(hollowCorner);
        }

        Color pelletColor = new Color(190, 180, 155);
        gfx.setColor(pelletColor);
        for (int row = 0; row < selectedMap.length; row++)
        {
            for (int col = 0; col < selectedMap[col].length; col++)
            {
                if (selectedMap[row][col] == 2)
                {
                    gfx.fillOval( col * 10 + 7, row * 10 + 67, 6, 6);
                } else if (selectedMap[row][col] == 3)
                {
                    gfx.fillOval(col * 10, row * 10 + 60, 20, 20);
                }
            }
        }
        
        if (canMove)
        {
            if (!sounds.intro.isRunning())
            {
                gfx.drawImage(models.pacman.get(currentSpriteDirection).get(currentSpriteFrame),
                playerX + 4, playerY + 4, 32, 32, null);
            } else
            {
                gfx.drawImage(models.pacman.get(currentSpriteDirection).get(0), 28, 48, null);
            }
        } else
        {
            gfx.drawImage(models.pacman.get(currentSpriteDirection).get(2), playerX + 4, playerY + 4, 32, 32, null);
        }

        if (debug)
        {
            gfx.setColor(new Color(255, 0, 0, 128));
            for (Shape invisibleWall : build.invisibleWalls)
            gfx.fill(invisibleWall);
        }
    }

    protected void drawPaused(Graphics2D gfx)
    {
        gfx.setColor(new Color(0, 0, 0, 200));
        gfx.fillRect(0, 0, screenWidth, screenHeight);
    }

    protected void drawExit(Graphics2D gfx)
    {
        gfx.setColor(new Color(0, 0, 0, 200));
        gfx.fillRect(0, 0, screenWidth, screenHeight);

        gfx.drawImage(quitTitle, 50, 185, 480, 105, null);

        gfx.setColor(Color.BLUE);
        gfx.fillRoundRect(100, 310, 170, 80, 20, 20);
        gfx.setColor(Color.BLACK);
        gfx.fillRoundRect(106, 316, 159, 68, 15, 15);
        gfx.setColor(Color.BLUE.darker());
        gfx.fillRoundRect(110, 321, 151, 59, 15, 15);
        gfx.drawImage(yesButton, 120, 320, 125, 60, null);

        gfx.setColor(Color.BLUE);
        gfx.fillRoundRect(290, 310, 170, 80, 20, 20);
        gfx.setColor(Color.BLACK);
        gfx.fillRoundRect(296, 316, 159, 68, 15, 15);
        gfx.setColor(Color.BLUE.darker());
        gfx.fillRoundRect(300, 321, 151, 59, 15, 15);
        gfx.drawImage(noButton, 325, 320, 100, 60, null);
    }
}
