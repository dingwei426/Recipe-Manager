package org.example.myproject;

import org.example.myproject.Authentication.LoginFrame;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            System.out.println("Frame created: " + loginFrame);
        });
    }
}
