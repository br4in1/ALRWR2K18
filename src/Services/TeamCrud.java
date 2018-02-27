 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Team;
import Views.main;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moez
 */
public class TeamCrud {

	public static void addTeam(Team t) {
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

	public static void RemoveTeam(int id) {
		Connection con = DataSource.getInstance().getCon();
		String query = "DELETE FROM `Team` WHERE id=?";
		try {
			PreparedStatement ste = con.prepareStatement(query);

			ste.setInt(1, id);

			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public static void updateTeam(Team t) {
		Connection con = DataSource.getInstance().getCon();
		String query = "UPDATE `Team` SET `name`=?,`Coach`=?,`President`=?,`Area`=?,`GamesPlayed`=?,`GoalScored`=?,`GoalAgainst`=?,`Participations`=?,`FifaDate`=?,`WcGroup`=?,`Win`=?,`Loose`=?,`Draw`=?,`Points`=?,`FifaRank`=?,`FlagPhoto`=?,`LogoPhoto`=?,`SquadPhoto`=?,`DescriptionPhoto`=?,`Description`=?,`Website`=?,`Video`=? WHERE id=?";
// TeamCrud.updateTeam(new Team(name.getText(),coach.getText(),president.getText(),area.getText(),Integer.parseInt(gamesPlayed.getText()),Integer.parseInt(goalScored.getText()),Integer.parseInt(goalAgainst.getText()),Integer.parseInt(participation.getText()),Date.valueOf(date.getValue()),wcgroupe.getText(),Integer.parseInt(win.getText()),Integer.parseInt(loose.getText()),Integer.parseInt(draw.getText()),Integer.parseInt(points.getText()),Integer.parseInt(fifarank.getText()),(String) uploadResult.get("url"),(String) uploadResult.get("url"),(String) uploadResult.get("url"),(String) uploadResult.get("url"),description.getText(),website.getText(),video.getText()));
    
		try {
			PreparedStatement ste = con.prepareStatement(query);

			ste.setString(1, t.getName());
			ste.setString(2, t.getCoach());
			ste.setString(3, t.getPresident());
			ste.setString(4, t.getArea());
			ste.setInt(5, t.getGamesPlayed());
			ste.setInt(6, t.getGoalScored());
			ste.setInt(7, t.getGoalAgainst());
			ste.setInt(8, t.getParticipations());
			ste.setDate(9, t.getFifaDate());
			ste.setString(10, t.getWcGroup());
			ste.setInt(11, t.getWin());
			ste.setInt(12, t.getLoose());
			ste.setInt(13, t.getDraw());
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

	public static List<Team> findAllTeam() {
		List<Team> listTeam = new ArrayList<Team>();
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT * from `Team` ";
		try {
                    Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
                        
			while (set.next()) {
                             //listTeam.add(new Team(set.getString("name"), set.getString("coach"), set.getString("president"), set.getString("area"), set.getInt("gamesPlayed"), set.getInt("goalScored"), set.getInt("goalAgainst"), set.getInt("participations"), set.getDate("fifaDate"), set.getString("wcGroup"), set.getInt("win"), set.getInt("loose"), set.getInt("draw"), set.getInt("points"), set.getInt("fifaRank"), set.getString("flagPhoto"), set.getString("logoPhoto"), set.getString("squadPhoto"), set.getString("descriptionPhoto"), set.getString("description"), set.getString("website"), set.getString("video")));
                            
                               Team T = new Team(set.getString("name"), set.getString("coach"), set.getString("president"), set.getString("area"), set.getInt("gamesPlayed"), set.getInt("goalScored"), set.getInt("goalAgainst"), set.getInt("participations"), set.getDate("fifaDate"), set.getString("wcGroup"), set.getInt("win"), set.getInt("loose"), set.getInt("draw"), set.getInt("points"), set.getInt("fifaRank"), set.getString("flagPhoto"), set.getString("logoPhoto"), set.getString("squadPhoto"), set.getString("descriptionPhoto"), set.getString("description"), set.getString("website"), set.getString("video"));
                            T.setId(set.getInt("id")); 
//String name, String coach, String president, String area, int gamesPlayed, int goalScored, int goalAgainst, int participations, Date fifaDate, String wcGroup, int win, int loose, int draw, int points, int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video
                            listTeam.add(T);
				
			}
                        return listTeam;
		} catch (SQLException ex) {
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;

	}
	public static List<Team> findAllTeamGroupByArea() {
		List<Team> listTeam = new ArrayList<Team>();
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT * from `Team` order by area ";
		try {
                    Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
                        
			while (set.next()) {
                             //listTeam.add(new Team(set.getString("name"), set.getString("coach"), set.getString("president"), set.getString("area"), set.getInt("gamesPlayed"), set.getInt("goalScored"), set.getInt("goalAgainst"), set.getInt("participations"), set.getDate("fifaDate"), set.getString("wcGroup"), set.getInt("win"), set.getInt("loose"), set.getInt("draw"), set.getInt("points"), set.getInt("fifaRank"), set.getString("flagPhoto"), set.getString("logoPhoto"), set.getString("squadPhoto"), set.getString("descriptionPhoto"), set.getString("description"), set.getString("website"), set.getString("video")));
                            
                               Team T = new Team(set.getString("name"), set.getString("coach"), set.getString("president"), set.getString("area"), set.getInt("gamesPlayed"), set.getInt("goalScored"), set.getInt("goalAgainst"), set.getInt("participations"), set.getDate("fifaDate"), set.getString("wcGroup"), set.getInt("win"), set.getInt("loose"), set.getInt("draw"), set.getInt("points"), set.getInt("fifaRank"), set.getString("flagPhoto"), set.getString("logoPhoto"), set.getString("squadPhoto"), set.getString("descriptionPhoto"), set.getString("description"), set.getString("website"), set.getString("video"));
                            T.setId(set.getInt("id")); 
//String name, String coach, String president, String area, int gamesPlayed, int goalScored, int goalAgainst, int participations, Date fifaDate, String wcGroup, int win, int loose, int draw, int points, int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video
                            listTeam.add(T);
				
			}
                        return listTeam;
		} catch (SQLException ex) {
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;

	}
	public static List<Team> findAllTeamSortedByName() {
		List<Team> listTeam = new ArrayList<Team>();
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT * from `Team` order by name ";
		try {
                    Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
                        
			while (set.next()) {
                             //listTeam.add(new Team(set.getString("name"), set.getString("coach"), set.getString("president"), set.getString("area"), set.getInt("gamesPlayed"), set.getInt("goalScored"), set.getInt("goalAgainst"), set.getInt("participations"), set.getDate("fifaDate"), set.getString("wcGroup"), set.getInt("win"), set.getInt("loose"), set.getInt("draw"), set.getInt("points"), set.getInt("fifaRank"), set.getString("flagPhoto"), set.getString("logoPhoto"), set.getString("squadPhoto"), set.getString("descriptionPhoto"), set.getString("description"), set.getString("website"), set.getString("video")));
                            
                               Team T = new Team(set.getString("name"), set.getString("coach"), set.getString("president"), set.getString("area"), set.getInt("gamesPlayed"), set.getInt("goalScored"), set.getInt("goalAgainst"), set.getInt("participations"), set.getDate("fifaDate"), set.getString("wcGroup"), set.getInt("win"), set.getInt("loose"), set.getInt("draw"), set.getInt("points"), set.getInt("fifaRank"), set.getString("flagPhoto"), set.getString("logoPhoto"), set.getString("squadPhoto"), set.getString("descriptionPhoto"), set.getString("description"), set.getString("website"), set.getString("video"));
                            T.setId(set.getInt("id")); 
//String name, String coach, String president, String area, int gamesPlayed, int goalScored, int goalAgainst, int participations, Date fifaDate, String wcGroup, int win, int loose, int draw, int points, int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video
                            listTeam.add(T);
				
			}
                        return listTeam;
		} catch (SQLException ex) {
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;

	}
	

	public static Team findById(int id) {
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT * from `Team` where id = ? ";
		try {
			PreparedStatement ste = con.prepareStatement(query);
                        ste.setInt(1, id);
			ResultSet result = ste.executeQuery();
                        
			while (result.next()) {
				return new Team(result.getString("name"), result.getString("coach"), result.getString("president"), result.getString("area"), result.getInt("gamesPlayed"), result.getInt("goalScored"), result.getInt("goalAgainst"), result.getInt("participations"), result.getDate("fifaDate"), result.getString("wcGroup"), result.getInt("win"), result.getInt("loose"), result.getInt("draw"), result.getInt("points"), result.getInt("fifaRank"), result.getString("flagPhoto"), result.getString("logoPhoto"), result.getString("squadPhoto"), result.getString("descriptionPhoto"), result.getString("description"), result.getString("website"), result.getString("video"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
	}
       
        public static Boolean findTeamByName(String name) {
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT * from `Team` where name = ? ";
		try {
			PreparedStatement ste = con.prepareStatement(query);
                        ste.setString(1, name);
			ResultSet result = ste.executeQuery();
			while (result.next()) {
				//return new Team(result.getString("name"), result.getString("coach"), result.getString("president"), result.getString("area"), result.getInt("gamesPlayed"), result.getInt("goalScored"), result.getInt("goalAgainst"), result.getInt("participations"), result.getDate("fifaDate"), result.getString("wcGroup"), result.getInt("win"), result.getInt("loose"), result.getInt("draw"), result.getInt("points"), result.getInt("fifaRank"), result.getString("flagPhoto"), result.getString("logoPhoto"), result.getString("squadPhoto"), result.getString("descriptionPhoto"), result.getString("description"), result.getString("website"), result.getString("video"));
                                return true ;
                        }
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}
        public static Boolean findTeamByNameId(String name,int id) {
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT * from `Team` where name = ? and id != ? ";
		try {
			PreparedStatement ste = con.prepareStatement(query);
                        ste.setString(1, name);
                        ste.setInt(2, id);
			ResultSet result = ste.executeQuery();
			while (result.next()) {
				//return new Team(result.getString("name"), result.getString("coach"), result.getString("president"), result.getString("area"), result.getInt("gamesPlayed"), result.getInt("goalScored"), result.getInt("goalAgainst"), result.getInt("participations"), result.getDate("fifaDate"), result.getString("wcGroup"), result.getInt("win"), result.getInt("loose"), result.getInt("draw"), result.getInt("points"), result.getInt("fifaRank"), result.getString("flagPhoto"), result.getString("logoPhoto"), result.getString("squadPhoto"), result.getString("descriptionPhoto"), result.getString("description"), result.getString("website"), result.getString("video"));
                                return true ;
                        }
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}
	
	public static HashMap<String,Integer> GetNameIdMap(){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT id,name from team";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ResultSet set = ste.executeQuery();
			while (set.next()) {
				map.put(set.getString("name"), set.getInt("id"));
			}
			return map;
		} catch (SQLException ex) {
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
	}
	public static HashMap<String,Integer> GetDateIdMap(){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT id,date from team";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ResultSet set = ste.executeQuery();
			while (set.next()) {
			//	map.put(set.date, set.getInt("id"));
			}
			return map;
		} catch (SQLException ex) {
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
	}
        public static List<Integer> GeIdlist(){
		
                List<Integer> list = new ArrayList<>();
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT id from team";
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
         public static List<String> GetNamelist(){
		
                List<String> list = new ArrayList<>();
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT name from team";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ResultSet set = ste.executeQuery();
			while (set.next()) {
				list.add(set.getString("name"));
                               // System.out.println(list);
			}
			return list;
		} catch (SQLException ex) {
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
	}
		 
		 public static List<Team> findTeamByNameList(String name) {
		Connection con = DataSource.getInstance().getCon();
		List<Team> list = new ArrayList<>() ;
		String query = "SELECT * from `Team` where name = ? ";
		try {
			PreparedStatement ste = con.prepareStatement(query);
                        ste.setString(1, name);
			ResultSet result = ste.executeQuery();
			while (result.next()) {
				//return new Team(result.getString("name"), result.getString("coach"), result.getString("president"), result.getString("area"), result.getInt("gamesPlayed"), result.getInt("goalScored"), result.getInt("goalAgainst"), result.getInt("participations"), result.getDate("fifaDate"), result.getString("wcGroup"), result.getInt("win"), result.getInt("loose"), result.getInt("draw"), result.getInt("points"), result.getInt("fifaRank"), result.getString("flagPhoto"), result.getString("logoPhoto"), result.getString("squadPhoto"), result.getString("descriptionPhoto"), result.getString("description"), result.getString("website"), result.getString("video"));
                  	Team t = new  Team(result.getString("name"), result.getString("coach"), result.getString("president"), result.getString("area"), result.getInt("gamesPlayed"), result.getInt("goalScored"), result.getInt("goalAgainst"), result.getInt("participations"), result.getDate("fifaDate"), result.getString("wcGroup"), result.getInt("win"), result.getInt("loose"), result.getInt("draw"), result.getInt("points"), result.getInt("fifaRank"), result.getString("flagPhoto"), result.getString("logoPhoto"), result.getString("squadPhoto"), result.getString("descriptionPhoto"), result.getString("description"), result.getString("website"), result.getString("video"));
			              list.add(t);
                        }
			return list ;
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null ;
	}
		 public static Team findByName(String name) {
		Connection con = DataSource.getInstance().getCon();
		List<Team> list = new ArrayList<>() ;
		String query = "SELECT * from `Team` where name = ? ";
		try {
			PreparedStatement ste = con.prepareStatement(query);
                        ste.setString(1, name);
			ResultSet result = ste.executeQuery();
			while (result.next()) {
				//return new Team(result.getString("name"), result.getString("coach"), result.getString("president"), result.getString("area"), result.getInt("gamesPlayed"), result.getInt("goalScored"), result.getInt("goalAgainst"), result.getInt("participations"), result.getDate("fifaDate"), result.getString("wcGroup"), result.getInt("win"), result.getInt("loose"), result.getInt("draw"), result.getInt("points"), result.getInt("fifaRank"), result.getString("flagPhoto"), result.getString("logoPhoto"), result.getString("squadPhoto"), result.getString("descriptionPhoto"), result.getString("description"), result.getString("website"), result.getString("video"));
			Team t = new  Team(result.getString("name"), result.getString("coach"), result.getString("president"), result.getString("area"), result.getInt("gamesPlayed"), result.getInt("goalScored"), result.getInt("goalAgainst"), result.getInt("participations"), result.getDate("fifaDate"), result.getString("wcGroup"), result.getInt("win"), result.getInt("loose"), result.getInt("draw"), result.getInt("points"), result.getInt("fifaRank"), result.getString("flagPhoto"), result.getString("logoPhoto"), result.getString("squadPhoto"), result.getString("descriptionPhoto"), result.getString("description"), result.getString("website"), result.getString("video"));       
			
					return t;
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null ;
	}

	
}
