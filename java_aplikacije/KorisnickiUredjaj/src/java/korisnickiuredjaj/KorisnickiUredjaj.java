/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korisnickiuredjaj;

import entiteti.Obaveze;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import zahtevi.AlarmZahtev;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 *
 * @author Jovana
 */
public class KorisnickiUredjaj {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory conFactory;
    @Resource(lookup = "ReproductionQueue")
    private static Queue repQueue;
    @Resource(lookup = "HistoryQueue")
    private static Queue histQueue;
    @Resource(lookup = "AlarmQueue")
    static Queue alarmQueue;
    @Resource(lookup = "PlanerQueue")
    private static Queue planQueue;
    @Resource(lookup = "ScheduleQueue")
    private static Queue schQueue;

    public static void main(String[] args) {

        JMSContext context = conFactory.createContext();
        JMSConsumer histConsumer = context.createConsumer(histQueue);
        JMSProducer producer = context.createProducer();
        JMSConsumer schConsumer = context.createConsumer(schQueue);

        try {
            /*
            //Pustanje muzike
            TextMessage play = context.createTextMessage("Gigi DAgostino - LAmour Toujours");
            play.setBooleanProperty("arhiviraj", true);
            play.setIntProperty("vrstaZahteva", 1);
            producer.send(repQueue, play);
            play.setText("Beethoven - Moonlight Sonata");
            producer.send(repQueue, play);
            //Pregled istorije reprodukovanih pesama
            TextMessage getHistory = context.createTextMessage();
            getHistory.setIntProperty("vrstaZahteva", 0);
            producer.send(repQueue, getHistory);
            Message msg = histConsumer.receive();
            ObjectMessage objMsg = (ObjectMessage)msg;
            Vector<String> history = (Vector<String>) objMsg.getObject();
            System.out.println("Sve do sada reprodukovane pesme: ");
            for(String song : history) System.out.println(song);
            //Alarm u zadatom trenutku
            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(2019, 5, 30, 21, 15, 0);
            AlarmZahtev setAlarm = new AlarmZahtev(cal.getTime());
            ObjectMessage alarmMsg = context.createObjectMessage(setAlarm);
            alarmMsg.setIntProperty("vrstaZahteva", 0);
            producer.send(alarmQueue, alarmMsg);
            //Periodican alarm
            Calendar cal2 = Calendar.getInstance();
            cal2.clear();
            cal2.set(2019, 5, 30, 0, 11, 0);
            AlarmZahtev setAlarm1 = new AlarmZahtev(cal2.getTime());
            setAlarm1.setTrenutak(cal2.getTime());
            setAlarm1.setPeriod(60000);  //1min
            ObjectMessage alarmMsg1 = context.createObjectMessage(setAlarm1);
            alarmMsg1.setIntProperty("vrstaZahteva", 1);
            producer.send(alarmQueue, alarmMsg1);
            //Random alarm
            AlarmZahtev randAlarm = new AlarmZahtev();
            ObjectMessage alarmRandMsg = context.createObjectMessage(randAlarm);
            alarmRandMsg.setIntProperty("vrstaZahteva", 2);
            producer.send(alarmQueue, alarmRandMsg);
            //Promena zvona
            AlarmZahtev alarmSound = new AlarmZahtev("Mahmut Orhan - Save me");
            ObjectMessage alarmSoundMsg = context.createObjectMessage(alarmSound);
            alarmSoundMsg.setIntProperty("vrstaZahteva", 3);
            producer.send(alarmQueue, alarmSoundMsg);
            //Dodavanje obaveze
            Obaveze obaveza = new Obaveze();
            obaveza.setCoordX(new BigDecimal(44.812));  //latitude
            obaveza.setCoordY(new BigDecimal(20.454));  //longitude
            obaveza.setOpis("sminka");
            Calendar cal1 = Calendar.getInstance();
            cal1.clear();
            cal1.set(2019, 6, 1, 4, 50);
            obaveza.setVreme(cal1.getTime());
            ObjectMessage omsg = context.createObjectMessage(obaveza);
            omsg.setIntProperty("vrstaZahteva", 0);
            producer.send(planQueue, omsg);
            //Podesavanje podsetnika
            omsg.setIntProperty("vrstaZahteva", 4);
            producer.send(planQueue, omsg);
            //Menjaj obavezu
            Obaveze obaveza1 = new Obaveze(2);
            obaveza1.setOpis("Sastanak promenjeno");
            ObjectMessage omsg1 = context.createObjectMessage(obaveza1);
            omsg1.setIntProperty("vrstaZahteva", 1);
            producer.send(planQueue, omsg1);
            //Izlistaj obaveze
            omsg1.setIntProperty("vrstaZahteva", 2);
            producer.send(planQueue, omsg1);
            Message msg1 = schConsumer.receive();
            ObjectMessage objSchMsg = (ObjectMessage)msg1;
            Vector<Obaveze> schedule = (Vector<Obaveze>) objSchMsg.getObject();
            System.out.println("Sve obaveze: ");
            System.out.println("______________________________");
            for(Obaveze o : schedule) {
                System.out.println("Id: " + o.getId());
                System.out.println("Opis: " + o.getOpis());
                System.out.println("Datum i vreme: " + o.getVreme());
                System.out.println("Latitude: " + o.getCoordX());
                System.out.println("Longitude: " + o.getCoordY());
                System.out.println("------------------------------");
            }
            //Obrisi obavezu
            Obaveze deleteO = new Obaveze(5);
            ObjectMessage delMsg = context.createObjectMessage(deleteO);
            delMsg.setIntProperty("vrstaZahteva", 3);
            producer.send(planQueue, delMsg);
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("*************************************************");
                System.out.println();
                System.out.println("Unesite indeks zeljene akcije: ");
                System.out.println("=======================================");
                System.out.println("Reprodukuj pesmu: 1");
                System.out.println("Izlistaj sve reprodukovane pesme: 2");
                System.out.println("Podesi jednokratni alarm: 3");
                System.out.println("Podesi periodican alarm: 4");
                System.out.println("Podesi alarm u nekom ponudjenom trenuktu: 5");
                System.out.println("Promeni ton alarma: 6");
                System.out.println("Dodaj obavezu: 7");
                System.out.println("Izmeni obavezu: 8");
                System.out.println("Izlistaj sve obaveze: 9");
                System.out.println("Obrisi obavezu: 10");
                System.out.println("Aktiviraj podsetnik za obavezu: 11");
                System.out.println("_________________________________________");

                try {
                    int akcija = Integer.parseInt(reader.readLine());
                    switch (akcija) {
                        case 1:
                            System.out.println("Unesite naziv zeljene pesme");
                            String naziv = reader.readLine();
                            TextMessage play = context.createTextMessage(naziv);
                            play.setBooleanProperty("arhiviraj", true);
                            play.setIntProperty("vrstaZahteva", 1);
                            producer.send(repQueue, play);
                            break;
                        case 2:
                            TextMessage getHistory = context.createTextMessage();
                            getHistory.setIntProperty("vrstaZahteva", 0);
                            producer.send(repQueue, getHistory);
                            Message msg = histConsumer.receive();
                            ObjectMessage objMsg = (ObjectMessage) msg;
                            Vector<String> history = (Vector<String>) objMsg.getObject();
                            System.out.println("Sve do sada reprodukovane pesme: ");
                            for (String song : history) {
                                System.out.println(song);
                            }
                            break;
                        case 3:
                            System.out.println("Unesite vreme za alarm u formatu: yyyy.MM.dd HH:mm:ss");
                            DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                            try {
                                Date trenutak = df.parse(reader.readLine());
                                AlarmZahtev setAlarm = new AlarmZahtev(trenutak);
                                ObjectMessage alarmMsg = context.createObjectMessage(setAlarm);
                                alarmMsg.setIntProperty("vrstaZahteva", 0);
                                producer.send(alarmQueue, alarmMsg);

                            } catch (ParseException ex) {
                                Logger.getLogger(KorisnickiUredjaj.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println("Pogresan unos vremena za alarm!");
                            }
                            break;
                        case 4:
                            System.out.println("Unesite vreme za alarm u formatu: yyyy.MM.dd HH:mm:ss");
                            DateFormat df1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                            try {
                                Date trenutak = df1.parse(reader.readLine());
                                System.out.println("Unesite period alarma u milisekundama");
                                long period = Long.parseLong(reader.readLine());
                                AlarmZahtev setAlarm1 = new AlarmZahtev(trenutak);
                                setAlarm1.setPeriod(period);
                                ObjectMessage alarmMsg1 = context.createObjectMessage(setAlarm1);
                                alarmMsg1.setIntProperty("vrstaZahteva", 1);
                                producer.send(alarmQueue, alarmMsg1);

                            } catch (ParseException ex) {
                                Logger.getLogger(KorisnickiUredjaj.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println("Pogresan unos vremena za alarm!");
                            }
                            break;
                        case 5:
                            System.out.println("Odaberite kada zelite da se oglasi alarm:");
                            System.out.println("- Za 1 minut: 1");
                            System.out.println("- Za 15 minuta: 2");
                            System.out.println("- Za pola sata: 3");
                            System.out.println("- Za 1 sat: 4");
                            int odabir = Integer.parseInt(reader.readLine());
                            AlarmZahtev randAlarm = new AlarmZahtev();
                            ObjectMessage alarmRandMsg = context.createObjectMessage(randAlarm);
                            alarmRandMsg.setIntProperty("vrstaZahteva", 2);
                            alarmRandMsg.setIntProperty("trenutak", odabir);
                            producer.send(alarmQueue, alarmRandMsg);
                            break;
                        case 6:
                            System.out.println("Unesite naziv pesme koju zelite da podesite kao ton alarma");
                            String ton = reader.readLine();
                            AlarmZahtev alarmSound = new AlarmZahtev(ton);
                            ObjectMessage alarmSoundMsg = context.createObjectMessage(alarmSound);
                            alarmSoundMsg.setIntProperty("vrstaZahteva", 3);
                            producer.send(alarmQueue, alarmSoundMsg);
                            break;
                        case 7:
                            System.out.println("Unesite vreme za alarm u formatu: yyyy.MM.dd HH:mm:ss");
                            DateFormat df2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                            try {
                                Date trenutak = df2.parse(reader.readLine());
                                System.out.println("Unesite opis obaveze");
                                String opis = reader.readLine();
                                System.out.println("Unesite latitude koordinatu lokacije");
                                double latitude = Double.parseDouble(reader.readLine());
                                System.out.println("Unesite longitude koordinatu lokacije");
                                double longitude = Double.parseDouble(reader.readLine());
                                Obaveze obaveza = new Obaveze();
                                obaveza.setCoordX(new BigDecimal(latitude));
                                obaveza.setCoordY(new BigDecimal(longitude));
                                obaveza.setOpis(opis);
                                obaveza.setVreme(trenutak);
                                ObjectMessage newO = context.createObjectMessage(obaveza);
                                newO.setIntProperty("vrstaZahteva", 0);
                                producer.send(planQueue, newO);
                            } catch (ParseException ex) {
                                Logger.getLogger(KorisnickiUredjaj.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println("Pogresan unos vremena!");
                            }
                            break;
                        case 8:
                            System.out.println("Unesite id obaveze koju zelite da izmenite");
                            int updateId = Integer.parseInt(reader.readLine());
                            System.out.println("Unesite vreme za alarm u formatu: yyyy.MM.dd HH:mm:ss");
                            DateFormat df3 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                            try {
                                Date trenutak = df3.parse(reader.readLine());
                                System.out.println("Unesite opis obaveze");
                                String opis = reader.readLine();
                                System.out.println("Unesite latitude koordinatu lokacije");
                                double latitude = Double.parseDouble(reader.readLine());
                                System.out.println("Unesite longitude koordinatu lokacije");
                                double longitude = Double.parseDouble(reader.readLine());
                                Obaveze obaveza = new Obaveze(updateId);
                                obaveza.setCoordX(new BigDecimal(latitude));
                                obaveza.setCoordY(new BigDecimal(longitude));
                                obaveza.setOpis(opis);
                                obaveza.setVreme(trenutak);
                                ObjectMessage newO = context.createObjectMessage(obaveza);
                                newO.setIntProperty("vrstaZahteva", 1);
                                producer.send(planQueue, newO);
                            } catch (ParseException ex) {
                                Logger.getLogger(KorisnickiUredjaj.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println("Pogresan unos vremena!");
                            }
                            break;
                        case 9:
                            ObjectMessage listO = context.createObjectMessage(new Obaveze());
                            listO.setIntProperty("vrstaZahteva", 2);
                            producer.send(planQueue, listO);
                            Message msg1 = schConsumer.receive();
                            ObjectMessage objSchMsg = (ObjectMessage) msg1;
                            Vector<Obaveze> schedule = (Vector<Obaveze>) objSchMsg.getObject();
                            System.out.println("Sve obaveze: ");
                            System.out.println("______________________________");
                            for (Obaveze o : schedule) {
                                System.out.println("Id: " + o.getId());
                                System.out.println("Opis: " + o.getOpis());
                                System.out.println("Datum i vreme: " + o.getVreme());
                                System.out.println("Latitude: " + o.getCoordX());
                                System.out.println("Longitude: " + o.getCoordY());
                                System.out.println("------------------------------");
                            }
                            break;
                        case 10:
                            System.out.println("Unesite id obaveze koju zelite da obrisete");
                            int deleteId = Integer.parseInt(reader.readLine());
                            Obaveze deleteO = new Obaveze(deleteId);
                            ObjectMessage delMsg = context.createObjectMessage(deleteO);
                            delMsg.setIntProperty("vrstaZahteva", 3);
                            producer.send(planQueue, delMsg);
                            break;
                        case 11:
                            System.out.println("Unesite id obaveze za koju zelite da aktivirate podsetnik");
                            int warnId = Integer.parseInt(reader.readLine());
                            Obaveze warnO = new Obaveze(warnId);
                            ObjectMessage warnMsg = context.createObjectMessage(warnO);
                            warnMsg.setIntProperty("vrstaZahteva", 4);
                            producer.send(planQueue, warnMsg);
                            break;
                        default:
                    }
                } catch (IOException ex) {
                    Logger.getLogger(KorisnickiUredjaj.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (JMSException ex) {
            Logger.getLogger(KorisnickiUredjaj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
