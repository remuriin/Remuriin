package Practice_Swing.UI_Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Table
{
    // Creates a JPanel containing a JTable and its header manually
    // stretchRows: if true, rows stretch to fill table height (minus header); if false, uses fixed rowHeight
    // interactable: if false, table is purely display and cannot be selected or edited
    // -----------------------------
    // NEW PARAMETERS:
    // headerColor: color for table header background
    // headerFontColor: color for table header text
    // tableBgColor: table's background color
    // tableFontColor: font color for table cells
    // headerHeight: custom height for the table header
    // headerFontBold: whether header font should be bold
    // -----------------------------
    public static JPanel createTable(
        int x1, int y1, int x2, int y2,
        int rowHeight, boolean stretchRows,
        boolean interactable,
        String[] columns,
        Object[][] data,
        Color headerColor,        // header background color
        Color headerFontColor,    // header font color
        Color tableBgColor,       // table background color
        Color tableFontColor,     // table cell font color
        int headerHeight,         // custom header height
        boolean headerFontBold    // NEW: bold header font
    )
    {
        // Panel to hold the table and header
        JPanel panel = new JPanel(null); // null layout for absolute positioning
        panel.setBounds(x1, y1, x2 - x1, y2 - y1);

        // Create non-editable table model
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);

        // Set table background and font color
        table.setBackground(tableBgColor);
        table.setForeground(tableFontColor);

        // Set row selection behavior based on interactable
        table.setRowSelectionAllowed(interactable);
        table.setColumnSelectionAllowed(false);
        table.setEnabled(interactable);

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
                label.setBackground(headerColor);
                label.setForeground(headerFontColor);
                label.setBorder(null);
                label.setHorizontalAlignment(JLabel.CENTER);

                // Apply bold font if requested
                int fontStyle = headerFontBold ? Font.BOLD : Font.PLAIN;
                label.setFont(new Font(label.getFont().getName(), fontStyle, label.getFont().getSize()));

                return label;
            }
        });

        // Row height handling (pixel-perfect fill)
        int availableHeight = (y2 - y1) - headerHeight;
        int rows = data.length > 0 ? data.length : 1;

        if (stretchRows) {
            int baseRowHeight = availableHeight / rows;
            int remainder = availableHeight % rows;

            for (int i = 0; i < rows; i++) {
                table.setRowHeight(i, baseRowHeight + (i == rows - 1 ? remainder : 0));
            }
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
        centerRenderer.setForeground(tableFontColor);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add header manually with custom height
        header.setBounds(0, 0, x2 - x1, headerHeight);
        table.setBounds(0, headerHeight, x2 - x1, availableHeight);
        panel.add(header);
        panel.add(table);

        return panel;
    }
}
