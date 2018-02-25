/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Gallery;
import Entities.Likes;
import Utils.DataSource;
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
public class LikesCrud {
	
	public static void Like(Likes L) {
		Connection con = DataSource.getInstance().getCon();
		String query = "INSERT INTO `likes`(`idUser`, `idPhoto`) VALUES (?,?)";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setInt(1, L.getIdUser());
			ste.setInt(2, L.getIdPhoto());			
			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(LikesCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public static void Unlike(int id) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getCon();
		String req = "DELETE from  likes  WHERE idUser =?";
		PreparedStatement pre = con.prepareStatement(req);
		pre.setInt(1, id);
		pre.executeUpdate();
	}
	
	
	public int PhotoLiked(int id ) throws SQLException {
         int nbre=0;
		Scanner sc = new Scanner(System.in);
		Connection con = DataSource.getInstance().getCon();
		Statement stmt3 = con.createStatement();
		ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) as a from likes WHERE idPhoto="+id);
		while (rs3.next()) {
		nbre = rs3.getInt(1);
		}; 
		System.out.println(nbre);
		return nbre;

	}
	
	


	
}
