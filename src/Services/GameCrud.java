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
import Utils.DataSource;
import com.sun.rowset.internal.Row;
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
			ste.setInt(2, Integer.parseInt(game.getHomeTeam()));
			ste.setInt(3, Integer.parseInt(game.getAwayTeam()));
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
		String query = "select g.*,t1.name 'nomaway',t2.name 'nomhome',s.name 'nomstade' from Game g join Team t1 on t1.id = g.HomeTeam join Team t2 on t2.id = g.AwayTeam join Stadium s on s.id = g.Stadium";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			while (set.next()) {
				Game g = new Game(set.getDate("Date"), set.getString("nomhome"), set.getString("nomaway"), set.getString("Result"), set.getString("nomstade"), set.getString("Summary"), set.getString("SummaryPhoto"), set.getString("Highlights"), set.getString("Referee"));

				g.setId(set.getInt("id"));
				result.add(g);
			}

			System.out.println(result);
			return result;
		} catch (SQLException ex) {
			Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static void update(String row, String value, int id) {
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
