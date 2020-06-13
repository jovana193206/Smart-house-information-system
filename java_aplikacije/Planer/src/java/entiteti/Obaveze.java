/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jovana
 */
@Entity
@Table(name = "obaveze")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Obaveze.findAll", query = "SELECT o FROM Obaveze o")
    , @NamedQuery(name = "Obaveze.findById", query = "SELECT o FROM Obaveze o WHERE o.id = :id")
    , @NamedQuery(name = "Obaveze.findByCoordX", query = "SELECT o FROM Obaveze o WHERE o.coordX = :coordX")
    , @NamedQuery(name = "Obaveze.findByCoordY", query = "SELECT o FROM Obaveze o WHERE o.coordY = :coordY")
    , @NamedQuery(name = "Obaveze.findByVreme", query = "SELECT o FROM Obaveze o WHERE o.vreme = :vreme")
    , @NamedQuery(name = "Obaveze.findByOpis", query = "SELECT o FROM Obaveze o WHERE o.opis = :opis")})
public class Obaveze implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "coordX")
    private BigDecimal coordX;
    @Column(name = "coordY")
    private BigDecimal coordY;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vreme;
    @Size(max = 100)
    @Column(name = "opis")
    private String opis;

    public Obaveze() {
    }

    public Obaveze(Integer id) {
        this.id = id;
    }

    public Obaveze(Integer id, Date vreme) {
        this.id = id;
        this.vreme = vreme;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCoordX() {
        return coordX;
    }

    public void setCoordX(BigDecimal coordX) {
        this.coordX = coordX;
    }

    public BigDecimal getCoordY() {
        return coordY;
    }

    public void setCoordY(BigDecimal coordY) {
        this.coordY = coordY;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
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
        if (!(object instanceof Obaveze)) {
            return false;
        }
        Obaveze other = (Obaveze) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Obaveze[ id=" + id + " ]";
    }
    
}
