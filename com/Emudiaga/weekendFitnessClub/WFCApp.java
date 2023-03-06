package com.Emudiaga.weekendFitnessClub;

import java.io.*;
import java.util.Scanner;

public class WFCApp implements Serializable {
    private final static String filePath = "com/Emudiaga/weekendFitnessClub/Serialization/appState.dat";

    public static void main(String[] args) throws IOException {
        AppManager appManager = null;


        /*----------------------------Load AppManager Object from File---------------*/
        try{
             FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis);

            appManager = (AppManager) ois.readObject();
            ois.close();
            fis.close();
        } catch(FileNotFoundException | ClassNotFoundException e) {
            appManager = new AppManager();
        } finally {
            assert appManager != null;
            appManager.setScanner(new Scanner(System.in));
        }

        /*----------------------------Run Full App Logic---------------*/

        appManager.fullApp();
    }


}
