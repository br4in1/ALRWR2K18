/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Article;
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
			String sql = "INSERT INTO articles (titre, contenu, idEntity, typeEntity, datePublication, derniereModification, Auteur) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ste = conn.prepareStatement(sql);
			ste.setString(1, n.getTitre());
			ste.setString(2, n.getContenu());
			ste.setInt(3, n.getIdEntity());
			ste.setString(4, n.getTypeEntity());
			ste.setDate(5, n.getDatePublication());
			ste.setDate(6, n.getDerniereModification());
			ste.setInt(7, n.getAuteur());
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
						result.getDate("derniereModification"), result.getInt("auteur")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public static Article findById(int id) {
		String sql = "SELECT * from articles where id = ?";
		try {
			PreparedStatement ste = conn.prepareStatement(sql);
			ste.setInt(1, id);
			ResultSet result = ste.executeQuery();
			if (result.next()) {
				return new Article(result.getInt("id"), result.getString("titre"), result.getString("contenu"),
						result.getInt("idEntity"), result.getString("typeEntity"), result.getDate("datePublication"),
						result.getDate("derniereModification"), result.getInt("auteur"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public static Article update(int id, String column, int value) {
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
		String sql = null;
		if (column.equals("titre")) {
			sql = "Update articles set titre = ?  where id  = ?";
		} else if (column.equals("contenu")) {
			sql = "Update articles set contenu = ?  where id  = ?";
		} else if (column.equals("typeEntity")) {
			sql = "Update articles set typeEntity = ?  where id  = ?";
		} else if (column.equals("idEntity") || column.equals("auteur")) {
			update(id, column, Integer.parseInt(value));
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
}
