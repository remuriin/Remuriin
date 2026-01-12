package Practice_Swing.UI_Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ScrollPaneTable
{
    // Creates a JTable wrapped in a JScrollPane with specified corners, row height, columns, and data
    // stretchRows: if true, rows stretch to fill table height; if false, uses fixed rowHeight
    // interactable: if false, table is purely display and cannot be selected or edited
    public static JScrollPane createTable(
        int x1, int y1, int x2, int y2,
        int rowHeight, boolean stretchRows,
        boolean interactable,          // new parameter
        String[] columns,
        Object[][] data)
    {
        // Create non-editable table model
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);

        // Set row selection behavior based on interactable
        table.setRowSelectionAllowed(interactable);
        table.setColumnSelectionAllowed(false);
        table.setEnabled(interactable); // disables any interaction if false

        // Table header settings
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setBorder(null);

        // Fully flat header using custom renderer
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                label.setBackground(Color.DARK_GRAY);
                label.setForeground(Color.WHITE);
                label.setBorder(null);
                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }
        });

        // Row height handling
        if (stretchRows) {
            int visibleHeight = y2 - y1 - header.getPreferredSize().height; // subtract header
            int rows = data.length > 0 ? data.length : 1;
            table.setRowHeight(Math.max(1, visibleHeight / rows));
        } else {
            table.setRowHeight(rowHeight);
        }

        // Table appearance
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setBorder(null);

        // Row highlight colors (only matters if interactable)
        if (interactable) {
            table.setSelectionBackground(new Color(50, 150, 250));
            table.setSelectionForeground(Color.WHITE);
        }

        // Center all cell text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Wrap in scroll pane
        JScrollPane scrollPane = new JScrollPane(
            table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(x1, y1, x2 - x1, y2 - y1);

        return scrollPane;
    }
}
