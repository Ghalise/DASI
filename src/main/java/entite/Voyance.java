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
public class Voyance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVoyance;
    
    private int beginHour;
    
    private int endHour;
    
    private String comment;

    public Voyance() {
    }
    
    public Voyance(int beginHour, int endHour, String comment) {
        this.beginHour = beginHour;
        this.endHour = endHour;
        this.comment = comment;
    }

    public Long getIdVoyance() {
        return idVoyance;
    }

    public void setIdVoyance(Long idVoyance) {
        this.idVoyance = idVoyance;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return idVoyance;
    }

    public void setId(Long id) {
        this.idVoyance = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoyance != null ? idVoyance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voyance)) {
            return false;
        }
        Voyance other = (Voyance) object;
        if ((this.idVoyance == null && other.idVoyance != null) || (this.idVoyance != null && !this.idVoyance.equals(other.idVoyance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Voyance[ id=" + idVoyance + " ]";
    }
    
}
