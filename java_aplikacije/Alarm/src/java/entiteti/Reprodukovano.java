/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jovana
 */
@Entity
@Table(name = "reprodukovano")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reprodukovano.findAll", query = "SELECT r FROM Reprodukovano r")
    , @NamedQuery(name = "Reprodukovano.findById", query = "SELECT r FROM Reprodukovano r WHERE r.id = :id")
    , @NamedQuery(name = "Reprodukovano.findByVreme", query = "SELECT r FROM Reprodukovano r WHERE r.vreme = :vreme")})
public class Reprodukovano implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vreme;
    @JoinColumn(name = "idP", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pesme idP;

    public Reprodukovano() {
    }

    public Reprodukovano(Integer id) {
        this.id = id;
    }

    public Reprodukovano(Integer id, Date vreme) {
        this.id = id;
        this.vreme = vreme;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public Pesme getIdP() {
        return idP;
    }

    public void setIdP(Pesme idP) {
        this.idP = idP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reprodukovano)) {
            return false;
        }
        Reprodukovano other = (Reprodukovano) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Reprodukovano[ id=" + id + " ]";
    }
    
}
