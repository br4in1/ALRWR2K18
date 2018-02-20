/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Gallery;
import Entities.Opinions;
import Utils.DataSource;
import com.sun.corba.se.spi.oa.OADefault;
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

/**
 *
 * @author dell
 */
public class OpinionsCrud {
	public static void AddOpinion(Opinions O) {
		Connection con = DataSource.getInstance().getCon();
		String query = "INSERT INTO `opinions`(`idUser`, `avis` , `nbreEtoiles`) VALUES (?,?,?)";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setInt(1, O.getIdUser());
			ste.setString(2, O.getAvis());
			ste.setString(3,O.getNbreEtoiles());
			

			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(OpinionsCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static List<Opinions> DisplayAllOpinions() {
		Connection con = DataSource.getInstance().getCon();
		List<Opinions> list = new ArrayList<>();
		try {

			String req = "SELECT * FROM `opinions`";
			PreparedStatement ste = con.prepareStatement(req);
			ResultSet result = ste.executeQuery();
			while (result.next()) {
				Opinions O = new Opinions(result.getInt("idUser"),result.getString("avis"), result.getString("nbreEtoiles"));
				O.setId(result.getInt("id"));
				list.add(O);

			}

		} catch (SQLException ex) {
			Logger.getLogger(GalleryCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;

	}
		public void DeleteOpinion(int id) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getCon();
		String req = "DELETE from  opinions  WHERE id =?";
		PreparedStatement pre = con.prepareStatement(req);
		pre.setInt(1, id);
		pre.executeUpdate();
	}
		public List<Integer> AfficherID() throws SQLException {
		List<Integer> list = new ArrayList<>();
		Connection con = DataSource.getInstance().getCon();
		String req = "SELECT `id` FROM `opinions`";
			PreparedStatement ste = con.prepareStatement(req);
			ResultSet result = ste.executeQuery();
		while (result.next()) {

		 list.add(result.getInt(1));

		}
		return list;	
			

	}
		public List<String> AfficherEtoiles() throws SQLException {
		List<String> list = new ArrayList<>();
		Connection con = DataSource.getInstance().getCon();
		String req = "SELECT `nbreEtoiles` FROM `opinions`";
			PreparedStatement ste = con.prepareStatement(req);
			ResultSet result = ste.executeQuery();
		while (result.next()) {

		 list.add(result.getString(1));

		}
		return list;	
			

	}
	
}
