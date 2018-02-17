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
import java.sql.Statement;
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
            ste.setString(5, P.getNation());
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
    
public static void removePlayer(int id) {
		Connection con = DataSource.getInstance().getCon();
		String query = "DELETE FROM `Player` WHERE id=?";
		try {
			PreparedStatement ste = con.prepareStatement(query);

			ste.setInt(1, id);

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
            ste.setString(5, P.getNation());
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
            ste.setInt(18, P.getId());
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
}

 public static List<Player> findAllPlayers()
 {
     List<Player> listPlayers = new ArrayList<Player>() ;
     Connection con = DataSource.getInstance().getCon();
     String query = "SELECT * from `Player` ";
    
            try {
                  Statement  ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
                        
			while (set.next()) {
                             //listTeam.add(new Team(set.getString("name"), set.getString("coach"), set.getString("president"), set.getString("area"), set.getInt("gamesPlayed"), set.getInt("goalScored"), set.getInt("goalAgainst"), set.getInt("participations"), set.getDate("fifaDate"), set.getString("wcGroup"), set.getInt("win"), set.getInt("loose"), set.getInt("draw"), set.getInt("points"), set.getInt("fifaRank"), set.getString("flagPhoto"), set.getString("logoPhoto"), set.getString("squadPhoto"), set.getString("descriptionPhoto"), set.getString("description"), set.getString("website"), set.getString("video")));
                          Player P = new Player(set.getString("name"),set.getString("lastName"),set.getInt("age"),set.getString("club"),set.getString("nation"),set.getDouble("height"),set.getDouble("weight"),set.getString("position"),set.getInt("goals"),set.getString("description"),set.getString("profilePhoto"),set.getString("blanketPhoto"),set.getString("descriptionPhoto"),set.getString("fbLink"),set.getString("twitterLink"),set.getInt("shirtNb"),set.getString("video"));
                 P.setId(set.getInt("id"));
                 //listPlayers.add(new Player(result.getString("name"),result.getString("lastName"),result.getInt("age"),result.getString("club"),result.getString("nation"),result.getDouble("height"),result.getDouble("weight"),result.getString("position"),result.getInt("goals"),result.getString("description"),result.getString("profilePhoto"),result.getString("blanketPhoto"),result.getString("descriptionPhoto"),result.getString("fbLink"),result.getString("twitterLink"),result.getInt("shirtNb"),result.getString("video")));
                 listPlayers.add(P) ;
				
			}
                        return listPlayers;
		} catch (SQLException ex) {
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
     
 }

 
 public static Player findById(int id){
Connection con = DataSource.getInstance().getCon();
     String query = "SELECT * from `Player` where id = ? ";
      try {
            PreparedStatement ste = con.prepareStatement(query);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while(result.next())
            {

           return new  Player(result.getString("name"),result.getString("lastName"),result.getInt("age"),result.getString("club"),result.getString("nation"),result.getDouble("height"),result.getDouble("weight"),result.getString("position"),result.getInt("goals"),result.getString("description"),result.getString("profilePhoto"),result.getString("blanketPhoto"),result.getString("descriptionPhoto"),result.getString("fbLink"),result.getString("twitterLink"),result.getInt("shirtNb"),result.getString("video"));
            
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            
        }
      return null ;
  }
 public static List<Player> findPlayersByNation(int teamid) {
		Connection con = DataSource.getInstance().getCon();
		List result = new ArrayList<Player>();
		String query = "select * from player where nation = ? ";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			while (set.next()) {
				Player p = new Player(set.getString("Name"),set.getString("LastName"),set.getString("position"));
				result.add(p);
				System.out.println(p);
			}
			return result;
			
		} catch (SQLException ex) {
			System.out.println(result);
			Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
 
 
 
 
  public static List<Integer> GeIdlistPlayer(){
		
                List<Integer> list = new ArrayList<>();
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT id from `Player`";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ResultSet set = ste.executeQuery();
			while (set.next()) {
				list.add(set.getInt("id"));
			}
			return list;
		} catch (SQLException ex) {
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
	}
}
  