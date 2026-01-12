import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class gameTest extends JPanel implements ActionListener {
    static final int SCREEN_W = 448;
    static final int SCREEN_H = 576;

    static String currentDir = "NONE";
    static String desiredDir = "NONE";

    static String pressedKey = "";

    int x = 12, y = 60; // player position
    final int w = 24, h = 24; // player size
    final int speed = 2;

    final int hitBoxW = 16;
    final int hitBoxH = 16;
    final int hitBoxOffsetX = (w - hitBoxW) / 2;
    final int hitBoxOffsetY = (h - hitBoxH) / 2;

    int dx = 0, dy = 0; // movement vector

    Timer timer;
    ArrayList<Shape> walls = new ArrayList<>();
    ArrayList<Shape> hollows = new ArrayList<>();
    ArrayList<Shape> orbsSmall = new ArrayList<>();
    ArrayList<Shape> orbsBig = new ArrayList<>();
    ArrayList<Shape> passableHollows = new ArrayList<>();
    ArrayList<Shape> invisibleWalls = new ArrayList<>();
    boolean showDebugWalls = false;

    public gameTest() {
        setPreferredSize(new Dimension(SCREEN_W, SCREEN_H));
        setFocusable(true);

        createManualWalls();
        orbPlacer();
        setupKeyBindings();

        timer = new Timer(16, this);
        timer.start();
    }

    private void setupKeyBindings() {
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        bindKey(im, am, "pressed A", "leftPressed", () -> desiredDir = "LEFT");
        bindKey(im, am, "pressed D", "rightPressed", () -> desiredDir = "RIGHT");
        bindKey(im, am, "pressed W", "upPressed", () -> desiredDir = "UP");
        bindKey(im, am, "pressed S", "downPressed", () -> desiredDir = "DOWN");

        bindKey(im, am, "pressed F1", "toggleDebug", () -> showDebugWalls = !showDebugWalls); // toggle invisible walls
    }

    private void bindKey(InputMap im, ActionMap am, String keyStroke, String name, Runnable action) {
        im.put(KeyStroke.getKeyStroke(keyStroke), name);
        am.put(name, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
    }

    private void createManualWalls() {
        // ====================UPPER HALF==========================
        // border
        topOuterBorder(0, 256, 80);
        lowerRightInnerCorner(80, 256);
        leftOuterBorder(80, 208, 48);
        upperRightInnerCorner(80, 192);
        bottomOuterBorder(16, 192, 64);
        lowerLeftOuterCorner(0, 192);
        leftOuterBorder(0, 64, 128);
        upperLeftOuterCorner(0, 48);
        topOuterBorder(16, 48, 416);
        upperRightOuterCorner(208, 48);
        upperLeftOuterCorner(224, 48);
        rightInnerBorder(208, 64, 48);
        leftInnerBorder(224, 64, 48);
        lowerLeftInnerCorner(208, 112);
        lowerRightInnerCorner(224, 112);
        hollows.add(new Rectangle(218, 50, 12, 14));
        upperRightOuterCorner(SCREEN_W - 16, 48);
        rightOuterBorder(SCREEN_W - 16, 64, 128);
        lowerRightOuterCorner(432, 192);
        bottomOuterBorder(368, 192, 64);
        upperLeftInnerCorner(352, 192);
        rightOuterBorder(352, 208, 48);
        lowerLeftInnerCorner(352, 256);
        topOuterBorder(368, 256, 80);

        // obstacles
        // left 3x4 block
        obstacleBlockGenerator(32, 80, 4, 3);
        // left 3x5 block
        obstacleBlockGenerator(112, 80, 5, 3);
        // right 3x5 block
        obstacleBlockGenerator(256, 80, 5, 3);
        // right 3x4 block
        obstacleBlockGenerator(352, 80, 4, 3);
        // left 2x4 block
        obstacleBlockGenerator(32, 144, 4, 2);
        // right 2x4 block
        obstacleBlockGenerator(352, 144, 4, 2);
        // middle T block
        obstacleBlockGenerator(160, 144, 8, 2);
        obstacleBlockGenerator(208, 160, 2, 4);
        upperRightOuterCorner(208, 160);
        upperLeftOuterCorner(224, 160);
        hollows.add(new Rectangle(208, 154, 32, 12));
        hollows.add(new Rectangle(218, 166, 12, 10));
        // left T block
        obstacleBlockGenerator(112, 144, 2, 8);
        obstacleBlockGenerator(128, 192, 4, 2);
        lowerLeftOuterCorner(128, 192);
        upperLeftOuterCorner(128, 208);
        hollows.add(new Rectangle(122, 192, 12, 32));
        hollows.add(new Rectangle(134, 202, 10, 12));
        // right T block
        obstacleBlockGenerator(304, 144, 2, 8);
        obstacleBlockGenerator(256, 192, 4, 2);
        lowerRightOuterCorner(304, 192);
        upperRightOuterCorner(304, 208);
        hollows.add(new Rectangle(314, 192, 12, 32));
        hollows.add(new Rectangle(304, 202, 10, 12));

        // ====================CAGE BLOCK==========================
        walls.add(new Rectangle(206, 250, 2, 4));
        walls.add(new Rectangle(240, 250, 2, 4));
        walls.add(new Rectangle(168, 248, 40, 2));
        walls.add(new Rectangle(240, 248, 40, 2));
        walls.add(new Rectangle(174, 254, 34, 2));
        walls.add(new Rectangle(240, 254, 34, 2));
        walls.add(new Rectangle(168, 250, 2, 60));
        walls.add(new Rectangle(278, 250, 2, 60));
        walls.add(new Rectangle(174, 256, 2, 48));
        walls.add(new Rectangle(272, 256, 2, 48));
        walls.add(new Rectangle(174, 304, 100, 2));
        walls.add(new Rectangle(168, 310, 112, 2));

        // ====================LOWER HALF==========================
        // border
        bottomOuterBorder(0, 288, 80);
        upperRightInnerCorner(80, 288);
        leftOuterBorder(80, 304, 48);
        lowerRightInnerCorner(80, 352);
        topOuterBorder(16, 352, 64);
        upperLeftOuterCorner(0, 352);
        leftOuterBorder(0, 368, 160);
        obstacleBlockGenerator(0, 432, 3, 2);
        lowerLeftOuterCorner(0, 432);
        upperLeftOuterCorner(0, 448);
        hollows.add(new Rectangle(2, 442, 14, 12));
        lowerLeftOuterCorner(0, 528);
        bottomOuterBorder(16, 528, 416);
        lowerRightOuterCorner(432, 528);
        rightOuterBorder(432, 368, 160);
        obstacleBlockGenerator(400, 432, 3, 2);
        lowerRightOuterCorner(432, 432);
        upperRightOuterCorner(432, 448);
        hollows.add(new Rectangle(432, 442, 14, 12));
        upperRightOuterCorner(432, 352);
        topOuterBorder(368, 352, 64);
        lowerLeftInnerCorner(352, 352);
        rightOuterBorder(352, 304, 48);
        upperLeftInnerCorner(352, 288);
        bottomOuterBorder(368, 288, 80);

        // obstacles
        // left 5x2 block
        obstacleBlockGenerator(112, 288, 2, 5);
        // right 5x2 block
        obstacleBlockGenerator(304, 288, 2, 5);
        // upper middle T block
        obstacleBlockGenerator(160, 336, 8, 2);
        obstacleBlockGenerator(208, 352, 2, 4);
        upperRightOuterCorner(208, 352);
        upperLeftOuterCorner(224, 352);
        hollows.add(new Rectangle(208, 346, 32, 12));
        hollows.add(new Rectangle(218, 358, 12, 10));
        // left angled block
        obstacleBlockGenerator(31, 384, 4, 2);
        obstacleBlockGenerator(64, 384, 2, 5);
        upperRightOuterCorner(64, 400);
        hollows.add(new Rectangle(64, 394, 16, 12));
        hollows.add(new Rectangle(74, 400, 12, 16));
        // left 2x5 block
        obstacleBlockGenerator(112, 384, 5, 2);
        // right 2x5 block
        obstacleBlockGenerator(256, 384, 5, 2);
        // right angled block
        obstacleBlockGenerator(352, 384, 4, 2);
        obstacleBlockGenerator(352, 384, 2, 5);
        upperLeftOuterCorner(368, 400);
        hollows.add(new Rectangle(368, 394, 16, 12));
        hollows.add(new Rectangle(362, 400, 12, 16));
        // lower middle T block
        obstacleBlockGenerator(160, 432, 8, 2);
        obstacleBlockGenerator(208, 448, 2, 4);
        upperRightOuterCorner(208, 448);
        upperLeftOuterCorner(224, 448);
        hollows.add(new Rectangle(208, 442, 32, 12));
        hollows.add(new Rectangle(218, 454, 12, 10));
        // lower left T block
        obstacleBlockGenerator(32, 480, 10, 2);
        obstacleBlockGenerator(112, 432, 2, 4);
        lowerRightOuterCorner(112, 480);
        lowerLeftOuterCorner(128, 480);
        hollows.add(new Rectangle(112, 490, 32, 12));
        hollows.add(new Rectangle(122, 480, 12, 10));
        // lower right T block
        obstacleBlockGenerator(256, 480, 10, 2);
        obstacleBlockGenerator(304, 432, 2, 4);
        lowerRightOuterCorner(304, 480);
        lowerLeftOuterCorner(320, 480);
        hollows.add(new Rectangle(304, 490, 32, 12));
        hollows.add(new Rectangle(314, 480, 12, 10));

        // invisible walls
        createInvisibleCollisionWalls();
    }

    private void createInvisibleCollisionWalls() {
        // ============================= WALLS ================================
        // upper half
        invisibleWalls.add(new Rectangle(0, 256, 96, 16));
        invisibleWalls.add(new Rectangle(80, 208, 16, 48));
        invisibleWalls.add(new Rectangle(16, 48, 416, 16));
        invisibleWalls.add(new Rectangle(0, 64, 16, 128));
        invisibleWalls.add(new Rectangle(16, 192, 80, 16));
        invisibleWalls.add(new Rectangle(208, 64, 32, 64));
        invisibleWalls.add(new Rectangle(432, 64, 16, 128));
        invisibleWalls.add(new Rectangle(352, 192, 80, 16));
        invisibleWalls.add(new Rectangle(352, 208, 16, 48));
        invisibleWalls.add(new Rectangle(352, 256, 96, 16));

        // lower half
        invisibleWalls.add(new Rectangle(0, 288, 96, 16));
        invisibleWalls.add(new Rectangle(80, 304, 16, 48));
        invisibleWalls.add(new Rectangle(16, 352, 80, 16));
        invisibleWalls.add(new Rectangle(0, 368, 16, 160));
        invisibleWalls.add(new Rectangle(16, 432, 32, 32));
        invisibleWalls.add(new Rectangle(16, 528, 416, 16));
        invisibleWalls.add(new Rectangle(432, 368, 16, 160));
        invisibleWalls.add(new Rectangle(400, 432, 32, 32));
        invisibleWalls.add(new Rectangle(352, 352, 80, 16));
        invisibleWalls.add(new Rectangle(352, 304, 16, 48));
        invisibleWalls.add(new Rectangle(352, 288, 96, 16));

        // ============================= OBSTACLES ================================
        // upper half
        // left 3x4 block
        invisibleWalls.add(new Rectangle(32, 80, 64, 48));
        // left 3x5 block
        invisibleWalls.add(new Rectangle(112, 80, 80, 48));
        // right 3x5 block
        invisibleWalls.add(new Rectangle(256, 80, 80, 48));
        // right 3x4 block
        invisibleWalls.add(new Rectangle(352, 80, 64, 48));
        // left 2x4 block
        invisibleWalls.add(new Rectangle(32, 144, 64, 32));
        // right 2x4 block
        invisibleWalls.add(new Rectangle(352, 144, 64, 32));
        // middle T block
        invisibleWalls.add(new Rectangle(160, 144, 128, 32));
        invisibleWalls.add(new Rectangle(208, 176, 32, 48));
        // left T block
        invisibleWalls.add(new Rectangle(112, 144, 32, 128));
        invisibleWalls.add(new Rectangle(144, 192, 48, 32));
        // right T block
        invisibleWalls.add(new Rectangle(304, 144, 32, 128));
        invisibleWalls.add(new Rectangle(256, 192, 48, 32));

        // cage
        invisibleWalls.add(new Rectangle(160, 240, 128, 16));
        invisibleWalls.add(new Rectangle(160, 256, 16, 48));
        invisibleWalls.add(new Rectangle(160, 304, 128, 16));
        invisibleWalls.add(new Rectangle(272, 256, 16, 48));

        // lower half
        invisibleWalls.add(new Rectangle(112, 288, 32, 80));
        invisibleWalls.add(new Rectangle(304, 288, 32, 80));
        // upper middle T block
        invisibleWalls.add(new Rectangle(160, 336, 128, 32));
        invisibleWalls.add(new Rectangle(208, 368, 32, 48));
        // left angled block
        invisibleWalls.add(new Rectangle(32, 384, 64, 32));
        invisibleWalls.add(new Rectangle(64, 416, 32, 48));
        // right angled block
        invisibleWalls.add(new Rectangle(352, 384, 64, 32));
        invisibleWalls.add(new Rectangle(352, 416, 32, 48));
        // left 2x5 block
        invisibleWalls.add(new Rectangle(112, 384, 80, 32));
        invisibleWalls.add(new Rectangle(256, 384, 80, 32));
        // lower middle T block
        invisibleWalls.add(new Rectangle(160, 432, 128, 32));
        invisibleWalls.add(new Rectangle(208, 464, 32, 48));
        // lower left T block
        invisibleWalls.add(new Rectangle(32, 480, 160, 32));
        invisibleWalls.add(new Rectangle(112, 432, 32, 48));
        // lower left T block
        invisibleWalls.add(new Rectangle(256, 480, 160, 32));
        invisibleWalls.add(new Rectangle(304, 432, 32, 48));
    }

    private void orbPlacer() {
        for (int i = 0; i < 192; i += 16) {
            orbGenerator(16 + i, 64, "small");
        }
        for (int i = 224; i < 416; i += 16) {
            orbGenerator(16 + i, 64, "small");
        }
        orbGenerator(16, 80, "small");
        orbGenerator(16, 128, "big");
        orbGenerator(16, 96, "small");
        orbGenerator(16, 112, "small");
        orbGenerator(16, 144, "small");
        orbGenerator(16, 160, "small");
        orbGenerator(16, 176, "small");
        // orbGenerator(16, 96, "big");
    }

    private void orbGenerator(int x, int y, String size) {
        if (size.equals("small")) {
            orbsSmall.add(new Ellipse2D.Double(x + 6, y + 6, 4, 4));
            return;
        }
        if (size.equals("big")) {
            orbsBig.add(new Ellipse2D.Double(x, y, 16, 16));
            return;
        }
    }

    private void obstacleBlockGenerator(int x, int y, int width, int height) {
        boolean withHorizontalWalls = false;
        boolean withVerticalWalls = false;

        if (width > 2) {
            withHorizontalWalls = true;
        }
        if (height > 2) {
            withVerticalWalls = true;
        }
        // corners
        upperLeftInnerCorner(x, y);
        upperRightInnerCorner(x + (width - 1) * 16, y);
        lowerLeftInnerCorner(x, y + (height - 1) * 16);

        // walls
        lowerRightInnerCorner(x + (width - 1) * 16, y + (height - 1) * 16);
        if (withHorizontalWalls) {
            bottomInnerBorder(x + 16, y, (width - 2) * 16);
            topInnerBorder(x + 16, withVerticalWalls ? y + (height - 1) * 16 : y + 16, (width - 2) * 16);
        }
        if (withVerticalWalls) {
            rightInnerBorder(x, y + 16, (height - 2) * 16);
            leftInnerBorder(withHorizontalWalls ? x + (width - 1) * 16 : x + 16, y + 16, (height - 2) * 16);
        }
    }

    // OFF SETTERS
    private void addOuterCornerOffsets(int x, int y, boolean mirrorX, boolean mirrorY) {
        final int BW = 16, BH = 16;

        int[][] outer = {
                { 0, 0 }, { 0, 8 }, { 6, 8 }, { 6, 10 }, { 8, 10 }, { 8, 16 },
                { 16, 16 }, { 16, 8 }, { 14, 8 }, { 14, 4 }, { 12, 4 }, { 12, 2 },
                { 8, 2 }, { 8, 0 }
        };

        int[][] hollow = {
                { 0, 2 }, { 0, 6 }, { 6, 6 }, { 6, 8 }, { 8, 8 }, { 8, 10 },
                { 10, 10 }, { 10, 16 }, { 14, 16 }, { 14, 8 }, { 12, 8 },
                { 12, 4 }, { 8, 4 }, { 8, 2 }
        };

        Polygon outerPoly = new Polygon();
        for (int[] o : outer) {
            int ox = o[0], oy = o[1];
            int px = mirrorX ? x + (BW - ox) : x + ox;
            int py = mirrorY ? y + (BH - oy) : y + oy;
            outerPoly.addPoint(px, py);
        }
        walls.add(outerPoly);

        Polygon hollowPoly = new Polygon();
        for (int[] o : hollow) {
            int ox = o[0], oy = o[1];
            int px = mirrorX ? x + (BW - ox) : x + ox;
            int py = mirrorY ? y + (BH - oy) : y + oy;
            hollowPoly.addPoint(px, py);
        }
        hollows.add(hollowPoly);
    }

    private void addOuterBorderOffsets(int x, int y, boolean vertical, boolean mirrorX, boolean mirrorY, int length) {
        int width = 0, height = 0;
        int px = mirrorX ? x + 8 : x + 0;
        int py = mirrorY ? y + 8 : y + 0;
        width = vertical ? width + 8 : width + length;
        height = vertical ? height + length : height + 8;

        walls.add(new Rectangle(px, py, width, height));
        hollows.add(new Rectangle(vertical ? px + 2 : px, vertical ? py : py + 2,
                vertical ? width - 4 : width, vertical ? height : height - 4));
    }

    private void addInnerCornerOffsets(int x, int y, boolean mirrorX, boolean mirrorY) {
        final int BW = 16, BH = 16;

        int[][] outline = {
                { 14, 8 }, { 16, 8 }, { 16, 10 }, { 12, 10 }, { 12, 12 }, { 10, 12 },
                { 10, 16 }, { 8, 16 }, { 8, 12 }, { 10, 12 }, { 10, 10 }, { 12, 10 },
                { 12, 8 }
        };

        Polygon polyOutline = new Polygon();
        for (int[] o : outline) {
            int ox = o[0], oy = o[1];
            int px = mirrorX ? x + (BW - ox) : x + ox;
            int py = mirrorY ? y + (BH - oy) : y + oy;
            polyOutline.addPoint(px, py);
        }
        walls.add(polyOutline);
    }

    private void addInnerBorderOffsets(int x, int y, boolean vertical, boolean mirrorX, boolean mirrorY, int length) {
        int width = 0, height = 0;
        int px = mirrorX ? x + 8 : x + 0;
        int py = mirrorY ? y + 8 : y + 0;
        width = vertical ? width + 2 : width + length;
        height = vertical ? height + length : height + 2;

        walls.add(new Rectangle(px, py, width, height));
    }

    // INNER BORDERS
    private void topInnerBorder(int x, int y, int length) {
        addInnerBorderOffsets(x, y + 6, false, false, false, length);
    }

    private void bottomInnerBorder(int x, int y, int length) {
        addInnerBorderOffsets(x, y, false, false, true, length);
    }

    private void rightInnerBorder(int x, int y, int length) {
        addInnerBorderOffsets(x, y, true, true, false, length);
    }

    private void leftInnerBorder(int x, int y, int length) {
        addInnerBorderOffsets(x + 6, y, true, false, false, length);
    }

    // INNER CORNERS
    private void upperLeftInnerCorner(int x, int y) {
        addInnerCornerOffsets(x, y, false, false);
    }

    private void upperRightInnerCorner(int x, int y) {
        addInnerCornerOffsets(x, y, true, false);
    }

    private void lowerLeftInnerCorner(int x, int y) {
        addInnerCornerOffsets(x, y, false, true);
    }

    private void lowerRightInnerCorner(int x, int y) {
        addInnerCornerOffsets(x, y, true, true);
    }

    // OUTER BORDERS
    private void leftOuterBorder(int x, int y, int length) {
        addOuterBorderOffsets(x, y, true, false, false, length);
    }

    private void rightOuterBorder(int x, int y, int length) {
        addOuterBorderOffsets(x, y, true, true, false, length);
    }

    private void topOuterBorder(int x, int y, int length) {
        addOuterBorderOffsets(x, y, false, false, false, length);
    }

    private void bottomOuterBorder(int x, int y, int length) {
        addOuterBorderOffsets(x, y, false, false, true, length);
    }

    // OUTER CORNERS
    private void upperLeftOuterCorner(int x, int y) {
        addOuterCornerOffsets(x, y, true, false);
    }

    private void upperRightOuterCorner(int x, int y) {
        addOuterCornerOffsets(x, y, false, false);
    }

    private void lowerLeftOuterCorner(int x, int y) {
        addOuterCornerOffsets(x, y, true, true);
    }

    private void lowerRightOuterCorner(int x, int y) {
        addOuterCornerOffsets(x, y, false, true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. Try to change to desiredDir if possible
        if (canMove(desiredDir)) {
            currentDir = desiredDir;
        }

        tunnel();

        // 2. Move in the current direction if possible
        int nextX = x;
        int nextY = y;

        switch (currentDir) {
            case "LEFT":
                nextX -= speed;
                break;
            case "RIGHT":
                nextX += speed;
                break;
            case "UP":
                nextY -= speed;
                break;
            case "DOWN":
                nextY += speed;
                break;
        }

        if (!collidesWithLookahead(x, y, nextX - x, 0)) {
            x = nextX;
        }
        if (!collidesWithLookahead(x, y, 0, nextY - y)) {
            y = nextY;
        }
        repaint();
    }

    private boolean canMove(String dir) {
        switch (dir) {
            case "LEFT": {
                return !collidesWithLookahead(x, y, -1, 0);
            }
            case "RIGHT": {
                return !collidesWithLookahead(x, y, 1, 0);
            }
            case "UP": {
                return !collidesWithLookahead(x, y, 0, -1);
            }
            case "DOWN": {
                return !collidesWithLookahead(x, y, 0, 1);
            }
        }
        return false;
    }

    private boolean collidesWithLookahead(int x, int y, int dx, int dy) {
        int lookahead = 1;

        int checkX = x + (dx > 0 ? lookahead : dx < 0 ? -lookahead : 0);
        int checkY = y + (dy > 0 ? lookahead : dy < 0 ? -lookahead : 0);

        Rectangle rect = new Rectangle(
                checkX + hitBoxOffsetX,
                checkY + hitBoxOffsetY,
                hitBoxW,
                hitBoxH);

        for (Shape wall : invisibleWalls) {
            if (wall.intersects(rect)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.BLUE);
        for (Shape wall : walls)
            g2.fill(wall);

        g2.setColor(Color.BLACK);
        for (Shape hole : hollows)
            g2.fill(hole);

        g2.setColor(Color.WHITE);
        for (Shape orb : orbsSmall)
            g2.fill(orb);

        g2.setColor(Color.WHITE);
        for (Shape orb : orbsBig)
            g2.fill(orb);

        if (showDebugWalls) {
            g2.setColor(new Color(255, 0, 0, 128));
            for (Shape wall : invisibleWalls)
                g2.fill(wall);
        }
        // Player
        g2.setColor(Color.YELLOW);
        g2.fillOval(x, y, w, h);
    }

    private void tunnel() {
        if (x == -32) {
            x = 456;
            return;
        }
        if (x == 464) {
            x = -24;
            return;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Pacman in swing");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameTest panel = new gameTest();
            window.setContentPane(panel);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });
    }
}
