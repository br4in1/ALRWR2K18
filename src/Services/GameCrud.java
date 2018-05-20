/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.Game;
import Entities.Team;
import Utils.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simo
 */
public class GameCrud {

	public static void InsertGame(Game game) {
		Connection con = DataSource.getInstance().getCon();

		String query = "INSERT INTO `Game`"
				+ "(`Date`, `HomeTeam`, `AwayTeam`, `Result`, `Stadium`, `Summary`, `SummaryPhoto`, `Highlights`, `Referee`)"
				+ " VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ste = con.prepareStatement(query);

			ste.setDate(1, game.getDate());
			ste.setString(2, game.getHomeTeam());
			ste.setString(3, game.getAwayTeam());
			ste.setString(4, game.getResult());
			ste.setInt(5, Integer.parseInt(game.getStadium()));
			ste.setString(6, game.getSummary());
			ste.setString(7, game.getSummaryPhoto());
			ste.setString(8, game.getHighlights());
			ste.setString(9, game.getReferee());

			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static List<Game> findAllGames() {
		Connection con = DataSource.getInstance().getCon();
		List result = new ArrayList<Game>();
		
		String query = "SELECT * from `Game` ORDER BY Date";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			while (set.next()) {
				
				Game g = new Game(set.getDate("Date"), set.getString("HomeTeam"), set.getString("AwayTeam"), set.getString("Result"), set.getString("Stadium"), set.getString("Summary"), set.getString("SummaryPhoto"), set.getString("Highlights"), set.getString("Referee"));

				g.setId(set.getInt("id"));
				result.add(g);
			}
			return result;
		} catch (SQLException ex) {
			Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	public static List<Game> searchGames(String like) {
		Connection con = DataSource.getInstance().getCon();
		List result = new ArrayList<Game>();
		//String query = "select g.*,t1.name 'nomaway',t2.name 'nomhome',s.name 'nomstade' from Game g join Team t1 on t1.id = g.HomeTeam join Team t2 on t2.id = g.AwayTeam join Stadium s on s.id = g.Stadium where (t1.name like '%"+like+"%' or t2.name like '%"+like+"%') ORDER BY Date ";
		String query = "SELECT g.* from `Game` g where (g.HomeTeam like '%\"+like+\"%' or g.AwayTeam like '%\"+like+\"%') ORDER BY Date";
		
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			while (set.next()) {
				//Game g = new Game(set.getDate("Date"), set.getString("nomhome"), set.getString("nomaway"), set.getString("Result"), set.getString("nomstade"), set.getString("Summary"), set.getString("SummaryPhoto"), set.getString("Highlights"), set.getString("Referee"));
				Game g = new Game(set.getDate("Date"), set.getString("HomeTeam"), set.getString("AwayTeam"), set.getString("Result"), set.getString("Stadium"), set.getString("Summary"), set.getString("SummaryPhoto"), set.getString("Highlights"), set.getString("Referee"));

				g.setId(set.getInt("id"));
				result.add(g);
			}
			return result;
		} catch (SQLException ex) {
			Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
//public static Da findDatesByTeams(String team1, String team2) {
	public static List<Date> findDatesByTeams(String team1, String team2) {
		/*Connection con = DataSource.getInstance().getCon();
		
		List result = new ArrayList<Date>();
		String strSQLQuery = String.format("select date from game where HomeTeam= %s and awayTeam= %s", team1, team2);
		try {
		Statement ste = con.createStatement();
		ResultSet set = ste.executeQuery(strSQLQuery);
		System.out.println("cccccc " + set);
		while (set.next()) {
		System.out.println(set.getDate("date") + "aaaaaaaaaa");
		result.add(set.getDate("date"));
		System.out.println(result + "bbbbbb");
		}
		System.out.println(result);
		return result;
		} catch (SQLException ex) {
		Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;*/
		
		
		
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT date from `game` where HomeTeam = ? and AwayTeam = ?";
		List<Date> l = new ArrayList<Date>();
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setString(1, team1);
			ste.setString(2, team2);

			ResultSet result = ste.executeQuery();

			while (result.next()) {
				l.add(result.getDate("date"));

				//return new Team(result.getString("name"), result.getString("coach"), result.getString("president"), result.getString("area"), result.getInt("gamesPlayed"), result.getInt("goalScored"), result.getInt("goalAgainst"), result.getInt("participations"), result.getDate("fifaDate"), result.getString("wcGroup"), result.getInt("win"), result.getInt("loose"), result.getInt("draw"), result.getInt("points"), result.getInt("fifaRank"), result.getString("flagPhoto"), result.getString("logoPhoto"), result.getString("squadPhoto"), result.getString("descriptionPhoto"), result.getString("description"), result.getString("website"), result.getString("video"));
			}
			return l ;
		} catch (SQLException ex) {
			Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
		
		
	}

	public static void update(String row, String value, int id) {
		Connection con = DataSource.getInstance().getCon();
		String strSQLQuery = String.format("update game set %s = '%s' where id=%s", row, value, id);
		try {
			PreparedStatement ste = con.prepareStatement(strSQLQuery);
			ste.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void update(String row, int value, int id) {
		Connection con = DataSource.getInstance().getCon();
		String strSQLQuery = String.format("update game set %s = '%s' where id=%s", row, value, id);
		System.out.println(strSQLQuery);
		try {
			PreparedStatement ste = con.prepareStatement(strSQLQuery);
			ste.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void update(String row, Date value, int id) {
		Connection con = DataSource.getInstance().getCon();
		String strSQLQuery = String.format("update game set %s = '%s' where id=%s", row, value, id);
		System.out.println(strSQLQuery);
		try {

			PreparedStatement ste = con.prepareStatement(strSQLQuery);
			ste.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void updateSquad(String row, String value, Date date) {
		Connection con = DataSource.getInstance().getCon();
		String strSQLQuery = String.format("update game set %s = '%s' where date=?", row, value);
		System.out.println(strSQLQuery);
		try {

			PreparedStatement ste = con.prepareStatement(strSQLQuery);
			ste.setDate(1, date);
			ste.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void removeGame(int id) {
		Connection con = DataSource.getInstance().getCon();
		String query = "DELETE FROM `Game` WHERE id=?";
		try {
			PreparedStatement ste = con.prepareStatement(query);

			ste.setInt(1, id);

			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
