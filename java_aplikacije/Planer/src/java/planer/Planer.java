/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planer;

import entiteti.Obaveze;
import io.ipgeolocation.api.IPGeolocationAPI;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import zahtevi.AlarmZahtev;

/**
 *
 * @author Jovana
 */
public class Planer {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory conFactory;
    @Resource(lookup = "PlanerQueue")
    private static Queue planQueue;
    @Resource(lookup = "ScheduleQueue")
    private static Queue schQueue;
    @Resource(lookup = "AlarmQueue")
    static Queue alarmQueue;
    
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;
    
    private static double faksX = 44.807;
    private static double faksY = 20.474;
    
    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("PlanerPU");
        em = emf.createEntityManager();
        
        try {
            JMSContext context = conFactory.createContext();
            JMSConsumer consumer = context.createConsumer(planQueue);
            JMSProducer producer = context.createProducer();
            
            while(true){
                System.out.println("Waiting for requests...");
                Message message = consumer.receive();
                Obaveze obaveza = (Obaveze)((ObjectMessage)message).getObject();
                int vrstaZahteva = message.getIntProperty("vrstaZahteva");
                //vrsta zahteva: 0-dodajObavezu, 1-MenjajObavezu, 2-Izlistaj, 3-ObrisiObavezu, 4-AktivirajPodsetnik
                switch(vrstaZahteva) {
                    case 0:
                        System.out.println("Add engagement request received");
                        em.getTransaction().begin();
                        em.persist(obaveza);
                        em.getTransaction().commit();
                        break;
                    case 1:
                        System.out.println("Change engagement request received");
                        Obaveze retrived = em.find(Obaveze.class, obaveza.getId());
                        em.getTransaction().begin();
                        if(obaveza.getCoordX() != null) retrived.setCoordX(obaveza.getCoordX());
                        if(obaveza.getCoordY() != null) retrived.setCoordY(obaveza.getCoordY());
                        if(obaveza.getOpis() != null) retrived.setOpis(obaveza.getOpis());
                        if(obaveza.getVreme() != null) retrived.setVreme(obaveza.getVreme());
                        em.getTransaction().commit();
                        break;
                    case 2:
                        System.out.println("List engagements request received");
                        Vector<Obaveze> obaveze = new Vector<>();
                        String getSchedule = "select o from Obaveze o ";
                        TypedQuery<Obaveze> q = em.createQuery(getSchedule, Obaveze.class);
                        obaveze = (Vector<Obaveze>)q.getResultList();
                        ObjectMessage objectMessage = context.createObjectMessage();
                        objectMessage.setObject(obaveze);
                        producer.send(schQueue, objectMessage);
                        break;
                    case 3:
                        System.out.println("Delete engagement request received");
                        em.getTransaction().begin();
                        int deleted = em.createQuery("delete from Obaveze where id = " + obaveza.getId() + " ").executeUpdate();
                        em.getTransaction().commit();
                        break;
                    case 4:
                        Obaveze retrived1 = em.find(Obaveze.class, obaveza.getId());
                        System.out.println("Set engagement alarm request received");
                        double x = retrived1.getCoordX().doubleValue(), y = retrived1.getCoordY().doubleValue();
                        //long potrebnoVreme = KalkulatorRazdaljine.razdaljina(x, y, faksX, faksY);
                        long potrebnoVreme = KalkulatorRazdaljine.razdaljina(x, y);
                        Date podsetnikTrenutak = new Date(retrived1.getVreme().getTime() - potrebnoVreme);
                        AlarmZahtev podsetnik = new AlarmZahtev(podsetnikTrenutak);
                        ObjectMessage alarmMsg = context.createObjectMessage(podsetnik);
                        alarmMsg.setIntProperty("vrstaZahteva", 0);
                        producer.send(alarmQueue, alarmMsg);
                        break;
                    default:
                        System.out.println("Pogresan identifikator zahteva");
                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(Planer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
