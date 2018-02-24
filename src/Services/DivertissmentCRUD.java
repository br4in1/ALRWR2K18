/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Divertissement;
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
public class DivertissmentCRUD {
    
   
  
    
        public void ajouterdivertissement(Divertissement p) throws SQLException
     {    
		 Connection con = Utils.DataSource.getInstance().getCon();
               String sql = "INSERT INTO `divertissement`(`nom`, `geolat`, `geolong`, `heureOuverture` , `heureFermeture` , `Link`, `image`, `city`) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setString(1,p.getNom());
        ste.setDouble(2,p.getGeolat());
        ste.setDouble(3,p.getGeolong());
        ste.setTimestamp(4,p.getHeureOuverture());
		ste.setTimestamp(5,p.getHeureFermeture());
        ste.setString(6,p.getLink());
        ste.setString(7,p.getImage());
        ste.setString(8,p.getCity());
        
        ste.executeUpdate();   
         
     }
        
          public List<Divertissement> AfficherTousdivertissement() throws SQLException
       {
		    Connection con = Utils.DataSource.getInstance().getCon();
		   List<Divertissement> list = new ArrayList<>();
            String req = "select * from divertissement where nom not like ('test')" ;
            PreparedStatement ste = con.prepareStatement(req);
            ResultSet result = ste.executeQuery();//select 
            while(result.next())
            {      
            list.add(new Divertissement(result.getInt("id"), result.getString("nom"), result.getDouble("geolat") , result.getDouble("geolong"), result.getTimestamp("heureOuverture"), result.getTimestamp("heureFermeture"), result.getString("Link"), result.getString("image") , result.getString("city")));
          }
        return list ;
       }
          
              public List<Divertissement> afficherseuelementdivertissement(String s ) throws SQLException
       {  Connection con = Utils.DataSource.getInstance().getCon();
		   List<Divertissement> list = new ArrayList<>();
            String req = "select * from divertissement where nom=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setString(1,s); 
            ResultSet result = ste.executeQuery();//select 
            
            while(result.next())
            {      
            list.add(new Divertissement(result.getInt("id"), result.getString("nom"), result.getDouble("geolat") , result.getDouble("geolong"), result.getTimestamp("heureOuverture"), result.getTimestamp("heureFermeture"), result.getString("Link"), result.getString("image") , result.getString("city")));
           }
        return list ;
       }
			        public List<Divertissement> afficherseuelementdivertissement(int s ) throws SQLException
       {  Connection con = Utils.DataSource.getInstance().getCon();
		   List<Divertissement> list = new ArrayList<>();
            String req = "select * from divertissement where id=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1,s); 
            ResultSet result = ste.executeQuery();//select 
            
            while(result.next())
            {      
            list.add(new Divertissement(result.getInt("id"), result.getString("nom"), result.getDouble("geolat") , result.getDouble("geolong"), result.getTimestamp("heureOuverture"), result.getTimestamp("heureFermeture"), result.getString("Link"), result.getString("image") , result.getString("city")));
           }
        return list ;
       }
          
          
          
      
             public  void delete(int p) throws SQLException
     {     Connection con = Utils.DataSource.getInstance().getCon();
        
            String req = "delete from divertissement where id=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1,p); 
            ste.executeUpdate(); 
     }
        public void updateTest(Divertissement p) throws SQLException
        {	 Connection con = Utils.DataSource.getInstance().getCon();
            String req = "update divertissement set geolat=(?) , geolong=(?) where nom=(?)" ;
            PreparedStatement ste = con.prepareStatement(req);
            ste.setDouble(1,p.getGeolat()); 
            ste.setDouble(2,p.getGeolong()); 
            ste.setString(3,p.getNom()); 
          
            ste.executeUpdate(); 
     
        }
		
		 public void updateA(Divertissement p) throws SQLException
        {	 Connection con = Utils.DataSource.getInstance().getCon();
            String req = "UPDATE divertissement SET nom=(?),geolat=(?),geolong=(?),heureOuverture=(?),heureFermeture=(?),Link=(?),image=(?),city=(?) WHERE id=(?) ";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setString(1,p.getNom()); 
			ste.setDouble(2,p.getGeolat()); 
            ste.setDouble(3,p.getGeolong()); 
            ste.setTimestamp(4, p.getHeureOuverture());
			ste.setTimestamp(5, p.getHeureFermeture());
			ste.setString(6, p.getLink());
			ste.setString(7,p.getImage());
			ste.setString(8,p.getCity());
			ste.setInt(9, p.getId());
			
          
            ste.executeUpdate(); 
     
        }
        
    
}
