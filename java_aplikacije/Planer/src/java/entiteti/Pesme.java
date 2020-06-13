/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jovana
 */
@Entity
@Table(name = "pesme")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pesme.findAll", query = "SELECT p FROM Pesme p")
    , @NamedQuery(name = "Pesme.findById", query = "SELECT p FROM Pesme p WHERE p.id = :id")
    , @NamedQuery(name = "Pesme.findByNaziv", query = "SELECT p FROM Pesme p WHERE p.naziv = :naziv")})
public class Pesme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "naziv")
    private String naziv;
    @Lob
    @Size(max = 65535)
    @Column(name = "url")
    private String url;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idP")
    private Collection<Reprodukovano> reprodukovanoCollection;

    public Pesme() {
    }

    public Pesme(Integer id) {
        this.id = id;
    }

    public Pesme(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public Collection<Reprodukovano> getReprodukovanoCollection() {
        return reprodukovanoCollection;
    }

    public void setReprodukovanoCollection(Collection<Reprodukovano> reprodukovanoCollection) {
        this.reprodukovanoCollection = reprodukovanoCollection;
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
        if (!(object instanceof Pesme)) {
            return false;
        }
        Pesme other = (Pesme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Pesme[ id=" + id + " ]";
    }
    
}
