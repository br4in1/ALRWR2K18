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
import java.sql.ResultSet;
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
            ste.setInt(2, game.getHomeTeam().getId());
            ste.setInt(3, game.getAwayTeam().getId());
            ste.setString(4, game.getResult());
            ste.setInt(5, game.getStadium().getId());
            ste.setString(6, game.getSummary());
            ste.setString(7, game.getSummaryPhoto());
            ste.setString(8, game.getHighlights());
            ste.setString(9, game.getReferee());

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GameCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Game> findGamesForArticles() {
        Connection con = DataSource.getInstance().getCon();
        List<Game> list = new ArrayList<>();

        String query = "SELECT * from Game";
        String team = "SELECT * from team where id = ?";
        try {
            PreparedStatement ste = con.prepareStatement(query);
            ResultSet result = ste.executeQuery();
            PreparedStatement ste1 = con.prepareStatement(team);

            while (result.next()) {
                Game g = new Game();
                Team t = new Team();

                g.setId(result.getInt("id"));
                g.setDate(result.getDate("Date"));

                ste1.setInt(1, result.getInt("HomeTeam"));
                ResultSet result1 = ste1.executeQuery();
                if (result1.next()) {
                    t.setName(result1.getString("name"));
                    g.setHomeTeam(t);
                }
                ste1.setInt(1, result.getInt("AwayTeam"));
                ResultSet result2 = ste1.executeQuery();
                if (result2.next()) {
                    t.setName(result2.getString("name"));
                    g.setAwayTeam(t);
                }

                list.add(g);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

}
