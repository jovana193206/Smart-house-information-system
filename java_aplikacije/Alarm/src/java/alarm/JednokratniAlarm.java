/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarm;

import static alarm.Alarm.conFactory;
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
public class JednokratniAlarm extends Thread {
    
    private Date trenutak;

    public JednokratniAlarm(Date trenutak) {
        this.trenutak = trenutak;
    }
    
    @Override
    public void run() {
        JMSContext context = conFactory.createContext();
        JMSProducer producer = context.createProducer();
        Date currentTime = new Date();
        if(trenutak.after(currentTime)) {
            long timeUntilAlarm = trenutak.getTime() - currentTime.getTime();
            try {
                sleep(timeUntilAlarm);
                TextMessage msg = context.createTextMessage(Alarm.zvono);
                msg.setIntProperty("vrstaZahteva", 1);
                msg.setBooleanProperty("arhiviraj", false);
                producer.send(Alarm.repQueue, msg);
            } catch (InterruptedException ex) {
                Logger.getLogger(JednokratniAlarm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JMSException ex) {
                Logger.getLogger(JednokratniAlarm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
