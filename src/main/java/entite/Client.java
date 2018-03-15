/*
 * La classe Client permet d'implémenter un client, de demander une voyance, d'accéder au profil astrologique ou aux coordonnées du client.
 */
package entite;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.AstroTest;

/**
 *
 * @author erouille
 */
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClient;
    
    private String gender;
    
    private String surname;
    
    private String firstname;
    
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    
    @Embedded
    private Information information;
    
    @Embedded
    private AstroProfile astroProfile;

    public Client() {
    }

    public Client(String gender, String surname, String firstname, String birthDate, Information information) {
        try {
            this.gender=gender;
            this.surname = surname;
            this.firstname = firstname;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dn = sdf.parse(birthDate);
            this.birthDate = dn;
            this.information=information;
            createAstroProfile();
        } catch (ParseException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getIdClient() {
        return idClient;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public AstroProfile getAstroProfile() {
        return astroProfile;
    }
    
    

    public void createAstroProfile(){
        //@TODO probleme avec la clef
        AstroTest at=new AstroTest("ASTRO-01-M0lGLURBU0ktQVNUUk8tQjAx");
        try {
            astroProfile = new AstroProfile(at.getProfil(firstname, birthDate));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "idClient=" + idClient + ", surname=" + surname + ", firstname=" + firstname + ", birthDate=" + birthDate + ", information=" + information + '}';
    }

}
