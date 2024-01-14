/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
@Path("/user")
public class RestUserService {
     private TrafficDB db;
      @POST
    @Path("/")
    public Response saveTraffic(
            @FormParam("col1") int col1,
            @FormParam("col5") String col5,
            @FormParam("col2") String col2,
            @FormParam("col3") String col3,
            @FormParam("col4") String col4) {
        
        User user = new User( col1, col2, col3, col4,col5 );

        // Utilisez l'objet Traffic pour effectuer l'insertion dans la base de données
        try {
            db = new TrafficDB();
            try (Connection connection = db.DBcnx()) {
                if (connection != null) {
                    String sql = "INSERT INTO user ( cin, nom, prenom, email,mdp) VALUES ( ?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                        preparedStatement.setInt(1, user.getCin());
                        preparedStatement.setString(2, "'"+user.getNom()+"'");
                        preparedStatement.setString(3, "'"+user.getPrenom()+"'");
                        preparedStatement.setString(4, "'"+user.getEmail()+"'");
                        preparedStatement.setString(5, "'"+user.getMdp()+"'");

                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            // La requête a réussi
                            return Response.status(Response.Status.CREATED).entity(" User added successfuly!<br> Id: "+col1+"<br> Name: " + col2)  
            .build(); 
                        }
                    }
                }

                // Handle the case where the database connection is null or insertion failed
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(" !<br> ")  
            .build(); 
            }
        } catch (SQLException e) {
            // Handle SQL exceptions, log or rethrow as needed
            throw new RuntimeException("Error saving user", e);
        }
    }

}
    

