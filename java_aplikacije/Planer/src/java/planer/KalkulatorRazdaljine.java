/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planer;

import io.ipgeolocation.api.Geolocation;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jovana
 */
public class KalkulatorRazdaljine {

    //Vraca vreme potrebno da se stigne na odredjenu lokaciju
    static long razdaljina(double xB, double yB) {
        try {
            String cmdarray[] = new String[3];
            cmdarray[0] = "java";
            cmdarray[1] = "-jar";
            cmdarray[2] = "Locator.jar";
            Process proc = Runtime.getRuntime().exec(cmdarray);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String latStr = stdInput.readLine();
            if(latStr == null) {
                System.out.println("Get current location went wrong... :( ");
                return 0;
            }
            String longStr = stdInput.readLine();
            if(longStr == null) {
                System.out.println("Get current location went wrong... :( ");
                return 0;
            }
            System.out.println("My current latitude is " + latStr);
            System.out.println("My current longitude is " + longStr);
            double currentX = Double.parseDouble(latStr);
            double currentY = Double.parseDouble(longStr);
            double distanceKm = Point2D.Double.distance(currentX, currentY, xB, yB) * 500;
            return (long) ((distanceKm / 30) * 60 * 60 * 1000); //30km/h
            
        } catch (IOException ex) {
            Logger.getLogger(KalkulatorRazdaljine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }

    static long razdaljina(double xA, double yA, double xB, double yB) {
        double distanceKm = Point2D.Double.distance(xA, yA, xB, yB);
        return (long) ((distanceKm / 30) * 60 * 60 * 1000); //30km/h
    }
}
