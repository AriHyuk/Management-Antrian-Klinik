/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Ari Awaludin
 */
import model.Pasien;

import java.util.Comparator;

public class PasienComparator {
    public static Comparator<Pasien> namaComparator = Comparator.comparing(Pasien::getNama);
    public static Comparator<Pasien> umurDesc = (a, b) -> b.getUmur() - a.getUmur();
    public static Comparator<Pasien> umurAsc = Comparator.comparingInt(Pasien::getUmur);
}
