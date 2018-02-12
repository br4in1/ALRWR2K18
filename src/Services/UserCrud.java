/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Admin;
import Entities.Moderator;
import Entities.SimpleUser;
import Entities.User;
import Utils.BCrypt;
import java.util.*;
import javax.activation.*;
import java.sql.Connection;
import Utils.DataSource;
import Utils.TokenGenerator;
import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author br4in
 */
public class UserCrud {

	public static Boolean findUserByUsername(String username) {
		Connection con = DataSource.getInstance().getCon();
		String query = "select * from User where username = '" + username + "'";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			if (set.next()) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public static Boolean findUserByEmail(String email) {
		Connection con = DataSource.getInstance().getCon();
		String query = "select * from User where email = '" + email + "'";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			if (set.next()) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public static void AddUserToDataBaseStepOne(SimpleUser u) {
		Connection con = DataSource.getInstance().getCon();
		String query = "INSERT INTO User(username,username_canonical,email,email_canonical,enabled,password,roles,birth_date,registration_date,lastname,firstname,nationality,loggedin,fidelity_points,confirmation_token) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setString(1, u.getUsername());
			ste.setString(2, u.getUsername());
			ste.setString(3, u.getEmail());
			ste.setString(4, u.getEmail());
			ste.setInt(5, (u.getEnabled()) ? 1 : 0);
			ste.setString(6, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12)));
			ste.setString(7, u.getRoles());
			ste.setDate(8, u.getBirthdate());
			ste.setDate(9, u.getRegistrationdate());
			ste.setString(10, u.getLastname());
			ste.setString(11, u.getFirstname());
			ste.setString(12, u.getNationality());
			ste.setInt(13, (u.getLoggedin()) ? 1 : 0);
			ste.setInt(14, 0);
			ste.setString(15, TokenGenerator.generateToken(u.getUsername()));
			ste.executeUpdate();
			/*if(ste.isCloseOnCompletion()){
				String to = u.getEmail();
				String from = "mymail";
				String host = "smtp.gnet.tn";
				Properties prop = System.getProperties();
				prop.setProperty("mail.smtp.host", host);
				Session session = 
			}*/
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static User AuthenticateUser(String username, String password) {
		User u = null;
		Boolean found = false;
		Connection con = DataSource.getInstance().getCon();
		String query = "select * from User where username = '" + username + "'";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			if (set.next()) {
				if (set.getString("roles").equals("ROLE_USER")) {
					found = true;
					u = new SimpleUser(set.getDate("birth_date"), set.getDate("registration_date"), set.getString("nationality"), true, set.getInt("fidelity_points"), set.getString("profile_picture"), set.getString("username"), set.getString("email"), set.getBoolean("enabled"), null, set.getString("password"), null, set.getString("roles"), set.getString("firstname"), set.getString("lastname"));
				} else if (set.getString("roles").equals("ROLE_MODERATOR")) {
					found = true;
					u = new Moderator(set.getString("phone_number"),set.getString("username"), set.getString("email"), set.getBoolean("enabled"), null, set.getString("password"), null, set.getString("roles"), set.getString("firstname"), set.getString("lastname"));
				} else {
					found = true;
					u = new Admin(set.getString("phone_number"),set.getString("username"), set.getString("email"), set.getBoolean("enabled"), null, set.getString("password"), null, set.getString("roles"), set.getString("firstname"), set.getString("lastname"));
				}
				if (found && BCrypt.checkpw(password, u.getPassword())) {
					return u;
				} else {
					return null;
				}
			}
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static String getConfirmationToken(String username) {
		Connection con = DataSource.getInstance().getCon();
		String query = "select * from User where username = '" + username + "'";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			while (set.next()) {
				return set.getString("confirmation_token");
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static void enableSimpleUser(String username) {
		Connection con = DataSource.getInstance().getCon();
		String query = "update User set enabled = 1 where username = ?";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setString(1, username);
			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void UpdateUserPhoto(String url,String username){
		Connection con = DataSource.getInstance().getCon();
		String query = "update User set profile_picture = ? where username = ?";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setString(1, url);
			ste.setString(2, username);
			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
