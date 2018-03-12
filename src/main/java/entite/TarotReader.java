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
public class TarotReader extends Medium implements Serializable {
    
    private String cards;

    public TarotReader(String cards, String name, String biography, String talent) {
        super(name, biography, talent);
        this.cards = cards;
    }

    public TarotReader() {
    }
    

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "TarotReader{" + "cards=" + cards + '}';
    }
    
    
}