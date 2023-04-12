package com.WFC.weekendFitnessClub;

/**
 * The main class for the Weekend Fitness Club application. This class loads the AppManager object from a file, or creates a new instance if the file is not found or cannot be read. It then runs the full app logic.
 */

public class WFCApp {

    /**
     * The main method for the Weekend Fitness Club application. Creates new instance of the AppManager. It then starts the App.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AppManager appManager = new AppManager();

        /*----------------------------Starts the App---------------*/
        appManager.startWFCApp();
    }
}
