/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Player;
import Utils.DataSource;
import Views.main;
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
 * @author Moez
 */
public class PlayerCrud {
    public static void addPlayer(Player P ){
        Connection con = DataSource.getInstance().getCon();
        String query = "INSERT INTO `Player`(`Name`, `LastName`, `Age`, `Club`, `Nation`, `Height`, `Weight`, `Position`, `Goals`, `Description`, `ProfilePhoto`, `BlanketPhoto`, `DescriptionPhoto`, `FbLink`, `TwitterLink`, `ShirtNb`, `Video`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       
       try {
            PreparedStatement ste = con.prepareStatement(query);
            
            ste.setString(1, P.getName());
            ste.setString(2, P.getLastName());
            ste.setInt(3, P.getAge());
            ste.setString(4, P.getClub());
            ste.setInt(5, P.getNation());
            ste.setDouble(6, P.getHeight());
            ste.setDouble(7, P.getWeight());
            ste.setString(8, P.getPosition());
            ste.setInt(9, P.getGoals());
            ste.setString(10, P.getDescription());
            ste.setString(11, P.getProfilePhoto());
            ste.setString(12, P.getBlanketPhoto());
            ste.setString(13, P.getDescriptionPhoto());
            ste.setString(14, P.getFbLink());
            ste.setString(15, P.getTwitterLink());
            ste.setInt(16, P.getShirtNb());
            ste.setString(17, P.getVideo());
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
  
public static void deletePlayer(Player P)
    {
        Connection con = DataSource.getInstance().getCon();
        String query = "DELETE FROM `Player` WHERE id=?";
        try {
            PreparedStatement ste = con.prepareStatement(query);
            
            ste.setInt(1, P.getId());
           
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  


public static void updateTeam(Player P ) {
    Connection con = DataSource.getInstance().getCon();
        String query = "UPDATE `Player` SET `Name`=?,`LastName`=?,`Age`=?,`Club`=?,`Nation`=?,`Height`=?,`Weight`=?,`Position`=?,`Goals`=?,`Description`=?,`ProfilePhoto`=?,`BlanketPhoto`=?,`DescriptionPhoto`=?,`FbLink`=?,`TwitterLink`=?,`ShirtNb`=?,`Video`=? WHERE id=?";
        
        try {
            PreparedStatement ste = con.prepareStatement(query);
             ste.setString(1, P.getName());
            ste.setString(2, P.getLastName());
            ste.setInt(3, P.getAge());
            ste.setString(4, P.getClub());
            ste.setInt(5, P.getNation());
            ste.setDouble(6, P.getHeight());
            ste.setDouble(7, P.getWeight());
            ste.setString(8, P.getPosition());
            ste.setInt(9, P.getGoals());
            ste.setString(10, P.getDescription());
            ste.setString(11, P.getProfilePhoto());
            ste.setString(12, P.getBlanketPhoto());
            ste.setString(13, P.getDescriptionPhoto());
            ste.setString(14, P.getFbLink());
            ste.setString(15, P.getTwitterLink());
            ste.setInt(16, P.getShirtNb());
            ste.setString(17, P.getVideo());
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
}

 public static List<Player> findAllPlayers()
 {
     List<Player> listPlayers = new ArrayList<Player>() ;
     listPlayers =null ; 
     Connection con = DataSource.getInstance().getCon();
     String query = "SELECT * `Player` ";
     try {
            PreparedStatement ste = con.prepareStatement(query);
            ResultSet result = ste.executeQuery();
            while(result.next())
            {
                 listPlayers.add(new Player(result.getInt("id"),result.getString("name"),result.getString("lastName"),result.getInt("age"),result.getString("club"),result.getInt("nation"),result.getDouble("height"),result.getDouble("weight"),result.getString("position"),result.getInt("goals"),result.getString("description"),result.getString("profilePhoto"),result.getString("blanketPhoto"),result.getString("descriptionPhoto"),result.getString("fbLink"),result.getString("twitterLink"),result.getInt("shirtNb"),result.getString("video")));
                 return listPlayers ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            
        }
     return null ; 
     
 }

 
 
 public static Player findById(int id){
Connection con = DataSource.getInstance().getCon();
     String query = "SELECT * `Player` id = ? ";
      try {
            PreparedStatement ste = con.prepareStatement(query);
            ResultSet result = ste.executeQuery();
            while(result.next())
            {

           return new  Player(result.getInt("id"),result.getString("name"),result.getString("lastName"),result.getInt("age"),result.getString("club"),result.getInt("nation"),result.getDouble("height"),result.getDouble("weight"),result.getString("position"),result.getInt("goals"),result.getString("description"),result.getString("profilePhoto"),result.getString("blanketPhoto"),result.getString("descriptionPhoto"),result.getString("fbLink"),result.getString("twitterLink"),result.getInt("shirtNb"),result.getString("video"));
            
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            
        }
      return null ;
  }
}
