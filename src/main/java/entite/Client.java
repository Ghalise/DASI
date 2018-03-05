/*
 * La classe Client permet d'implémenter un client, de demander une voyance, d'accéder au profil astrologique ou aux coordonnées du client.
 */
package entite;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author erouille
 */
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
   // public static enum Civilite{M,F,A};
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClient;
    
   // private Civilite civilite;
    
    private String nom;
    
    private String prenom;
    
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    public Client() {
    }

    public Client( String nom, String prenom, String dateNaissance) {
        try {
            //this.civilite=civilite;
            this.nom = nom;
            this.prenom = prenom;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dn = sdf.parse(dateNaissance);
            this.dateNaissance = dn;
        } catch (ParseException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void setCivilite(Civilite civilite) {
//        this.civilite = civilite;
//    }
//           
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

//     public Civilite getCivilite() {
//       return civilite;
//    }
      
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public Long getId() {
        return idClient;
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
    

//    @Override
//    public String toString() {
//        return "Client{" + "idClient=" + idClient + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + '}';
//    }

    @Override
    public String toString() {
        return "Client{" + "idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + '}';
    }

}
