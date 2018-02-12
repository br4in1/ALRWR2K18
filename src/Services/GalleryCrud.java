/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Gallery;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class GalleryCrud {
    
    
  public static void AjouterImage(Gallery G){
        Connection con = DataSource.getInstance().getCon();
        String query = "INSERT INTO Gallery VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ste = con.prepareStatement(query);
            ste.setInt(1, G.getId());
            ste.setInt(2,G.getIdUser());
            ste.setString(3, G.getUrl());
            ste.setString(4, G.getDescription());
            ste.setString(5, G.getEmplacement());

            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GalleryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    
    
    public static List<String> AfficherImageFromDB() {
        Connection con = DataSource.getInstance().getCon();
        List<String> list = new ArrayList<>();

        String req = "select Emplacement from gallery";
        try{
        PreparedStatement ste = con.prepareStatement(req);
        ResultSet result = ste.executeQuery();//select 
        while (result.next()) {
            list.add(result.getString(1));
        }
        return list;
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
                
    }

  
     

    
}
