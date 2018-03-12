/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author erouille
 */
@Entity
public class Medium implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMedium;
    
    private String name;
    
    private String biography;
    
    private String talent;

    public Medium() {
    }

    public Medium(String name, String biography, String talent) {
        this.name = name;
        this.biography = biography;
        this.talent = talent;
    }

    public Long getId() {
        return idMedium;
    }

    public void setId(Long id) {
        this.idMedium = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedium != null ? idMedium.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medium)) {
            return false;
        }
        Medium other = (Medium) object;
        if ((this.idMedium == null && other.idMedium != null) || (this.idMedium != null && !this.idMedium.equals(other.idMedium))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Medium[ id=" + idMedium + " ]";
    }
    
}
