import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class JTable_Test
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Reservation Table Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        frame.setLayout(new GridBagLayout()); // Centers the table
        frame.getContentPane().setBackground(new Color(25, 25, 25)); // Background color

        // ✅ Define proper column headers
        String[] columnNames = {
            "Reservation ID",
            "Seat No.",
            "Customer Name",
            "Booking Type",
            "Status",
            "Payment",
            "Date Created"
        };

        // ✅ Each row now matches column count
        Object[][] data = {
            {"R001", "A10", "John Doe", "Regular", "Confirmed", "Cash", "2025-10-27"},
            {"R002", "B15", "Jane Smith", "Online", "Pending", "Credit", "2025-10-25"},
            {"R003", "C12", "Carlos Reyes", "VIP", "Confirmed", "GCash", "2025-10-20"},
            {"R004", "D09", "Maria Santos", "Walk-in", "Canceled", "Cash", "2025-10-19"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // ✅ Enable full-row selection
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // ✅ Table styling
        table.setRowHeight(35);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.setGridColor(new Color(200, 200, 200));
        table.setBackground(new Color(245, 245, 245));
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(new Color(100, 150, 255)); // Blue highlight
        table.setSelectionForeground(Color.WHITE);

        // Center align all cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++)
        {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // ✅ Header styling
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setBackground(new Color(50, 50, 50));
        header.setForeground(Color.WHITE);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // ✅ Scroll pane (acts like a floating card)
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 300));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 2));

        // ✅ Center the table
        frame.add(scrollPane, new GridBagConstraints());

        frame.setVisible(true);
    }
}