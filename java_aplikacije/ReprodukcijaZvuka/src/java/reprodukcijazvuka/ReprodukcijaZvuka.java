/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reprodukcijazvuka;

import entiteti.Pesme;
import entiteti.Reprodukovano;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jovana
 */
public class ReprodukcijaZvuka {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory conFactory;
    @Resource(lookup = "ReproductionQueue")
    private static Queue repQueue;
    @Resource(lookup = "HistoryQueue")
    private static Queue histQueue;
    
    public static void reprodukujPesmu(String naziv, EntityManager em, boolean arhiviraj) {
        String getURI = "select p from Pesme as p where p.naziv = '" + naziv + "' ";
        TypedQuery<Pesme> q = em.createQuery(getURI, Pesme.class);
        List<Pesme> result = q.getResultList();
        Pesme pesma = null;
        String url = "https://youtu.be/xaXLLF2qm20";
        if(!result.isEmpty()) {
            pesma = result.get(0);
            url = pesma.getUrl();
        }
        else {
            System.out.println("Trazena pesma ne postoji u bazi");
            return;
        }
         //Desktop desktop = Desktop.getDesktop();
        try {
            //desktop.browse(new URI(url));
            Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome " + url});
            if(arhiviraj) {
                String getMaxId = "select r.id from Reprodukovano r order by r.id desc ";
                TypedQuery<Integer> q1 = em.createQuery(getMaxId, Integer.class);
                Integer maxId = 0;
                List<Integer> result1 = q1.getResultList();
                if(!result1.isEmpty()) {
                    maxId = result1.get(0);
                }
                em.getTransaction().begin();
                Reprodukovano rep = new Reprodukovano(maxId + 1);
                rep.setVreme(new Date());
                rep.setIdP(pesma);
                em.persist(rep);
                em.getTransaction().commit();
            }
        } catch (IOException ex) {
            Logger.getLogger(ReprodukcijaZvuka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Vector<String> sveReprodukovano(EntityManager em) {
        Vector<String> songs = new Vector<>();
        String getSongs = "select distinct P.naziv from Pesme P, Reprodukovano R where P.id = R.idP.id ";
        TypedQuery<String> q = em.createQuery(getSongs, String.class);
        songs = (Vector<String>)q.getResultList();
        return songs;
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReprodukcijaZvukaPU");
        EntityManager em = emf.createEntityManager();
        
        try {
            Destination destination = repQueue;
            JMSContext context = conFactory.createContext();
            JMSConsumer consumer = context.createConsumer(destination);
            JMSProducer producer = context.createProducer();
            
            while(true){
                System.out.println("Waiting for requests...");
                Message message = consumer.receive();
                if(message instanceof TextMessage){
                    int vrstaZahteva = ((TextMessage)message).getIntProperty("vrstaZahteva");
                    //vrstaZahteva: 0-istorija reprodukcija, 1-reprodukuj pesmu
                    switch (vrstaZahteva) {
                        case 0:
                            System.out.println("History request received");
                            Vector<String> history = sveReprodukovano(em);
                            ObjectMessage objectMessage = context.createObjectMessage();
                            objectMessage.setObject(history);
                            producer.send(histQueue, objectMessage);
                            break;
                        case 1:
                            System.out.println("Play request received");
                            boolean arhiviraj = ((TextMessage)message).getBooleanProperty("arhiviraj");
                            reprodukujPesmu(((TextMessage)message).getText(), em, arhiviraj);
                            break;
                        default:
                            System.out.println("Primljen je nepodrzan zahtev.");
                            break;
                    }
                }
                else{
                    System.out.println("Primljen je nepodrzan zahtev.");
                    break;
                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(ReprodukcijaZvuka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
