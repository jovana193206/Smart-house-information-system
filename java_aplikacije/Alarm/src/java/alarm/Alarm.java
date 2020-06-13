/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarm;

import zahtevi.AlarmZahtev;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 *
 * @author Jovana
 */
public class Alarm {
    
    static String zvono = "default alarm";
    //Konstante za ponudjene trenutke
    static long minut = 60000;
    static long petnaestMin = 900000;
    static long polaSata = 1800000;
    static long sat = 3600000;
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    static ConnectionFactory conFactory;
    @Resource(lookup = "ReproductionQueue")
    static Queue repQueue;
    @Resource(lookup = "AlarmQueue")
    static Queue alarmQueue;
    
    public static void main(String[] args) {
        try {
            JMSContext context = conFactory.createContext();
            JMSConsumer consumer = context.createConsumer(alarmQueue);
            
            while(true){
                System.out.println("Waiting for requests...");
                Message message = consumer.receive();
                AlarmZahtev zahtev = (AlarmZahtev)((ObjectMessage)message).getObject();
                int vrstaZahteva = message.getIntProperty("vrstaZahteva");
                //vrsta zahteva: 0-zadatTrenutak, 1-periodican, 2-ponudjeniTrenutak, 3-promenaZvona
                switch(vrstaZahteva) {
                    case 0:
                        System.out.println("One time alarm set");
                        JednokratniAlarm a = new JednokratniAlarm(zahtev.getTrenutak());
                        a.start();
                        break;
                    case 1:
                        System.out.println("Periodic alarm set");
                        PeriodicanAlarm p = new PeriodicanAlarm(zahtev.getTrenutak(), zahtev.getPeriod());
                        p.start();
                        break;
                    case 2:
                        System.out.println("Offered moment alarm set");
                        int i = message.getIntProperty("trenutak");
                        long addTime = 0;
                        switch(i) {
                            case 1:
                                addTime = minut;
                                break;
                            case 2:
                                addTime = petnaestMin;
                                break;
                            case 3: 
                                addTime = polaSata;
                                break;
                            case 4:
                                addTime = sat;
                                break;
                            default:
                        }
                        Date currentTime = new Date();
                        Date zadajVreme = new Date(currentTime.getTime() + addTime);
                        JednokratniAlarm j = new JednokratniAlarm(zadajVreme);
                        j.start();
                        break;
                    case 3:
                        System.out.println("Alarm sound change");
                        zvono = zahtev.getZvukZvona();
                        break;
                    default:
                        System.out.println("Pogresan identifikator zahteva");
                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(Alarm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

