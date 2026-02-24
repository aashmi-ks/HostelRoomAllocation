package HostelRoomAllocation;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HostelRoomAllocation extends JFrame {

    private JTextField txtStudentId, txtName, txtDepartment, txtYear;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnAllocateRoom;
    private JTable table;
    private DefaultTableModel model;

    // ✅ Shared allocation model
    private static DefaultTableModel allocModel = new DefaultTableModel();

    public HostelRoomAllocation() {

        setTitle("Hostel Room Allocation System");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color blueBtn = new Color(25, 118, 210);
        Color lightBg = new Color(235, 240, 248);

        // Initialize allocation table only once
        if (allocModel.getColumnCount() == 0) {
            allocModel.addColumn("Student ID");
            allocModel.addColumn("Name");
            allocModel.addColumn("Block");
            allocModel.addColumn("Room");
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(blueBtn, 2));
        mainPanel.setBackground(lightBg);

        // ===== TITLE =====
        JPanel titlePanel = new JPanel(new GridLayout(2,1));
        titlePanel.setBackground(blueBtn);

        JLabel lblTitle = new JLabel("HOSTEL ROOM ALLOCATION SYSTEM", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblSubTitle = new JLabel("STUDENT DETAILS", JLabel.CENTER);
        lblSubTitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSubTitle.setForeground(new Color(220, 230, 241));

        titlePanel.add(lblTitle);
        titlePanel.add(lblSubTitle);

        // ===== FORM =====
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setPreferredSize(new Dimension(700, 250));

        JLabel lblStudentId = new JLabel("Student Id :");
        lblStudentId.setBounds(120, 30, 120, 25);
        formPanel.add(lblStudentId);

        txtStudentId = new JTextField();
        txtStudentId.setBounds(250, 30, 250, 30);
        formPanel.add(txtStudentId);

        JLabel lblName = new JLabel("Name :");
        lblName.setBounds(120, 80, 120, 25);
        formPanel.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(250, 80, 250, 30);
        formPanel.add(txtName);

        JLabel lblDepartment = new JLabel("Department :");
        lblDepartment.setBounds(120, 130, 120, 25);
        formPanel.add(lblDepartment);

        txtDepartment = new JTextField();
        txtDepartment.setBounds(250, 130, 250, 30);
        formPanel.add(txtDepartment);

        JLabel lblYear = new JLabel("Year :");
        lblYear.setBounds(120, 180, 120, 25);
        formPanel.add(lblYear);

        txtYear = new JTextField();
        txtYear.setBounds(250, 180, 250, 30);
        formPanel.add(txtYear);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(lightBg);

        btnAdd = createBlueButton("Add", blueBtn);
        btnUpdate = createBlueButton("Update", blueBtn);
        btnDelete = createBlueButton("Delete", blueBtn);
        btnClear = createBlueButton("Clear", blueBtn);
        btnAllocateRoom = createBlueButton("Allocate Room", blueBtn);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnAllocateRoom);

        // ===== TABLE =====
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("STUDENT TABLE"));

        model = new DefaultTableModel();
        model.addColumn("Student Id");
        model.addColumn("Name");
        model.addColumn("Department");
        model.addColumn("Year");

        table = new JTable(model);
        table.setRowHeight(25);
        table.setSelectionBackground(blueBtn);
        table.getTableHeader().setBackground(new Color(21, 101, 192));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);

        // ===== BUTTON ACTIONS =====
        btnAdd.addActionListener(e -> {
            model.addRow(new Object[]{
                    txtStudentId.getText(),
                    txtName.getText(),
                    txtDepartment.getText(),
                    txtYear.getText()
            });
        });

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) model.removeRow(row);
        });

        btnClear.addActionListener(e -> {
            txtStudentId.setText("");
            txtName.setText("");
            txtDepartment.setText("");
            txtYear.setText("");
        });

        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                model.setValueAt(txtStudentId.getText(), row, 0);
                model.setValueAt(txtName.getText(), row, 1);
                model.setValueAt(txtDepartment.getText(), row, 2);
                model.setValueAt(txtYear.getText(), row, 3);
            }
        });

        btnAllocateRoom.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String studentId = model.getValueAt(row, 0).toString();
                String studentName = model.getValueAt(row, 1).toString();
                new RoomAllocationScreen(studentId, studentName);
            } else {
                JOptionPane.showMessageDialog(this, "Select a student first!");
            }
        });

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createBlueButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    // ================= ROOM ALLOCATION SCREEN =================
    class RoomAllocationScreen extends JFrame {

        private static final int MAX_CAPACITY = 3;
        private JScrollPane memberScrollPane;

        public RoomAllocationScreen(String studentId, String studentName) {

            setTitle("Room Allocation");
            setSize(750, 550);
            setLocationRelativeTo(null);
            setLayout(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel lblBlock = new JLabel("Block:");
            lblBlock.setBounds(100, 80, 120, 25);
            add(lblBlock);

            JComboBox<String> comboBlock =
                    new JComboBox<>(new String[]{"A Block", "B Block", "C Block"});
            comboBlock.setBounds(230, 80, 200, 28);
            add(comboBlock);

            JLabel lblRoom = new JLabel("Room No:");
            lblRoom.setBounds(100, 120, 120, 25);
            add(lblRoom);

            JComboBox<String> comboRoom =
                    new JComboBox<>(new String[]{"101", "102", "103", "201", "202"});
            comboRoom.setBounds(230, 120, 200, 28);
            add(comboRoom);

            JButton btnAllocate = new JButton("Allocate");
            btnAllocate.setBounds(230, 170, 120, 35);
            add(btnAllocate);

            JButton btnView = new JButton("View Members");
            btnView.setBounds(370, 170, 150, 35);
            add(btnView);

            // Allocate
            btnAllocate.addActionListener(e -> {

                String block = comboBlock.getSelectedItem().toString();
                String room = comboRoom.getSelectedItem().toString();

                int count = 0;

                for (int i = 0; i < allocModel.getRowCount(); i++) {
                    if (allocModel.getValueAt(i, 2).equals(block)
                            && allocModel.getValueAt(i, 3).equals(room)) {
                        count++;
                    }
                }

                if (count >= MAX_CAPACITY) {
                    JOptionPane.showMessageDialog(this,
                            "Room Full! Maximum 3 students allowed.");
                } else {
                    allocModel.addRow(new Object[]{
                            studentId, studentName, block, room
                    });
                    JOptionPane.showMessageDialog(this,
                            "Room Allocated Successfully!");
                }
            });

            // View Members as Table
            btnView.addActionListener(e -> {

                String block = comboBlock.getSelectedItem().toString();
                String room = comboRoom.getSelectedItem().toString();

                DefaultTableModel viewModel = new DefaultTableModel();
                viewModel.addColumn("Student ID");
                viewModel.addColumn("Name");
                viewModel.addColumn("Block");
                viewModel.addColumn("Room");

                for (int i = 0; i < allocModel.getRowCount(); i++) {
                    if (allocModel.getValueAt(i, 2).equals(block)
                            && allocModel.getValueAt(i, 3).equals(room)) {

                        viewModel.addRow(new Object[]{
                                allocModel.getValueAt(i, 0),
                                allocModel.getValueAt(i, 1),
                                allocModel.getValueAt(i, 2),
                                allocModel.getValueAt(i, 3)
                        });
                    }
                }

                if (memberScrollPane != null) {
                    remove(memberScrollPane);
                }

                JTable memberTable = new JTable(viewModel);
                memberTable.setRowHeight(25);

                memberScrollPane = new JScrollPane(memberTable);
                memberScrollPane.setBounds(80, 240, 580, 200);

                add(memberScrollPane);
                repaint();
                revalidate();
            });

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HostelRoomAllocation::new);
    }
}