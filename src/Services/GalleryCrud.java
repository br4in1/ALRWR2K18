/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Admin;
import Entities.Gallery;
import Entities.SimpleUser;
import Utils.DataSource;
import static com.mysql.jdbc.Messages.getString;
import static com.sun.javafx.fxml.expression.Expression.set;
import static com.sun.scenario.Settings.set;
import static java.lang.reflect.Array.set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author dell
 */
public class GalleryCrud {

	public static void AddImage(Gallery G) {
		Connection con = DataSource.getInstance().getCon();
		String query = "INSERT INTO `gallery`(`idUser`, `ville` , `lieu` , `description`, `image`, `etat`) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setInt(1, G.getIdUser());
			ste.setString(2, G.getVille());
			ste.setString(3, G.getLieu());
			ste.setString(4, G.getDescription());
			ste.setString(5, G.getImage());
			ste.setString(6, G.getEtat());

			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(GalleryCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static List<String> DisplayImageFromDB() throws SQLException {
		Connection con = DataSource.getInstance().getCon();
		List<String> list = new ArrayList<>();

		String req = "select image from gallery";
		PreparedStatement ste = con.prepareStatement(req);
		ResultSet result = ste.executeQuery();//select 
		while (result.next()) {
			list.add(result.getString(1));
		}

		return list;

	}

	public static List<Gallery> DisplayAll() {
		Connection con = DataSource.getInstance().getCon();
		List<Gallery> list = new ArrayList<>();
		try {

			String req = "SELECT * FROM `gallery`";
			PreparedStatement ste = con.prepareStatement(req);
			ResultSet result = ste.executeQuery();
			while (result.next()) {
				Gallery g = new Gallery(result.getInt("idUser"), result.getString("ville"), result.getString("lieu"), result.getString("description"), result.getString("image"), result.getString("etat"));
				g.setId(result.getInt("id"));

				list.add(g);

			}

		} catch (SQLException ex) {
			Logger.getLogger(GalleryCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;

	}

	public static List<Gallery> DisplayVille(int id) {
		Connection con = DataSource.getInstance().getCon();
		List<Gallery> list = new ArrayList<>();
		try {

			String req = "SELECT ville FROM `gallery` where id=(?)";
			PreparedStatement ste = con.prepareStatement(req);
			ste.setInt(1, id);
			ResultSet result = ste.executeQuery();

			while (result.next()) {
				Gallery g = new Gallery(result.getString("ville"));

				list.add(g);

			}

		} catch (SQLException ex) {
			Logger.getLogger(GalleryCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;

	}

	public void DeleteImage(int id) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getCon();
		String req = "DELETE from  gallery  WHERE id =?";
		PreparedStatement pre = con.prepareStatement(req);
		pre.setInt(1, id);
		pre.executeUpdate();
	}

	public void ModifyEtat(int id, Gallery g) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getCon();
		String req = "UPDATE `gallery` SET `etat`=? WHERE id=? ";
		PreparedStatement pre = con.prepareStatement(req);
		pre.setString(1, g.getEtat());
		pre.setInt(2, id);
		pre.executeUpdate();
	}

	public int returnEtat0() throws SQLException {
		int count = 0;
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getCon();
		Statement stmt3 = con.createStatement();
		ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) from `gallery` WHERE `etat`='0' ");
		while (rs3.next()) {
			count = rs3.getInt(1);

		};
		return count;
	}

	public int returnEtat1() throws SQLException {

		int count = 0;
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getCon();
		Statement stmt3 = con.createStatement();
		ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) from `gallery` WHERE `etat`='1' ");
		while (rs3.next()) {
			count = rs3.getInt(1);

		};
		return count;
	}
	
	public static Gallery findGalleryById(int id){
		Connection con = DataSource.getInstance().getCon();
		List<Gallery> list = new ArrayList<>();
		try {

			String req = "SELECT * FROM `gallery` where id = "+id;
			PreparedStatement ste = con.prepareStatement(req);
			ResultSet result = ste.executeQuery();
			if (result.next()) {
				Gallery g = new Gallery(result.getInt("idUser"), result.getString("ville"), result.getString("lieu"), result.getString("description"), result.getString("image"), result.getString("etat"));
				g.setId(result.getInt("id"));
				return g;
			}
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(GalleryCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
