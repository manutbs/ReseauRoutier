/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author User
 */
@XmlRootElement
public class User implements Serializable {

    private int Cin;
    private String Nom;
    private String Prenom;
    private String Email;
    private String Mdp;
    public int getCin() {
        return Cin;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public String getMdp() {
        return Mdp;
    }

    public User(int Cin, String Nom, String Prenom, String Email, String Mdp) {
        this.Cin = Cin;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Mdp = Mdp;
    }

    public void setCin(int Cin) {
        this.Cin = Cin;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }
    
   
    
}
