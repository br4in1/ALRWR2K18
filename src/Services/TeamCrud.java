/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;


import Entities.Team ; 
import Views.main;
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
 * @author Moez
 */


public class TeamCrud  {
    
public static void addTeam(Team t ){
        Connection con = DataSource.getInstance().getCon();
        String query = "INSERT INTO `Team`(`name`, `Coach`, `President`, `Area`, `Participations`, `FifaDate`, `WcGroup`,`FifaRank` ,`FlagPhoto`, `LogoPhoto`, `SquadPhoto`, `DescriptionPhoto`, `Description`, `Website`, `Video`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = con.prepareStatement(query);
            
            ste.setString(1, t.getName());
            ste.setString(2, t.getCoach());
            ste.setString(3, t.getPresident());
            ste.setString(4, t.getArea());
            ste.setInt(5, t.getParticipations());
            ste.setDate(6, t.getFifaDate());
            ste.setString(7, t.getWcGroup());
            ste.setInt(8, t.getFifaRank());
            ste.setString(9, t.getFlagPhoto());
            ste.setString(10, t.getLogoPhoto());
            ste.setString(11, t.getSquadPhoto());
            ste.setString(12, t.getDescriptionPhoto());
            ste.setString(13, t.getDescription());
            ste.setString(14, t.getWebsite());
            ste.setString(15, t.getVideo());
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
public static void deleteTeam(Team t)
    {
        Connection con = DataSource.getInstance().getCon();
        String query = "DELETE FROM `Team` WHERE id=?";
        try {
            PreparedStatement ste = con.prepareStatement(query);
            
            ste.setInt(1, t.getId());
           
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  


public static void updateTeam(Team t) {
    Connection con = DataSource.getInstance().getCon();
        String query = "UPDATE `Team` SET `name`=?,`Coach`=?,`President`=?,`Area`=?,`GamesPlayed`=?,`GoalScored`=?,`GoalAgainst`=?,`Participations`=?,`FifaDate`=?,`WcGroup`=?,`Win`=?,`Loose`=?,`Draw`=?,`Points`=?,`FifaRank`=?,`FlagPhoto`=?,`LogoPhoto`=?,`SquadPhoto`=?,`DescriptionPhoto`=?,`Description`=?,`Website`=?,`Video`=? WHERE id=?";
        
        
        try {
            PreparedStatement ste = con.prepareStatement(query);
            
            ste.setString(1, t.getName());
            ste.setString(2, t.getCoach());
            ste.setString(3, t.getPresident());
            ste.setString(4, t.getArea());
            ste.setInt(5,t.getGamesPlayed());
            ste.setInt(6, t.getGoalScored());
            ste.setInt(7, t.getGoalAgainst());
            ste.setInt(8, t.getParticipations());
            ste.setDate(9, t.getFifaDate());
            ste.setString(10, t.getWcGroup());
            ste.setInt(11, t.getWin());
            ste.setInt(12, t.getLoose());
            ste.setInt(13,t.getDraw());
            ste.setInt(14, t.getPoints());
            ste.setInt(15, t.getFifaRank());
            ste.setString(16, t.getFlagPhoto());
            ste.setString(17, t.getLogoPhoto());
            ste.setString(18, t.getSquadPhoto());
            ste.setString(19, t.getDescriptionPhoto());
            ste.setString(20, t.getDescription());
            ste.setString(21, t.getWebsite());
            ste.setString(22, t.getVideo());
            ste.setInt(23, t.getId());
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
}


 public static List<Team> findAllTeam()
 {
     List<Team> listTeam = new ArrayList<Team>() ;
     listTeam =null ; 
     Connection con = DataSource.getInstance().getCon();
     String query = "SELECT * `Team` ";
     try {
            PreparedStatement ste = con.prepareStatement(query);
            ResultSet result = ste.executeQuery();
            while(result.next())
            {
                 listTeam.add(new Team(result.getInt("id"),result.getString("name"),result.getString("coach"),result.getString("president"),result.getString("area"),result.getInt("gamePlayed"),result.getInt("goalScored"),result.getInt("goalAgainst"),result.getInt("participations"),result.getDate("fifaDate"),result.getString("wcGroup"),result.getInt("win"),result.getInt("loose"),result.getInt("draw"),result.getInt("fifaRank"),result.getString("flagPhoto"),result.getString("logoPhoto"),result.getString("squadPhoto"),result.getString("descriptionPhoto"),result.getString("description"),result.getString("website"),result.getString("video")));
                return listTeam ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            
        }
     return null ; 
     
 }
 public static Team findById(int id){
Connection con = DataSource.getInstance().getCon();
     String query = "SELECT * `Team` id = ? ";
      try {
            PreparedStatement ste = con.prepareStatement(query);
            ResultSet result = ste.executeQuery();
            while(result.next())
            {
             return new Team(result.getInt("id"),result.getString("name"),result.getString("coach"),result.getString("president"),result.getString("area"),result.getInt("gamePlayed"),result.getInt("goalScored"),result.getInt("goalAgainst"),result.getInt("participations"),result.getDate("fifaDate"),result.getString("wcGroup"),result.getInt("win"),result.getInt("loose"),result.getInt("draw"),result.getInt("fifaRank"),result.getString("flagPhoto"),result.getString("logoPhoto"),result.getString("squadPhoto"),result.getString("descriptionPhoto"),result.getString("description"),result.getString("website"),result.getString("video"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            
        }
      return null ;
  }
     
}

