/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.mycompany.DataSource.DataSource;
import com.mycompany.Entities.Hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sof
 */
public class HotelCRUD {
    
     Connection con;
     
      public HotelCRUD(){
       con = DataSource.getInstance().getConnection();
    }
    
        public void ajouterHotel(Hotel p) throws SQLException
     {    
               String sql = "INSERT INTO `hotel`(`nom`, `geolat`, `geolong`, `nbEtoiles`, `tripAdvisorLink`, `image`, `city`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setString(1,p.getNom());
        ste.setDouble(2,p.getGeolat());
        ste.setDouble(3,p.getGeolong());
        ste.setInt(4,p.getNbEtoiles());
        ste.setString(5,p.getLink());
        ste.setString(6,p.getImage());
        ste.setString(7,p.getCity());
        
        ste.executeUpdate();   
         
     }
        
          public List<Hotel> AfficherTousLesHotels() throws SQLException
       { List<Hotel> list = new ArrayList<>();
            String req = "select * from hotel" ;
            PreparedStatement ste = con.prepareStatement(req);
            ResultSet result = ste.executeQuery();//select 
            while(result.next())
            {      
            list.add(new Hotel(result.getInt("id"), result.getString("nom"), result.getDouble("geolat") , result.getDouble("geolong"), result.getInt("nbEtoiles"), result.getString("tripAdvisorLink"), result.getString("image") , result.getString("city")));
            }
        return list ;
       }
          
             public  void supprimerHotel(Hotel p) throws SQLException
     {    
        
            String req = "delete from hotel  where id=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1,p.getId()); 
            ste.executeUpdate(); 
     }
        
        
    
}
