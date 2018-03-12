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
import javax.persistence.ManyToOne;

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
    
    @ManyToOne //bidirectional
    private Employee employee;
    
    @ManyToOne
    private Medium medium;
    
    @ManyToOne
    private Client client;

    public Voyance() {
    }

    //?? plutôt les id et pas un client en entier ?

    public Voyance(Employee employee, Medium medium, Client client) {
        this.employee = employee;
        this.medium = medium;
        this.client = client;
    }

    public int getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(int beginHour) {
        this.beginHour = beginHour;
    }

    public Employee getEmployee() {
        return employee;
    }
    
    public Long getIdVoyance() {
        return idVoyance;
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

    public Medium getMedium() {
        return medium;
    }

    public Client getClient() {
        return client;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        return "Voyance{" + "idVoyance=" + idVoyance + ", beginHour=" + beginHour + ", endHour=" + endHour + ", comment=" + comment + ", employee=" + employee + ", medium=" + medium + ", client=" + client + '}';
    }
    
}
