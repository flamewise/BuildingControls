package com.example.buildingcontrols;

import com.example.buildingcontrols.ui.MainWindow;
import com.example.buildingcontrols.controllers.CLI;

public class Main {
    public static void main(String[] args) {
        String mode = System.getenv("APP_MODE");

        if ("CLI".equalsIgnoreCase(mode)) {
            System.out.println("Launching in CLI mode...");
            CLI cli = new CLI();
            cli.start(); // Launch CLI mode
        } else {
            System.out.println("Launching in GUI mode...");
            MainWindow.main(args); // Launch GUI mode
        }
    }
}
