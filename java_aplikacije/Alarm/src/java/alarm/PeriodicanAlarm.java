/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarm;

import static alarm.Alarm.conFactory;
import static java.lang.Thread.sleep;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;

/**
 *
 * @author Jovana
 */
public class PeriodicanAlarm extends Thread {

    private Date trenutak;
    private long period; //[ms]

    public PeriodicanAlarm(Date trenutak, long period) {
        this.trenutak = trenutak;
        this.period = period;
    }

    @Override
    public void run() {
        JMSContext context = conFactory.createContext();
        JMSProducer producer = context.createProducer();
        Date currentTime = new Date();
        long timeUntilAlarm = 0;
        if (trenutak.after(currentTime)) {
            timeUntilAlarm = trenutak.getTime() - currentTime.getTime();
        } else {
            long razlika = currentTime.getTime() - trenutak.getTime();
            long x = razlika / period;
            if((razlika % period) != 0) x++;
            timeUntilAlarm = period * x - razlika;
        }
        try {
            sleep(timeUntilAlarm);
            while (true) {
                TextMessage msg = context.createTextMessage(Alarm.zvono);
                msg.setIntProperty("vrstaZahteva", 1);
                msg.setBooleanProperty("arhiviraj", false);
                producer.send(Alarm.repQueue, msg);
                sleep(period);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(JednokratniAlarm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(JednokratniAlarm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
