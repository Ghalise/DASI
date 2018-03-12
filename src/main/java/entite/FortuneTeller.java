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
public class FortuneTeller extends Medium implements Serializable {

    private String media;

    public FortuneTeller() {
    }

    public FortuneTeller(String media, String name, String biography) {
        super(name, biography);
        this.media = media;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return super.toString() + "FortuneTeller{" + "media=" + media + '}';
    }
    
}
