/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@XmlRootElement
public class Traffic {

    private int Id;
    private String city;
    private String Rue;
    private String Cause;
    private int IdUser;
    private Timestamp Temps;
    public Traffic( String city, String Rue, String Cause, int IdUser, Timestamp Temps) {
       
        this.city = city;
        this.Rue = Rue;
        this.Cause = Cause;
        this.IdUser = IdUser;
        this.Temps = Temps;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRue(String Rue) {
        this.Rue = Rue;
    }

    public void setCause(String Cause) {
        this.Cause = Cause;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public void setTemps(Timestamp Temps) {
        this.Temps = Temps;
    }

    public int getId() {
        return Id;
    }

    public String getCity() {
        return city;
    }

    public String getRue() {
        return Rue;
    }

    public String getCause() {
        return Cause;
    }

    public int getIdUser() {
        return IdUser;
    }

    public Timestamp getTemps() {
        return Temps;
    }
    
     

 
   
    
    
    
}
