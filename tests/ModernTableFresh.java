import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ModernTableFresh {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ModernTableFresh().showTable();
        });
    }

    private void showTable() {
        // Frame
        JFrame frame = new JFrame("Modern JTable Fresh Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

        // Table data
        String[] columns = {"ID", "Name", "Email", "Status"};
        Object[][] data = new Object[50][4];
        for (int i = 0; i < 50; i++) {
            data[i][0] = i + 1;
            data[i][1] = "User " + (i + 1);
            data[i][2] = "user" + (i + 1) + "@example.com";
            data[i][3] = (i % 2 == 0) ? "Active" : "Inactive";
        }

        // Table
        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setFillsViewportHeight(true);
        table.setSelectionBackground(new Color(0, 120, 215));
        table.setSelectionForeground(Color.WHITE);

        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(30, 30, 30));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Cell styling
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(new Color(50, 50, 50));
        renderer.setForeground(Color.WHITE);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smooth scroll

        // Flat modern scrollbar
        scrollPane.getVerticalScrollBar().setUI(new FlatScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new FlatScrollBarUI());

        frame.add(scrollPane);
        frame.setVisible(true);
    }

    // Flat scrollbar UI
    static class FlatScrollBarUI extends javax.swing.plaf.basic.BasicScrollBarUI {

        private final int THUMB_SIZE = 12;

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            g.setColor(new Color(0, 120, 215));
            g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.setColor(new Color(40, 40, 40));
            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(0, 0));
            btn.setMinimumSize(new Dimension(0, 0));
            btn.setMaximumSize(new Dimension(0, 0));
            return btn;
        }

        @Override
        protected Dimension getMinimumThumbSize() {
            return new Dimension(THUMB_SIZE, THUMB_SIZE);
        }
    }
}
