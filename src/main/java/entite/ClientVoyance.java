/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author LENOVO
 */

public class ClientVoyance {
    
    private Date beginDate;
    
    private Date beginHour;
  
    private Date endHour;
    
    private String medium;
    
    public ClientVoyance(Voyance v) {
        this.beginDate=v.getBeginDate();
        this.beginHour=v.getBeginHour();
        this.endHour=v.getEndHour();
        this.medium=v.getMedium().getName();
    }
    
    public Date getBeginDate() {
        return beginDate;
    }

    public Date getBeginHour() {
        return beginHour;
    }

    public Date getEndHour() {
        return endHour;
    }
    
    public String getMedium() {
        return medium;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setBeginHour(Date beginHour) {
        this.beginHour = beginHour;
    }

    public void setEndHour(Date endHour) {
        this.endHour = endHour;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
    
    @Override
    public String toString() {
        return "ClientVoyance{" + "beginDate=" + beginDate + ", beginHour=" + beginHour + ", endHour=" + endHour + ", medium=" + medium + '}';
    }
    
    
    
}
