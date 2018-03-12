/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import dao.JpaUtil;
import entite.Astrologer;
import entite.FortuneTeller;
import service.Service;

/**
 *
 * @author erouille
 */
public class TestMedium {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    public static void main(String[] args) {
        JpaUtil.init();
        FortuneTeller i = new FortuneTeller("Irma", "Raconte n'importe quoi", "Poissons morts");
        FortuneTeller b = new FortuneTeller("Bouldingue", "Invente des mots pour paraitre intelligent", "Kinder Bueno");
        Astrologer a = new Astrologer("As", "Se croit superieur a tout le monde", "AstroMag", "1945");
        Service s=new Service();
        s.createMedium(i);
        s.createMedium(b);
        s.createMedium(a);
        JpaUtil.destroy();
    }
    
}

