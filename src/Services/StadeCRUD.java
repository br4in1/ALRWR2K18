/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Stade;
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
public class StadeCRUD {
	
	 public void ajouterStade(Stade p) throws SQLException
     {    
		 Connection con = Utils.DataSource.getInstance().getCon();
               String sql = "INSERT INTO `stadium`(`name`, `geolat`, `geolong`, `capacity`, `image`, `city`) VALUES (?,?,?,?,?,?)";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setString(1,p.getNom());
        ste.setDouble(2,p.getGeolat());
        ste.setDouble(3,p.getGeolong());
        ste.setInt(4,p.getCapacity());
        ste.setString(5,p.getImage());
        ste.setString(6,p.getCity());
        
        ste.executeUpdate();   
         
     }
        
          public List<Stade> AfficherTousLesStades() throws SQLException
       {
		    Connection con = Utils.DataSource.getInstance().getCon();
		   List<Stade> list = new ArrayList<>();
            String req = "select * from stadium" ;
            PreparedStatement ste = con.prepareStatement(req);
            ResultSet result = ste.executeQuery();//select 
            while(result.next())
            {      
            list.add(new Stade(result.getInt("id"), result.getString("name"), result.getDouble("geolat") , result.getDouble("geolong"), result.getInt("capacity"),result.getString("image") , result.getString("city")));
            }
        return list ;
       }
          
              public List<Stade> AfficherTousLesStade(String s ) throws SQLException
       {  Connection con = Utils.DataSource.getInstance().getCon();
		   List<Stade> list = new ArrayList<>();
            String req = "select * from stadium where name=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setString(1,s); 
            ResultSet result = ste.executeQuery();//select 
            
            while(result.next())
            {      
            list.add(new Stade(result.getInt("id"), result.getString("name"), result.getDouble("geolat") , result.getDouble("geolong"), result.getInt("capacity"),result.getString("image") , result.getString("city")));}
        return list ;
       }
          
          
        
             public  void SupprimerStade(int id) throws SQLException
     {     Connection con = Utils.DataSource.getInstance().getCon();
        
            String req = "delete from stadium where id=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1,id); 
            ste.executeUpdate(); 
     }
        public void updateTest(Stade p) throws SQLException
        {	 Connection con = Utils.DataSource.getInstance().getCon();
            String req = "update stadium set geolat=(?) , geolong=(?) where name=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setDouble(1,p.getGeolat()); 
            ste.setDouble(2,p.getGeolong()); 
            ste.setString(3,p.getNom()); 
          
            ste.executeUpdate(); 
     
        }
		
		 public void update(Stade p) throws SQLException
        {	 Connection con = Utils.DataSource.getInstance().getCon();
            String req = "UPDATE stadium SET name=(?),geolat=(?),geolong=(?),capacity=(?),image=(?),city=(?) WHERE id=(?) ";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setString(1,p.getNom()); 
			ste.setDouble(2,p.getGeolat()); 
            ste.setDouble(3,p.getGeolong()); 
            ste.setInt(4, p.getCapacity());
			ste.setString(5,p.getImage());
			ste.setString(6,p.getCity());
			ste.setInt(7, p.getId());
			ste.executeUpdate(); 
		}
	
}
