/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Ari Awaludin
 */
import model.Pasien;
import utils.DebugLogger;
import utils.PasienComparator;

import java.util.*;

public class AntrianController {
    private Queue<Pasien> antrian = new LinkedList<>();
    private DebugLogger logger;

    public AntrianController(DebugLogger logger) {
        this.logger = logger;
    }

    public void tambahPasien(String nama, int umur) {
        if (nama == null || nama.isEmpty() || umur <= 0) {
            logger.log("Input tidak valid.");
            return;
        }
        Pasien p = new Pasien(nama, umur);
        antrian.add(p);
        logger.log("Tambah: " + p);
    }

    public void prosesPasien() {
        if (antrian.isEmpty()) {
            logger.log("Antrian kosong.");
        } else {
            Pasien p = antrian.poll();
            logger.log("Proses: " + p);
        }
    }

    public void resetAntrian() {
        antrian.clear();
        logger.log("Antrian direset.");
    }

    public List<Pasien> getDaftarAntrian() {
        return new ArrayList<>(antrian);
    }

    public List<Pasien> getSorted(String by) {
        List<Pasien> list = getDaftarAntrian();
        switch (by) {
            case "Nama":
                list.sort(PasienComparator.namaComparator);
                break;
            case "Umur Tertua":
                list.sort(PasienComparator.umurDesc);
                break;
            case "Umur Termuda":
                list.sort(PasienComparator.umurAsc);
                break;
        }
        return list;
    }

    public List<Pasien> searchPasien(String keyword) {
        List<Pasien> hasil = new ArrayList<>();
        for (Pasien p : antrian) {
            if (p.getNama().toLowerCase().contains(keyword.toLowerCase())) {
                hasil.add(p);
            }
        }
        return hasil;
    }
        public int getJumlahAntrian() {
        return antrian.size();
    }

}