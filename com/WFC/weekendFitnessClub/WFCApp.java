package com.WFC.weekendFitnessClub;

/**
 * The main class for the Weekend Fitness Club application. This class loads the AppManager object from a file, or creates a new instance if the file is not found or cannot be read. It then runs the full app logic.
 */
import java.io.*;
import java.util.Scanner;

public class WFCApp implements Serializable {
    private final static String filePath = "com/WFC/weekendFitnessClub/Serialization/appState.dat";

    /**
     * The main method for the Weekend Fitness Club application. Loads the AppManager object from a file, or creates a new instance if the file is not found or cannot be read. It then runs the full app logic.
     *
     * @param args the command line arguments
     * @throws IOException if there is an error while reading the AppManager object from the file
     */
    public static void main(String[] args) throws IOException {
        AppManager appManager = null;

        /*----------------------------Load AppManager Object from File if it exist---------------*/
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            appManager = (AppManager) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            appManager = new AppManager();
        } finally {
            assert appManager != null;
            appManager.setScanner(new Scanner(System.in));
        }

        /*----------------------------Run Full App Logic---------------*/
        appManager.startWFCApp();
    }
}
