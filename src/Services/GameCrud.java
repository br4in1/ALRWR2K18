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
            ste.setInt(2, game.getHomeTeam());
            ste.setInt(3, game.getAwayTeam());
            ste.setString(4, game.getResult());
            ste.setInt(5, game.getStadium());
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
        String query = "select * from Game";
        try {
            Statement ste = con.createStatement();
            ResultSet set = ste.executeQuery(query);
            while (set.next()) {
                Game g = new Game(set.getDate("Date"), set.getInt("HomeTeam"), set.getInt("AwayTeam"), set.getString("Result"), set.getInt("Stadium"), set.getString("Summary"), set.getString("SummaryPhoto"), set.getString("Highlights"), set.getString("Referee"));
                g.setId(set.getInt("id"));
                result.add(g);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<Game> findGamesForArticles() {
        Connection con = DataSource.getInstance().getCon();
        List<Game> list = new ArrayList<>();
        String query = "SELECT * from Game";
        try {
            PreparedStatement ste = con.prepareStatement(query);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                Game g = new Game();
                g.setId(result.getInt("id"));
                g.setDate(result.getDate("Date"));
                g.setAwayTeam(result.getInt("AwayTeam"));
                g.setHomeTeam(result.getInt("HomeTeam"));
                list.add(g);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    
}
