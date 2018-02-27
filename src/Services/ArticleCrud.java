/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Article;
import Entities.User;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raiiz
 */
public class ArticleCrud {

    static Connection conn;
    private static ArticleCrud article;

    private ArticleCrud() {
        conn = DataSource.getInstance().getCon();
    }

    public static ArticleCrud getRepository() {
        if (article == null) {
            article = new ArticleCrud();
        }
        return article;
    }

    public Connection getConnection() {
        return conn;
    }

    public static boolean add(Article n) {
        if (article == null) {
            conn = DataSource.getInstance().getCon();
        }
        try {
            String sql = "INSERT INTO articles (titre, contenu, idEntity, typeEntity, datePublication, derniereModification, Auteur, articleImage) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ste = conn.prepareStatement(sql);
            ste.setString(1, n.getTitre());
            ste.setString(2, n.getContenu());
            ste.setInt(3, n.getIdEntity());
            ste.setString(4, n.getTypeEntity());
            ste.setDate(5, n.getDatePublication());
            ste.setDate(6, n.getDerniereModification());
            ste.setInt(7, n.getAuteur());
            ste.setString(8, n.getArticleImage());
            ste.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static List<Article> findAll() {
        if (article == null) {
            conn = DataSource.getInstance().getCon();
        }
        List<Article> list = new ArrayList<>();

        String req = "select * from articles";
        try {
            PreparedStatement ste = conn.prepareStatement(req);
            ResultSet result = ste.executeQuery();// select
            while (result.next()) {
                list.add(new Article(result.getInt("id"), result.getString("titre"), result.getString("contenu"),
                        result.getInt("idEntity"), result.getString("typeEntity"), result.getDate("datePublication"),
                        result.getDate("derniereModification"), result.getInt("auteur"), result.getString("articleImage")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Article> findAllOrderDate() {
        if (article == null) {
            conn = DataSource.getInstance().getCon();
        }
        List<Article> list = new ArrayList<>();

        String req = "select * from articles Order by datePublication Desc";
        try {
            PreparedStatement ste = conn.prepareStatement(req);
            ResultSet result = ste.executeQuery();// select
            while (result.next()) {
                list.add(new Article(result.getInt("id"), result.getString("titre"), result.getString("contenu"),
                        result.getInt("idEntity"), result.getString("typeEntity"), result.getDate("datePublication"),
                        result.getDate("derniereModification"), result.getInt("auteur"), result.getString("articleImage")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public static Article findById(int id) {
        if (article == null) {
            conn = DataSource.getInstance().getCon();
        }
        String sql = "SELECT * from articles where id = ?";
        try {
            PreparedStatement ste = conn.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            if (result.next()) {
                return new Article(result.getInt("id"), result.getString("titre"), result.getString("contenu"),
                        result.getInt("idEntity"), result.getString("typeEntity"), result.getDate("datePublication"),
                        result.getDate("derniereModification"), result.getInt("auteur"), result.getString("articleImage"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public static Article updateInts(int id, String column, int value) {
        if (article == null) {
            conn = DataSource.getInstance().getCon();
        }
        String sql = null;
        if (column.equals("idEntity")) {
            sql = "Update articles set idEntity= ?  where id  = ?";
        } else if (column.equals("auteur")) {
            sql = "Update articles set auteur = ?  where id  = ?";
        } else {
            System.out.println("verifier le nom de column puis ressayer");
            return null;
        }
        try {
            PreparedStatement ste = conn.prepareStatement(sql);
            ste.setInt(1, value);
            ste.setInt(2, id);
            ste.executeUpdate();
            return findById(id);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public static Article update(int id, String column, String value) {
        if (article == null) {
            conn = DataSource.getInstance().getCon();
        }
        String sql = null;
        if (column.equals("titre")) {
            sql = "Update articles set titre = ?  where id  = ?";
        } else if (column.equals("contenu")) {
            sql = "Update articles set contenu = ?  where id  = ?";
        } else if (column.equals("typeEntity")) {
            sql = "Update articles set typeEntity = ?  where id  = ?";
        } else if (column.equals("idEntity") || column.equals("auteur")) {
            updateInts(id, column, Integer.parseInt(value));
            return findById(id);
        } else if (column.equals("datePublication")) {
            sql = "Update articles set datePublication = ?  where id  = ?";
        } else if (column.equals("derniereModification")) {
            sql = "Update articles set derniereModification = ?  where id  = ?";
        } else {
            System.out.println("verifier le nom de column puis ressayer");
            return null;
        }
        try {
            PreparedStatement ste = conn.prepareStatement(sql);
            ste.setString(1, value);
            ste.setInt(2, id);
            ste.executeUpdate();
            return findById(id);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public static boolean remove(int id) {
        if (article == null) {
            conn = DataSource.getInstance().getCon();
        }
        String sql = "DELETE from articles where id  = ?";
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

    public static boolean remove(Article a) {
        if (article == null) {
            conn = DataSource.getInstance().getCon();
        }
        String sql = "DELETE from articles where id  = ?";
        try {
            PreparedStatement ste = conn.prepareStatement(sql);
            ste.setInt(1, a.getId());
            ste.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static List<Article> findAllByCategory(String cat) {
        if (article == null) {
            conn = DataSource.getInstance().getCon();
        }
        List<Article> list = new ArrayList<>();

        String req = "select * from articles where typeEntity = ?";
        try {
            PreparedStatement ste = conn.prepareStatement(req);
            ste.setString(1, cat);
            ResultSet result = ste.executeQuery();// select
            while (result.next()) {
                list.add(new Article(result.getInt("id"), result.getString("titre"), result.getString("contenu"),
                        result.getInt("idEntity"), result.getString("typeEntity"), result.getDate("datePublication"),
                        result.getDate("derniereModification"), result.getInt("auteur"), result.getString("articleImage")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public static AbstractMap.SimpleEntry<Article, User> findArticleUserById(int id) {
        if (article == null) {
            conn = DataSource.getInstance().getCon();
        }
        String req = "select u.id 'userId', u.firstname 'userFirstname', u.lastname 'userLastname', a.id, a.titre, a.contenu, a.idEntity, a.typeEntity, a.datePublication, a.derniereModification, a.auteur, a.articleImage from User u INNER JOIN Articles a on u.id = a.auteur where a.id = ?";
        try {
            PreparedStatement ste = conn.prepareStatement(req);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            if (result.next()) {
               return new AbstractMap.SimpleEntry<Article, User>(new Article(result.getInt("id"), result.getString("titre"), result.getString("contenu"),
                        result.getInt("idEntity"), result.getString("typeEntity"), result.getDate("datePublication"),
                        result.getDate("derniereModification"), result.getInt("auteur"), result.getString("articleImage")), new User(null, null,null, null,null,null,null,result.getString("userFirstname"), result.getString("userLastname")));
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    public static List<Article> findAllByKeyword(String keyword) {
        if (article == null) {
            conn = DataSource.getInstance().getCon();
        }
        List<Article> list = new ArrayList<>();

        String req = "select * from articles where titre like ? or contenu like ?";
        try {
            PreparedStatement ste = conn.prepareStatement(req);
            ste.setString(1, "%" + keyword +"%");
            ste.setString(2,"%" + keyword +"%");

            ResultSet result = ste.executeQuery();// select
            while (result.next()) {
                list.add(new Article(result.getInt("id"), result.getString("titre"), result.getString("contenu"),
                        result.getInt("idEntity"), result.getString("typeEntity"), result.getDate("datePublication"),
                        result.getDate("derniereModification"), result.getInt("auteur"), result.getString("articleImage")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
