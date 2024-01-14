/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@XmlRootElement
public class CPT implements Serializable{

    private int code;
    private double solde;
    private Date dateCreation;

    public CPT() {
    }

    public CPT(int code, double solde, Date dateCreation) {
        this.code = code;
        this.solde = solde;
        this.dateCreation = dateCreation;
    }

    public int getcode() {
        return code;
    }

    public void setcode(int code) {
        this.code = code;
    }

    public double getsolde() {
        return solde;
    }

    public void setsolde(double solde) {
        this.solde = solde;
    }

    public Date getdateCreation() {
        return dateCreation;
    }

    public void setdateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

}

