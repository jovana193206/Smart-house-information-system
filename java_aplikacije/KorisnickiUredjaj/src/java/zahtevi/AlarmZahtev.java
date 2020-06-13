/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zahtevi;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jovana
 */
public class AlarmZahtev implements Serializable {
    private Date trenutak;
    private long period; //u [ms]
    private String zvukZvona;

    public AlarmZahtev() {
        this.trenutak = null;
        this.period = 0;
        this.zvukZvona = null;
    }

    public AlarmZahtev(Date trenutak) {
        this.trenutak = trenutak;
        this.period = 0;
        this.zvukZvona = null;
    }

    public AlarmZahtev(Date trenutak, long period) {
        this.trenutak = trenutak;
        this.period = period;
        this.zvukZvona = null;
    }

    public AlarmZahtev(String zvukZvona) {
        this.trenutak = null;
        this.period = 0;
        this.zvukZvona = zvukZvona;
    }

    public Date getTrenutak() {
        return trenutak;
    }

    public void setTrenutak(Date trenutak) {
        this.trenutak = trenutak;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public String getZvukZvona() {
        return zvukZvona;
    }

    public void setZvukZvona(String zvukZvona) {
        this.zvukZvona = zvukZvona;
    }
    
}
