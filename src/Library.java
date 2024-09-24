import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Library extends JFrame implements ActionListener{
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel searchLabel;
    private JLabel baseOnLabel;
    private JLabel sortedBy;
    private JTextField searchField;
    private JComboBox<String> searchComboBox;
    private JComboBox<String> urutkan;
    private JButton searchButton;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;

    String[][] data = {
            {"Aljabar Linear", "Zacky", "2018", "B-007"},
            {"Aritmatika", "Zacky", "2009", "B-009"},
            {"Bahasa", "Adli", "2010", "C-001"},
            {"Ekonomi", "Adli", "2007", "B-003"},
            {"Filosofi", "Wildan", "2003", "A-002"},
            {"Fiqih", "Apri", "2003", "A-001"},
            {"Ilmu Hadist", "Apri", "2016", "A-007"},
            {"Ilmu Tafsir", "Apri", "2015", "A-008"},
            {"Java", "Nugrah", "2014", "A-009"},
            {"Kalkulus", "Zacky", "2009", "A-006"},
            {"Kandungan", "Apri", "2009", "C-003"},
            {"Kripto", "Nugrah", "2023", "B-001"},
            {"Matematika Diskrit", "Zacky", "2013", "A-004"},
            {"Muamalah", "Apri", "2019", "A-005"},
            {"Perbankan", "Adli", "2014", "C-004"},
            {"Pidato", "Wildan", "2017", "C-002"},
            {"Psikologi", "Wildan", "2015", "B-005"},
            {"Python", "Wildan", "2011", "A-010"},
            {"Saham", "Nugrah", "2020", "B-002"},
            {"Sastra Inggris", "Wildan", "2016", "A-011"},
            {"Sastra Mesin", "Nugrah", "2015", "C-006"},
            {"Sejarah", "Adli", "2005", "A-003"},
            {"Statiska", "Zacky", "2014", "B-006"},
            {"Tips Kaya", "Nugrah", "2021", "B-004"},
            {"Trading", "Adli", "2024", "C-005"}
    };

    private void initComponents(){
        setTitle("Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        Font label = new Font("Consolas",Font.BOLD,14);
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(119,136,153));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        titleLabel = new JLabel("LIBRARY", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 0, 1));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        panel.add(titleLabel, gbc);

        searchLabel = new JLabel("Search  :", SwingConstants.RIGHT);
        searchLabel.setForeground(new Color(224,255,255));
        searchLabel.setFont(label);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(searchLabel, gbc);

        searchField = new JTextField();
        searchField.setBackground(new Color(245, 255, 255));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        panel.add(searchField, gbc);

        baseOnLabel = new JLabel("Base on :", SwingConstants.RIGHT);
        baseOnLabel.setForeground(new Color(224,255,255));
        baseOnLabel.setFont(label);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(baseOnLabel, gbc);

        String[] searchOptions = {"Judul", "Penulis", "Tahun Terbit"};
        searchComboBox = new JComboBox<>(searchOptions);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(searchComboBox, gbc);

        searchButton = new JButton("Search");
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(searchButton, gbc);

        sortedBy = new JLabel("Sorted By :", SwingConstants.RIGHT);
        sortedBy.setForeground(new Color(224,255,255, 255));
        sortedBy.setFont(label);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(sortedBy, gbc);

        String[] urut = {"Judul", "Penulis", "Tahun Terbit", "Lokasi"};
        urutkan = new JComboBox<>(urut);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(urutkan, gbc);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Judul");
        tableModel.addColumn("Penulis");
        tableModel.addColumn("Tahun");
        tableModel.addColumn("Lokasi");

        table = new JTable(tableModel);
        table.setRowHeight(30);
        scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);

        searchButton.addActionListener(this);
        urutkan.addActionListener(this);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchTerm = searchField.getText().toLowerCase();
        String pilihan = (String) searchComboBox.getSelectedItem();
        String urut = (String) urutkan.getSelectedItem();
        if (e.getSource() == searchButton) {
            cari(searchTerm, pilihan, urut);
        } else if (e.getSource() == urutkan){
            urutkan(urut);
        }
    }

    private void tabel (){
        tableModel.setRowCount(0);
        for (int i = 0; i < data.length; i++) {
            tableModel.addRow(data[i]);
        }
    }

    private void cari(String searchTerm, String pilihan, String urut) {
        tableModel.setRowCount(0);

        int columnIndex = 0;
        switch (pilihan) {
            case "Judul":
                columnIndex = 0;
                break;
            case "Penulis":
                columnIndex = 1;
                break;
            case "Tahun Terbit":
                columnIndex = 2;
                break;
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i][columnIndex].toLowerCase().contains(searchTerm)) {
                tableModel.addRow(data[i]);
            }
        }
        urutkan(urut);
    }

    private void urutkan(String urut) {
        int columnIndex = 0;
        switch (urut) {
            case "Judul":
                columnIndex = 0;
                break;
            case "Penulis":
                columnIndex = 1;
                break;
            case "Tahun Terbit":
                columnIndex = 2;
                break;
            case "Lokasi":
                columnIndex = 3;
                break;
        }
        int row = tableModel.getRowCount();
        String[][] tableData = new String[row][4];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < 4; j++) {
                tableData[i][j] = (String) tableModel.getValueAt(i, j);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row - 1; j++) {
                if (tableData[j][columnIndex].compareTo(tableData[j + 1][columnIndex]) > 0) {
                    String[] temp = tableData[j];
                    tableData[j] = tableData[j + 1];
                    tableData[j + 1] = temp;
                }
            }
        }

        tableModel.setRowCount(0);
        for (int i = 0; i < row; i++) {
            tableModel.addRow(tableData[i]);
        }
    }

    public static void main(String[] args){
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Library library = new Library();
        library.initComponents();
        library.tabel();
    }
}