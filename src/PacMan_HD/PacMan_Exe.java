package PacMan_HD;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PacMan_Exe extends JPanel implements ActionListener
{
    Timer timer;
    final int fps = 165;

    final int SCREEN_W = 560;
    final int SCREEN_H = 720;
    final int TILE_SIZE = 10;

    int score = 0;

    int playerX = 26 * 10;
    int playerY = 51 * 10;
    final int playerW = 32;
    final int playerH = 32;

    private int spriteFrameCount = 0;
    private int currentSpriteFrame = 2;
    private int currentSpriteDirection = 0;
    private final int spriteFrameDelay = 7;
    
    final int hitBoxW = 40;
    final int hitBoxH = 40;

    final int speed = 1;
    static private boolean debug = false;

    String currentD = "NONE";
    String desiredD = "NONE";

    PacMan_MapBuilder build = new PacMan_MapBuilder();
    PacMan_MapLibrary map = new PacMan_MapLibrary();
    PacMan_Assets models = new PacMan_Assets();
    PacMan_Sounds sounds = new PacMan_Sounds();

    int[][] selectedMap = map.classic;

    Graphics gfx;
    PacMan_UI UI = new PacMan_UI
    (
        models,
        playerX,
        playerY,
        selectedMap,
        build,
        sounds,
        SCREEN_W,
        SCREEN_H,
        debug
    );

    GameState currentState = GameState.MENU;
    boolean introStart = false;

    enum GameState
    {
        MENU,
        SELECT_MAP,
        CUSTOM_MAP,
        SETTINGS,
        PLAYING,
        PAUSED,
        GAMEOVER,
        EXIT
    }

    public PacMan_Exe()
    {
        setPreferredSize(new Dimension(SCREEN_W, SCREEN_H));
        addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e)
            {
                java.awt.Point p = e.getPoint();
                if (null != currentState)
                {
                    switch (currentState)
                    {
                        case MENU ->
                        {
                            Rectangle playBtn = new Rectangle(140, 180, 280, 80);
                            Rectangle createMapBtn = new Rectangle(140, 270, 280, 80);
                            Rectangle settingsBtn = new Rectangle(140, 360, 280, 80);
                            Rectangle exitBtn = new Rectangle(140, 450, 280, 80);

                            if (playBtn.contains(p))
                            {
                                currentState = GameState.PLAYING;
                            }
                            else if (createMapBtn.contains(p))
                            {
                                currentState = GameState.CUSTOM_MAP;
                            }
                            else if (settingsBtn.contains(p))
                            {
                                currentState = GameState.SETTINGS;
                            }
                            else if (exitBtn.contains(p))
                            {
                                currentState = GameState.EXIT;
                            }
                        }
                        case SELECT_MAP ->
                        {

                        }
                        case CUSTOM_MAP ->
                        {

                        }
                        case SETTINGS ->
                        {

                        }
                        case EXIT ->
                        {
                            Rectangle yesBtn = new Rectangle(100, 310, 170, 80);
                            Rectangle noBtn = new Rectangle(290, 310, 170, 80);

                            if (yesBtn.contains(p))
                            {
                                System.exit(0);
                            }
                            else if (noBtn.contains(p))
                            {
                                currentState = GameState.MENU;
                            }
                        }
                        case PLAYING ->
                        {

                        }
                    }
                }
            }
        });

        setFocusable(true);
        build.generateMap(selectedMap, true, 2);
        setupKeyBindings();
        
        timer = new Timer(1000/fps, this);
        timer.start();
    }

    private void setupKeyBindings()
    {
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        bindKey(im, am, "pressed A", "leftPressed", () -> desiredD = "LEFT");
        bindKey(im, am, "pressed D", "rightPressed", () -> desiredD = "RIGHT");
        bindKey(im, am, "pressed W", "upPressed", () -> desiredD = "UP");
        bindKey(im, am, "pressed S", "downPressed", () -> desiredD = "DOWN");

        bindKey(im, am, "pressed F1", "showDebugWall", () ->
        {
            if (currentState != GameState.PAUSED)
            {
                debug = !debug;
            }
        });
        
        bindKey(im, am, "pressed ESCAPE", "back", () ->
        {
            currentState = switch (currentState)
            {
                case MENU ->
                {
                    yield GameState.EXIT;
                }
                case EXIT, SELECT_MAP, CUSTOM_MAP, SETTINGS ->
                {
                    yield GameState.MENU;
                }
                case PLAYING ->
                {
                    if (!sounds.intro.isRunning())
                    {
                        yield GameState.PAUSED;
                    }
                    else
                    {
                        yield GameState.PLAYING;
                    }
                }
                case PAUSED ->
                {
                    yield GameState.PLAYING;
                }
                default ->
                {
                    yield currentState;
                }
            };
        });
    }

    private void bindKey(InputMap im, ActionMap am, String keyStroke, String name, Runnable action)
    {
        im.put(KeyStroke.getKeyStroke(keyStroke), name);
        am.put(name, new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                action.run();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (currentState == GameState.PLAYING)
        {
            if (introStart)
            {
                sounds.intro.play(0.05f);
                introStart = false;
            }
            if (canMove(desiredD) && !sounds.intro.isRunning())
            {
                currentD = desiredD;
            }
            
            if (playerX < -40)
            {
                playerX = SCREEN_W;
            }
            else if (playerX > 560)
            {
                playerX = -playerW;
            }
    
            int nextX = playerX;
            int nextY = playerY;
    
            switch (currentD)
            {
                case "RIGHT":
                {
                    nextX += speed;
                    currentSpriteDirection = 0;
                    break;
                }
                case "LEFT":
                {
                    nextX -= speed;
                    currentSpriteDirection = 1;
                    break;
                }
                case "UP":
                {
                    nextY -= speed;
                    currentSpriteDirection = 2;
                    break;
                }
                case "DOWN":
                {
                    nextY += speed;
                    currentSpriteDirection = 3;
                    break;
                }
            }
    
            if (!collidesWithLookahead(playerX, playerY, nextX - playerX, 0))
            {
                playerX = nextX;
            }
            if (!collidesWithLookahead(playerX, playerY, 0, nextY - playerY))
            {
                playerY = nextY;
            }
            boolean eating;
            eating = canEat();
            if (!sounds.chomp.isRunning() && eating)
            {
                sounds.chomp.playWithCooldown(165, 40, 0.01f);
            }
            spriteFrameCount++;
            if (spriteFrameCount == spriteFrameDelay)
            {
                currentSpriteFrame++;
                if (currentSpriteFrame > 3)
                {
                    currentSpriteFrame = 0;
                }
                spriteFrameCount = 0;
            }
        }
        System.out.println(score);
        repaint();
    }

    private boolean canMove(String dir)
    {
        switch (dir)
        {
            case "LEFT":
            {
                return !collidesWithLookahead(playerX, playerY, -1, 0);
            }
            case "RIGHT":
            {
                return !collidesWithLookahead(playerX, playerY, 1, 0);
            }
            case "UP":
            {
                return !collidesWithLookahead(playerX, playerY, 0, -1);
            }
            case "DOWN":
            {
                return !collidesWithLookahead(playerX, playerY, 0, 1);
            }
        }
        return false;
    }

    static boolean eating = false;
    private boolean canEat()
    {
        int eatW = (int)(hitBoxW * 0.50);
        int eatH = (int)(hitBoxH * 0.50);

        int eatX = playerX + (hitBoxW - eatW) / 2;
        int eatY = playerY + (hitBoxH - eatH) / 2;

        Rectangle eatRect = new Rectangle(eatX, eatY, eatW, eatH);

        for (int row = 0; row < selectedMap.length; row++)
        {
            for (int col = 0; col < selectedMap[row].length; col++)
            {
                if (selectedMap[row][col] == 2)
                {
                    Rectangle orbRect = new Rectangle(col * TILE_SIZE + 7, row * TILE_SIZE + 67, 6, 6);
                    if (eatRect.intersects(orbRect))
                    {
                        selectedMap[row][col] = 0;
                        score++;
                        return true;
                    }
                } else if (selectedMap[row][col] == 3)
                {
                    Rectangle orbRect = new Rectangle(col * TILE_SIZE + 7, row * TILE_SIZE + 67, 6, 6);
                    if (eatRect.intersects(orbRect))
                    {
                        selectedMap[row][col] = 0;
                        score += 5;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean collidesWithLookahead(int x, int y, int dx, int dy)
    {
        int lookahead = 1;

        int checkX = x + (dx > 0 ? lookahead : dx < 0 ? -lookahead : 0);
        int checkY = y + (dy > 0 ? lookahead : dy < 0 ? -lookahead : 0);

        Rectangle rect = new Rectangle(checkX, checkY, hitBoxW, hitBoxH);

        for (Shape solidBlock : build.invisibleWalls)
        {
            if (solidBlock.intersects(rect))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gfx = (Graphics2D) g;

        switch (currentState)
        {
            case MENU:
            {
                UI.drawMenu(gfx, debug);
                break;
            }
            case SELECT_MAP:
            {
                UI.drawMenu(gfx, debug);
                UI.drawSelectMap(gfx, debug);
                break;
            }
            case CUSTOM_MAP:
            {
                UI.drawMenu(gfx, debug);
                UI.drawcustomMaps(gfx, debug);
                break;
            }
            case SETTINGS:
            {
                UI.drawMenu(gfx, debug);
                UI.drawSettings(gfx, debug);
                break;
            }
            case PLAYING:
            {
                UI.drawGame
                (
                    gfx,
                    playerX,
                    playerY,
                    currentSpriteDirection,
                    currentSpriteFrame,
                    canMove(currentD),
                    debug
                );
                break;
            }
            case PAUSED:
            {
                UI.drawGame(gfx, playerX, playerY, currentSpriteDirection,
                currentSpriteFrame, canMove(currentD), debug);
                UI.drawPaused(gfx);
                break;
            }
            case GAMEOVER:
            {

                break;
            }
            case EXIT:
            {
                UI.drawMenu(gfx, debug);
                UI.drawExit(gfx);
                break;
            }
        }
    }
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame("Pacman_HD");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            PacMan_Exe panel = new PacMan_Exe();
            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
