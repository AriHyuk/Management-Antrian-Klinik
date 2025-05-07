/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Ari Awaludin
 */
import javax.swing.*;

public class DebugLogger {
    private JTextArea area;

    public DebugLogger(JTextArea area) {
        this.area = area;
    }

    public void log(String text) {
        area.append(text + "\n");
    }
}

