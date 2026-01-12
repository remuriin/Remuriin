package Practice_Swing.UI_Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Menu_Panel extends JPanel implements ActionListener
{
    private final int SCREEN_W = 1536;
    private final int SCREEN_H = 864;

    private Image main = ResourceLoader.loadImage("Main.png");
    private JTable table;

    public Menu_Panel()
    {
        // Use absolute positioning so we can control x/y
        this.setLayout(null);

        // Table data
        String[] theatreColumns = {"ID", "Name", "Status"};
        // 8 rows × 3 columns
        Object[][] theatreData = new Object[8][3];

        for (int i = 0; i < 8; i++) {
            theatreData[i][0] = (i + 1) + "";         // ID
            theatreData[i][1] = "Name" + (i + 1);     // Name
            // Alternate statuses for variety
            switch (i % 4) {
                case 0 -> theatreData[i][2] = "Active";
                case 1 -> theatreData[i][2] = "Inactive";
                case 2 -> theatreData[i][2] = "Pending";
                case 3 -> theatreData[i][2] = "Suspended";
            }
        }

        JPanel theatrePane = Table.createTable
        (
            0,
            196,
            290,
            614,
            0,
            true,
            false,
            theatreColumns,
            theatreData,
            new Color(165, 135, 33),
            new Color(30, 30, 30),
            new Color(10, 25, 75),
            new Color(255, 255, 255),
            38,
            true
        );
        this.add(theatrePane);

        String[] columns = {"ID", "Name", "Status"};
        // 8 rows × 3 columns
        Object[][] data = new Object[4][3];

        for (int i = 0; i < 4; i++) {
            data[i][0] = (i + 1) + "";         // ID
            data[i][1] = "Name" + (i + 1);     // Name
            // Alternate statuses for variety
            switch (i % 4) {
                case 0 -> data[i][2] = "Active";
                case 1 -> data[i][2] = "Inactive";
                case 2 -> data[i][2] = "Pending";
                case 3 -> data[i][2] = "Suspended";
            }
        }

        JPanel upcomingScreeningsPane = Table.createTable
        (
            291,
            650,
            1538,
            820,
            0,
            true,
            false,
            columns,
            data,
            new Color(165, 135, 33),
            new Color(30, 30, 30),
            new Color(10, 25, 75),
            new Color(255, 255, 255),
            29,
            true
        );
        this.add(upcomingScreeningsPane);
    }

    public void setBoundsByCorners(JComponent comp, int x1, int y1, int x2, int y2)
    {
        comp.setBounds(x1, y1, x2 - x1, y2 - y1);
    }

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

        gfx.drawImage(main, 0, 0, SCREEN_W, SCREEN_H, null);
    }
}
