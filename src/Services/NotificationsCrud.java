package Services;


import Controllers.Login_formController;
import Entities.Notification;
import Services.UserCrud;
import Utils.BCrypt;
import Utils.DataSource;
import Utils.TokenGenerator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author br4in
 */
public class NotificationsCrud {
	public static List<Notification> GetMyNewNotifications(String username){
		List<Notification> ret = new ArrayList<Notification>();
		Connection con = DataSource.getInstance().getCon();
		String query = "select n.* from notifications n join user u on u.id = n.idUser where u.username = '"+username+"' and n.state = 0";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			while (set.next()) {
				Notification n = new Notification(set.getInt("idUser"), set.getString("text"), set.getBoolean("state"));
				n.setId(set.getInt("id"));
				ret.add(n);
			}
			return ret;
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public static void MakeNotificationSeen(int id){
		Connection con = DataSource.getInstance().getCon();
		String query = "update notifications set state = 1 where id = ?";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setInt(1, id);
			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void AddNotification(String Text,int idUser){
		Connection con = DataSource.getInstance().getCon();
		String query = "insert into notifications(idUser,text,state) values(?,?,0)";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setInt(1, idUser);
			ste.setString(2, Text);
			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
