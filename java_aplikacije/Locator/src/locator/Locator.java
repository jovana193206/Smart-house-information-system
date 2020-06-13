/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locator;

/**
 *
 * @author Jovana
 */
public class Locator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipdata.co/latitude?api-key=f487d6645da13203ebca57238720b207d7218473945be2a13749ca20").openStream(), "UTF-8").useDelimiter("\\A")) {
            String latStr = s.next();
            System.out.println(latStr);
            try (java.util.Scanner s1 = new java.util.Scanner(new java.net.URL("https://api.ipdata.co/longitude?api-key=f487d6645da13203ebca57238720b207d7218473945be2a13749ca20").openStream(), "UTF-8").useDelimiter("\\A")) {
                String longStr = s1.next();
                System.out.println(longStr);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    
}
