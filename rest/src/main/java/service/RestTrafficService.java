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
@Path("/traffic")
public class RestTrafficService {
    private TrafficDB db;
private List<Traffic> trafficList = new ArrayList<>();
    
    @GET
    @Path("/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Traffic> getAllTraffic(@PathParam(value = "city") String city) throws SQLException {
        String c="'"+city+"'";
    try {
      db = new TrafficDB();
        Connection connection = db.DBcnx();

        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM traffic WHERE city="+c );
          

            while(rs.next()) {
                
                Traffic t= new Traffic( rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getTimestamp(6));
                trafficList.add(t);
            
            }
            return trafficList;
        } else {
            // Handle the case where the database connection is null
            return null;
        }
        
    } catch (SQLException e) {
        // Handle SQL exceptions, log or rethrow as needed
        throw e;
    }
}
    
    @POST
    @Path("/")
    public Response saveTraffic(
            
            @FormParam("col2") String col2,
            @FormParam("col3") String col3,
            @FormParam("col4") String col4) {
        Timestamp col6 = new Timestamp(System.currentTimeMillis());
        Traffic traffic = new Traffic( col2, col3, col4, 1, col6);

        // Utilisez l'objet Traffic pour effectuer l'insertion dans la base de données
        try {
            db = new TrafficDB();
            try (Connection connection = db.DBcnx()) {
                if (connection != null) {
                    String sql = "INSERT INTO traffic ( city, rue, cause, iduser,temps) VALUES ( ?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                        preparedStatement.setString(1, "'"+traffic.getCity()+"'");
                        preparedStatement.setString(2, "'"+traffic.getRue()+"'");
                        preparedStatement.setString(3, "'"+traffic.getCause()+"'");
                        preparedStatement.setInt(4, traffic.getIdUser());
                        preparedStatement.setTimestamp(5, traffic.getTemps());

                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            // La requête a réussi
                            return Response.status(Response.Status.CREATED).entity(" Product added successfuly!<br> Id: "+col2+"<br> Name: " + col3+"<br> Price: "+col4)  
            .build(); 
                        }
                    }
                }

                // Handle the case where the database connection is null or insertion failed
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(" Product added successfuly!<br> ")  
            .build(); 
            }
        } catch (SQLException e) {
            // Handle SQL exceptions, log or rethrow as needed
            throw new RuntimeException("Error saving traffic", e);
        }
    }
}
