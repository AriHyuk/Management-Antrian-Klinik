/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Ari Awaludin
 */

import controller.AntrianController;
import model.Pasien;
import utils.DebugLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainView extends JFrame {
    private JTextField tfNama, tfUmur, tfSearch;
    private JTextArea areaLog;
    private JList<String> listAntrian;
    private DefaultListModel<String> listModel;
    private JComboBox<String> cbSort;
    private JLabel labelJumlah;

    private AntrianController controller;

    public MainView() {
        setTitle("Antrian Klinik Cerdas");
        setSize(850, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tfNama = new JTextField(15);
        tfUmur = new JTextField(5);
        tfSearch = new JTextField(10);
        JButton btnTambah = new JButton("➕ Tambah");
        JButton btnProses = new JButton("✅ Proses");
        JButton btnReset = new JButton("♻️ Reset");
        JButton btnCari = new JButton("🔍 Cari");

        cbSort = new JComboBox<>(new String[]{"Nama", "Umur Tertua", "Umur Termuda"});
        listModel = new DefaultListModel<>();
        listAntrian = new JList<>(listModel);
        areaLog = new JTextArea(10, 20);
        areaLog.setEditable(false);
        areaLog.setBackground(new Color(230, 255, 230));
        labelJumlah = new JLabel("Jumlah Antrian: 0");

        DebugLogger logger = new DebugLogger(areaLog);
        controller = new AntrianController(logger);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Input"));
        formPanel.setBackground(new Color(230, 240, 255));
        formPanel.add(new JLabel("Nama:"));
        formPanel.add(tfNama);
        formPanel.add(new JLabel("Umur:"));
        formPanel.add(tfUmur);
        formPanel.add(btnTambah);
        formPanel.add(btnProses);

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(240, 245, 255));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Sortir & Cari"));
        searchPanel.add(new JLabel("Urutkan:"));
        searchPanel.add(cbSort);
        searchPanel.add(new JLabel("Cari:"));
        searchPanel.add(tfSearch);
        searchPanel.add(btnCari);
        searchPanel.add(btnReset);

        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.add(formPanel, BorderLayout.NORTH);
        leftPanel.add(searchPanel, BorderLayout.CENTER);
        leftPanel.add(labelJumlah, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        JScrollPane listScroll = new JScrollPane(listAntrian);
        listScroll.setBorder(BorderFactory.createTitledBorder("Daftar Antrian"));
        JScrollPane logScroll = new JScrollPane(areaLog);
        logScroll.setBorder(BorderFactory.createTitledBorder("Log Aktivitas"));
        rightPanel.add(listScroll);
        rightPanel.add(logScroll);

        setLayout(new GridLayout(1, 2));
        add(leftPanel);
        add(rightPanel);

        // Event listeners
        btnTambah.addActionListener(this::onTambah);
        btnProses.addActionListener(e -> {
            controller.prosesPasien();
            refreshList();
        });
        btnReset.addActionListener(e -> {
            controller.resetAntrian();
            refreshList();
        });
        cbSort.addActionListener(e -> refreshSorted());
        btnCari.addActionListener(e -> refreshSearch());

        setVisible(true);
    }

    private void onTambah(ActionEvent e) {
        String nama = tfNama.getText();
        int umur;
        try {
            umur = Integer.parseInt(tfUmur.getText());
            controller.tambahPasien(nama, umur);
            refreshList();
        } catch (NumberFormatException ex) {
            areaLog.append("Umur harus berupa angka.\n");
        }
    }

    private void refreshList() {
        listModel.clear();
        for (Pasien p : controller.getDaftarAntrian()) {
            listModel.addElement(p.toString());
        }
        labelJumlah.setText("Jumlah Antrian: " + controller.getJumlahAntrian());
    }

    private void refreshSorted() {
        listModel.clear();
        List<Pasien> sorted = controller.getSorted((String) cbSort.getSelectedItem());
        for (Pasien p : sorted) {
            listModel.addElement(p.toString());
        }
    }

    private void refreshSearch() {
        listModel.clear();
        List<Pasien> result = controller.searchPasien(tfSearch.getText());
        for (Pasien p : result) {
            listModel.addElement(p.toString());
        }
    }
}
