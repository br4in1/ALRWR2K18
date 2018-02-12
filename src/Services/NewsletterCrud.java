/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Newsletter;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raiiz
 */
public class NewsletterCrud {
    static Connection conn;
    private static NewsletterCrud news;

    private NewsletterCrud() {
        conn = DataSource.getInstance().getCon();
    }

    public static NewsletterCrud getRepository() {
        if (news == null) {
            news = new NewsletterCrud();
        }
        return news;
    }

    public Connection getConnection() {
        return conn;
    }
    
    public static boolean add(Newsletter n) {
        if (news == null) {
            conn = DataSource.getInstance().getCon();
        }
        try {
            String sql = "INSERT INTO newsletters (email, contenu, preferences) VALUES (?,?,?)";
            PreparedStatement ste = conn.prepareStatement(sql);
            ste.setString(1, n.getEmail());
            ste.setString(2, n.getContenu());
            ste.setInt(3, n.getPreferences());
            ste.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static List<Newsletter> findAll() {
        if (news == null) {
            conn = DataSource.getInstance().getCon();
        }
        List<Newsletter> list = new ArrayList<>();

        String req = "select * from newsletters";
        try{
        PreparedStatement ste = conn.prepareStatement(req);
        ResultSet result = ste.executeQuery();//select 
        while (result.next()) {
            list.add(new Newsletter(result.getInt("id"), result.getString("email"), result.getString("contenu"), result.getInt("preferences")));
        }
        return list;
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
                
    }

    public static Newsletter findById(int id)  {
        if (news == null) {
            conn = DataSource.getInstance().getCon();
        }
        String sql = "SELECT * from newsletters where id = ?";
        try{
        PreparedStatement ste = conn.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet result = ste.executeQuery();
        if (result.next()) {
            return new Newsletter(result.getInt("id"), result.getString("email"), result.getString("contenu"), result.getInt("preferences"));
        } else {
            return null;
        }
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public static Newsletter update(int id, int preferences) {
        /**
         * parameters: id , pereferences this to update preferences
         */
        if (news == null) {
            conn = DataSource.getInstance().getCon();
        }
        String sql = "Update newsletters set preferences = ?  where id  = ?";
        try{
        PreparedStatement ste = conn.prepareStatement(sql);
        ste.setInt(1, preferences);
        ste.setInt(2, id);
        ste.executeUpdate();
        return findById(id);
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public static Newsletter update(int id, String column, String value) {
        if (news == null) {
            conn = DataSource.getInstance().getCon();
        }
        if (column.equals("id")) {
            update(id, Integer.parseInt(value));
            return findById(id);
        }
        String sql = null;
        if (column.equals("email")) {
            sql = "Update newsletters set email = ?  where id  = ?";
        } else if (column.equals("contenu")) {
            sql = "Update newsletters set contenu = ?  where id  = ?";
        } else {
            System.out.println("verifier le nom de column puis ressayer");
            return null;
        }
        try{
        PreparedStatement ste = conn.prepareStatement(sql);
        ste.setString(1, value);
        ste.setInt(2, id);
        ste.executeUpdate();
        return findById(id);
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public static boolean remove(int id) {
        if (news == null) {
            conn = DataSource.getInstance().getCon();
        }
        String sql = "DELETE from newsletters where id  = ?";
        try {
            PreparedStatement ste = conn.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
