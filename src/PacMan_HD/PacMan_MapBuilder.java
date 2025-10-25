package PacMan_HD;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PacMan_MapBuilder extends JPanel
{
    public ArrayList<Shape> wallBlocks = new ArrayList<>();
    public ArrayList<Shape> corners = new ArrayList<>();
    public ArrayList<Shape> hollows = new ArrayList<>();
    public ArrayList<Shape> hollowCorners = new ArrayList<>();
    public ArrayList<Shape> invisibleWalls = new ArrayList<>();
    public ArrayList<Shape> orbs = new ArrayList<>();
    public ArrayList<Shape> bigOrbs = new ArrayList<>();
    public ArrayList<Shape> gates = new ArrayList<>();

    static int[][] mapTemplate = new int[62][56];
    static int row = 0;
    static int column = 0;
    static int outlineThickness;

    static int y = 60;
    static int x = 0;

    public void generateMap(int[][] mapSelected, boolean hollow, int outline)
    {
        mapTemplate = mapSelected;
        outlineThickness = outline;

        for (y = 60; y < 680; y += 10)
        {
            for (x = 0; x < 560; x += 10)
            {
                wallBlockGenerate();
                cornerBlockGenerate();
                cageBlockGenerate();
                hollowWallGenerate();
                invisibleWallGenerate();
                orbGenerate();
                column++;
            }
            column = 0;
            row++;
        }
    }
    private void wallBlockGenerate()
    {
        if (mapTemplate[row][column] == 1)
        {
            wallBlocks.add(new Rectangle(x, y, 10, 10));
        }
    }

    private void cornerBlockGenerate()
    {
        if (mapTemplate[row][column] > 4)
        {
            int size = 10; // tile size
            int r = size;

            boolean roundTopLeft = false;
            boolean roundTopRight = false;
            boolean roundBottomRight = false;
            boolean roundBottomLeft = false;

            if (mapTemplate[row][column] == 5)
            {
                roundTopLeft = true;
            } else if (mapTemplate[row][column] == 6) {
                roundTopRight = true;
            } else if (mapTemplate[row][column] == 7) {
                roundBottomLeft = true;
            } else if (mapTemplate[row][column] == 8) {
                roundBottomRight = true;
            }

            Path2D.Float path = new Path2D.Float();

            // Start at top-left corner
            path.moveTo(x, y + (roundTopLeft ? r : 0));

            // --- TOP EDGE ---
            if (roundTopLeft) {
                path.quadTo(x, y, x + r, y);
            } else {
                path.lineTo(x + size, y);
            }

            // --- RIGHT EDGE ---
            if (roundTopRight) {
                path.lineTo(x + size - r, y);
                path.quadTo(x + size, y, x + size, y + r);
            } else {
                path.lineTo(x + size, y);
            }

            // --- BOTTOM EDGE ---
            if (roundBottomRight) {
                path.lineTo(x + size, y + size - r);
                path.quadTo(x + size, y + size, x + size - r, y + size);
            } else {
                path.lineTo(x + size, y + size);
            }

            // --- LEFT EDGE ---
            if (roundBottomLeft) {
                path.lineTo(x + r, y + size);
                path.quadTo(x, y + size, x, y + size - r);
            } else {
                path.lineTo(x, y + size);
            }

            // Close shape
            path.closePath();
            corners.add(path);
        }
    }

    private void cageBlockGenerate()
    {
        if (mapTemplate[row][column] == 4)
        {
            gates.add(new Rectangle(x, y + 3, 10, 4));
        }
    }

    private void hollowWallGenerate()
    {
        int xStart = 0;
        int yStart = 0;

        int width = 10;
        int height = 10;

        int doublewall = width - outlineThickness * 2;
        int singleWall = width - outlineThickness;


        if (mapTemplate[row][column] > 4)
        {
            int size = 10 - outlineThickness;
            int r = size;

            boolean roundTopLeft = false;
            boolean roundTopRight = false;
            boolean roundBottomRight = false;
            boolean roundBottomLeft = false;

            int xAxis = x;
            int yAxis = y;

            if (mapTemplate[row][column] == 5)
            {
                roundTopLeft = true;
                xAxis += outlineThickness;
                yAxis += outlineThickness;
            } else if (mapTemplate[row][column] == 6)
            {
                roundTopRight = true;
                yAxis += outlineThickness;
            } else if (mapTemplate[row][column] == 7)
            {
                roundBottomLeft = true;
                xAxis += outlineThickness;
            } else if (mapTemplate[row][column] == 8)
            {
                roundBottomRight = true;
            }

            Path2D.Float path = new Path2D.Float();

            path.moveTo(xAxis, yAxis + (roundTopLeft ? r : 0));

            if (roundTopLeft)
            {
                path.quadTo(xAxis, yAxis, xAxis + r, yAxis);
            } else
            {
                path.lineTo(xAxis + size, yAxis);
            }

            if (roundTopRight)
            {
                path.lineTo(xAxis + size - r, yAxis);
                path.quadTo(xAxis + size, yAxis, xAxis + size, yAxis + r);
            } else {
                path.lineTo(xAxis + size, yAxis);
            }

            if (roundBottomRight)
            {
                path.lineTo(xAxis + size, yAxis + size - r);
                path.quadTo(xAxis + size, yAxis + size, xAxis + size - r, yAxis + size);
            } else {
                path.lineTo(xAxis + size, yAxis + size);
            }

            if (roundBottomLeft)
            {
                path.lineTo(xAxis + r, yAxis + size);
                path.quadTo(xAxis, yAxis + size, xAxis, yAxis + size - r);
            } else {
                path.lineTo(xAxis, yAxis + size);
            }

            path.closePath();
            hollowCorners.add(path);
        } else if (column == 0 || column == 55 || row == 0 || row == 61)
        {
            //X axis
            if (column != 0 && column != 55)
            {
                if (mapTemplate[row][column - 1] == 0)
                {
                    xStart = x + outlineThickness;
                } else
                {
                    xStart = x;
                }
                if ((mapTemplate[row][column - 1] == 0) && (mapTemplate[row][column + 1] == 0))
                {
                    width = doublewall;
                } else if (((mapTemplate[row][column - 1] == 0) && (mapTemplate[row][column + 1] == 1)) ||
                ((mapTemplate[row][column - 1] == 1) && (mapTemplate[row][column + 1] == 0)))
                {
                    width = singleWall;
                }
            } else if ((column == 0) || (column == 55))
            {
                if (column == 0)
                {
                    xStart = x + outlineThickness;
                    if (mapTemplate[row][1] == 0)
                    {
                        width = doublewall;
                    } else
                    {
                        width = singleWall;
                    }
                } else if (column == 55)
                {
                    if (mapTemplate[row][54] == 0)
                    {
                        xStart = x + outlineThickness;
                        width = doublewall;
                    } else
                    {
                        xStart = x;
                        width = singleWall;
                    }
                }
            } else
            {
                xStart = x;
            }

            //Y axis
            if (row != 0 && row != 61)
            {
                if (mapTemplate[row - 1][column] == 0)
                {
                    yStart = y + outlineThickness;
                } else
                {
                    yStart = y;
                }
                if ((mapTemplate[row - 1][column] == 0) && (mapTemplate[row + 1][column] == 0))
                {
                    height = doublewall;
                } else if (((mapTemplate[row - 1][column] == 0) && (mapTemplate[row + 1][column] == 1)) ||
                ((mapTemplate[row - 1][column] == 1) && (mapTemplate[row + 1][column] == 0)))
                {
                    height = singleWall;
                }
            } else if ((row == 0) || (row == 61))
            {
                if (row == 0)
                {
                    yStart = y + outlineThickness;
                    if (mapTemplate[1][column] == 0)
                    {
                        height = doublewall;
                    } else
                    {
                        height = singleWall;
                    }
                } else if (row == 61)
                {
                    if (mapTemplate[60][column] == 0)
                    {
                        yStart = y + outlineThickness;
                        height = doublewall;
                    } else
                    {
                        yStart = y;
                        height = singleWall;
                    }
                }
            } else
            {
                yStart = y;
            }
            hollows.add(new Rectangle(xStart, yStart, width, height));
        } else if (mapTemplate[row][column] == 1)
        {
            if (mapTemplate[row][column - 1] == 0 || mapTemplate[row][column - 1] == 4)
            {
                xStart = x + outlineThickness;
            } else
            {
                xStart = x;
            }
            if ((mapTemplate[row][column - 1] == 0) && (mapTemplate[row][column + 1] == 0) || (mapTemplate[row][column + 1] == 4))
            {
                width = doublewall;
            } else if (((mapTemplate[row][column - 1] == 0) && (mapTemplate[row][column + 1] == 1)) ||
            ((mapTemplate[row][column - 1] == 1) && (mapTemplate[row][column + 1] == 0)))
            {
                width = singleWall;
            }

            if (mapTemplate[row - 1][column] == 0)
            {
                yStart = y + outlineThickness;
            } else
            {
                yStart = y;
            }
            if ((mapTemplate[row - 1][column] == 0) && (mapTemplate[row + 1][column] == 0))
            {
                height = doublewall;
            } else if (((mapTemplate[row - 1][column] == 0) && (mapTemplate[row + 1][column] == 1)) ||
            ((mapTemplate[row - 1][column] == 1) && (mapTemplate[row + 1][column] == 0)))
            {
                height = singleWall;
            }
            hollows.add(new Rectangle(xStart, yStart, width, height));
        }
    }

    private void orbGenerate()
    {
        if (mapTemplate[row][column] == 2)
        {
            orbs.add(new Ellipse2D.Double(x + 7, y + 7, 6, 6));
        } else if (mapTemplate[row][column] == 3)
        {
            orbs.add(new Ellipse2D.Double(x, y, 20, 20));
        }
    }

    private void invisibleWallGenerate()
    {
        if (mapTemplate[row][column] > 0 && mapTemplate[row][column] != 2 && mapTemplate[row][column] != 3)
        {
            invisibleWalls.add(new Rectangle(x, y, 10, 10));
        }
    }
}