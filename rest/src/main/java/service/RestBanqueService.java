/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.Null;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author User
 */
@Path("/banque")
public class RestBanqueService {
private TrafficDB db;
    private static Map<Integer, CPT> cpts = new HashMap<>();
    
    @GET
    @Path("/conversion/{montant}")
    @Produces(MediaType.APPLICATION_JSON)
    public double conversion(@PathParam(value = "montant") double mt) {
       
       
        return mt * 3.4;
    }

    @GET
    @Path("/CPTs/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public CPT getCPT(@PathParam(value = "code") int code) throws SQLException {
    try {
      db = new TrafficDB();
        Connection connection = db.DBcnx();

        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM compteb WHERE code=" + code);

            if (rs.next()) {
                return new CPT(code, rs.getFloat(2), rs.getDate(3));
            } else {
                // Handle the case where no result is found for the given code
                return null;
            }
        } else {
            // Handle the case where the database connection is null
            return null;
        }
    } catch (SQLException e) {
        // Handle SQL exceptions, log or rethrow as needed
        throw e;
    }
}

    @GET
    @Path("/Comptes")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CPT> ListeComptes() {
        return new ArrayList<>(cpts.values());
    }
    @PUT
    @Path("/Comptes/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public CPT update(CPT cp, @PathParam("code") int code) {
        cpts.put(code, cp);
        return cp;
    }
    @POST
    @Path("/Comptes")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
     public CPT save(CPT cp) {
        cpts.put(cp.getcode(), cp);
        return cp;
    }
    @DELETE
    @Path("/Comptes/{code}")
    @Produces(MediaType.APPLICATION_JSON) 
     public boolean delete(@PathParam("code")int code) {
        cpts.remove(code);
        return true;
    }
}
