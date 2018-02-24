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
import java.sql.SQLException;
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
		String req = "DELETE from  likes  WHERE idPhoto =?";
		PreparedStatement pre = con.prepareStatement(req);
		pre.setInt(1, id);
		pre.executeUpdate();
	}
	
	


	
}
