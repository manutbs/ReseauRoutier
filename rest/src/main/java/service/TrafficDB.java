/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class TrafficDB {
    
    public Connection conn;
   public Connection DBcnx() throws SQLException {
    try {
        String url = "jdbc:mysql://localhost:3306/traffic";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
        //System.out.println("Connexion avec Succès !");
        return conn;

    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Connexion échouée !");
        Logger.getLogger(TrafficDB.class.getName()).log(Level.SEVERE, null, e);
    }
    return conn;
}

}