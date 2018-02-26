/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Sof
 */
public class HotelCRUD {
    
   
  
    
        public void ajouterHotel(Hotel p) throws SQLException
     {    
		 Connection con = Utils.DataSource.getInstance().getCon();
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
       {
		    Connection con = Utils.DataSource.getInstance().getCon();
		   List<Hotel> list = new ArrayList<>();
            String req = "select * from hotel where nom not like ('test')" ;
            PreparedStatement ste = con.prepareStatement(req);
            ResultSet result = ste.executeQuery();//select 
            while(result.next())
            {      
            list.add(new Hotel(result.getInt("id"), result.getString("nom"), result.getDouble("geolat") , result.getDouble("geolong"), result.getInt("nbEtoiles"), result.getString("tripAdvisorLink"), result.getString("image") , result.getString("city")));
            }
        return list ;
       }
		     public List<Hotel> AfficherTousLesHotels(int i) throws SQLException
       {
		    Connection con = Utils.DataSource.getInstance().getCon();
		   List<Hotel> list = new ArrayList<>();
            String req = "select * from hotel where id=(?) " ;
            PreparedStatement ste = con.prepareStatement(req);
			ste.setInt(1,i); 
            ResultSet result = ste.executeQuery();//select 
            while(result.next())
            {      
            list.add(new Hotel(result.getInt("id"), result.getString("nom"), result.getDouble("geolat") , result.getDouble("geolong"), result.getInt("nbEtoiles"), result.getString("tripAdvisorLink"), result.getString("image") , result.getString("city")));
            }
        return list ;
       }
          
              public List<Hotel> AfficherTousLesHotels(String s ) throws SQLException
       {  Connection con = Utils.DataSource.getInstance().getCon();
		   List<Hotel> list = new ArrayList<>();
            String req = "select * from hotel where nom=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setString(1,s); 
            ResultSet result = ste.executeQuery();//select 
            
            while(result.next())
            {      
            list.add(new Hotel(result.getInt("id"), result.getString("nom"), result.getDouble("geolat") , result.getDouble("geolong"), result.getInt("nbEtoiles"), result.getString("tripAdvisorLink"), result.getString("image") , result.getString("city")));
            }
        return list ;
       }
          
          
          
          
          public Hotel AfficherHotelSeulement(Hotel p ) throws SQLException
          {	 Connection con = Utils.DataSource.getInstance().getCon();
              String req = "select * from hotel where nom=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setString(1,p.getNom()); 
            ResultSet result = ste.executeQuery();//select 
              
            return (new Hotel(result.getInt("id"), result.getString("nom"), result.getDouble("geolat") , result.getDouble("geolong"), result.getInt("nbEtoiles"), result.getString("tripAdvisorLink"), result.getString("image") , result.getString("city")));
          
          }
		  
		   public Map <Integer , Integer > StaticHotel() throws SQLException
          {	 Connection con = Utils.DataSource.getInstance().getCon();
		   Map <Integer , Integer > list = new HashMap<>() ;
            String req = "SELECT count(*) as total , nbEtoiles as nb FROM hotel group by nbEtoiles" ;
            PreparedStatement ste = con.prepareStatement(req);
            
            ResultSet result = ste.executeQuery();//select 
            
            while(result.next())
            {      
            list.put(result.getInt("nb"), result.getInt("total"));
            }
        return list ;
          }
		  
		  
             public  void supprimerHotel(int p) throws SQLException
     {     Connection con = Utils.DataSource.getInstance().getCon();
        
            String req = "delete from hotel where id=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1,p); 
            ste.executeUpdate(); 
     }
        public void updateTest(Hotel p) throws SQLException
        {	 Connection con = Utils.DataSource.getInstance().getCon();
            String req = "update hotel set geolat=(?) , geolong=(?) where nom=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setDouble(1,p.getGeolat()); 
            ste.setDouble(2,p.getGeolong()); 
            ste.setString(3,p.getNom()); 
          
            ste.executeUpdate(); 
     
        }
		
		 public void update(Hotel p) throws SQLException
        {	 Connection con = Utils.DataSource.getInstance().getCon();
            String req = "UPDATE hotel SET nom=(?),geolat=(?),geolong=(?),nbEtoiles=(?),tripAdvisorLink=(?),image=(?),city=(?) WHERE id=(?) ";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setString(1,p.getNom()); 
			ste.setDouble(2,p.getGeolat()); 
            ste.setDouble(3,p.getGeolong()); 
            ste.setInt(4, p.getNbEtoiles());
			ste.setString(5, p.getLink());
			ste.setString(6,p.getImage());
			ste.setString(7,p.getCity());
			ste.setInt(8, p.getId());
			
          
            ste.executeUpdate(); 
     
        }
        
    
}
