/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Controllers.Login_formController;
import Entities.Admin;
import Entities.Moderator;
import Entities.SimpleUser;
import Entities.User;
import Utils.BCrypt;
import java.util.*;
import java.sql.Connection;
import Utils.DataSource;
import Utils.TokenGenerator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

	public static SimpleUser getSimpleUserByUsername(String username) {
		Connection con = DataSource.getInstance().getCon();
		String query = "select * from User where username = '" + username + "'";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			if (set.next()) {
				SimpleUser u = new SimpleUser(set.getDate("birth_date"), set.getDate("registration_date"), set.getString("nationality"), true, set.getInt("fidelity_points"), set.getString("profile_picture"), set.getString("username"), set.getString("email"), set.getBoolean("enabled"), null, set.getString("password"), Timestamp.valueOf(LocalDateTime.now()), set.getString("roles"), set.getString("firstname"), set.getString("lastname"));
				return u;
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
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
			String confirmationToken = TokenGenerator.generateToken(u.getUsername());
			ste.setString(15, confirmationToken);
			ste.executeUpdate();
			Login_formController.SendConfirmationToken(u.getEmail(), confirmationToken);
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
					u = new SimpleUser(set.getDate("birth_date"), set.getDate("registration_date"), set.getString("nationality"), true, set.getInt("fidelity_points"), set.getString("profile_picture"), set.getString("username"), set.getString("email"), set.getBoolean("enabled"), null, set.getString("password"), Timestamp.valueOf(LocalDateTime.now()), set.getString("roles"), set.getString("firstname"), set.getString("lastname"));
					String q = "update User set loggedin = 1,last_login = ? where username = ?";
					try {
						PreparedStatement ste1 = con.prepareStatement(q);
						ste1.setTimestamp(1, java.sql.Timestamp.valueOf(LocalDateTime.now()));
						ste1.setString(2, username);
						ste1.executeUpdate();
					} catch (SQLException ex) {
						Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
					}
				} else if (set.getString("roles").equals("ROLE_MODERATOR")) {
					found = true;
					u = new Moderator(set.getString("phone_number"), set.getString("username"), set.getString("email"), set.getBoolean("enabled"), null, set.getString("password"), Timestamp.valueOf(LocalDateTime.now()), set.getString("roles"), set.getString("firstname"), set.getString("lastname"));
				} else {
					found = true;
					u = new Admin(set.getString("phone_number"), set.getString("username"), set.getString("email"), set.getBoolean("enabled"), null, set.getString("password"), Timestamp.valueOf(LocalDateTime.now()), set.getString("roles"), set.getString("firstname"), set.getString("lastname"));
				}
				if (found && BCrypt.checkpw(password, u.getPassword())) {
					u.setId(set.getInt("id"));
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

	public static void UpdateUserPhoto(String url, String username) {
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

	public static SimpleUser GetMyData_SimpleUser(SimpleUser u) {
		Connection con = DataSource.getInstance().getCon();
		String query = "select * from User where username = '" + u.getUsername() + "'";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			if (set.next()) {
				u.setId(set.getInt("id"));
				u.setRegistrationdate(set.getDate("registration_date"));
				u.setBirthdate(set.getDate("birth_date"));
				u.setFidaelitypoints(set.getInt("fidelity_points"));
				u.setLoggedin(true);
				u.setLast_login(new Timestamp(new Date().getTime()));
				u.setNationality(set.getString("nationality"));
				u.setFirstname(set.getString("firstname"));
				u.setLastname(set.getString("lastname"));
				return u;
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static Moderator GetMyData_Moderator(Moderator u) {
		Connection con = DataSource.getInstance().getCon();
		String query = "select * from User where username = '" + u.getUsername() + "'";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			if (set.next()) {
				u.setId(set.getInt("id"));
				u.setPhonenumber(set.getString("phone_number"));
				return u;
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static Admin GetMyData_Admin(Admin u) {
		Connection con = DataSource.getInstance().getCon();
		String query = "select * from User where username = '" + u.getUsername() + "'";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			if (set.next()) {
				u.setId(set.getInt("id"));
				u.setPhonenumber(set.getString("phone_number"));
				return u;
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static List<SimpleUser> getAllSimpleUsers() {
		Connection con = DataSource.getInstance().getCon();
		List users = new ArrayList<SimpleUser>();
		String query = "select * from User where roles = 'ROLE_USER'";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			while (set.next()) {
				SimpleUser u = new SimpleUser(set.getDate("birth_date"), set.getDate("registration_date"), set.getString("nationality"), set.getBoolean("loggedin"), set.getInt("fidelity_points"), set.getString("profile_picture"), set.getString("username"), set.getString("email"), set.getBoolean("enabled"), null, set.getString("password"), Timestamp.valueOf(LocalDateTime.now()), set.getString("roles"), set.getString("firstname"), set.getString("lastname"));
				users.add(u);
			}
			return users;
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static void BanSimpleUser(String username) {
		Connection con = DataSource.getInstance().getCon();
		String query = "update User set enabled = 0,loggedin = 0 where username = ?";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setString(1, username);
			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void UnBanSimpleUser(String username) {
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

	public static void LogOutUser(String username) {
		Connection con = DataSource.getInstance().getCon();
		String query = "update User set loggedin = 0 where username = ?";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setString(1, username);
			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static int GetLoggedInNumber() {
		Connection con = DataSource.getInstance().getCon();
		String query = "select count(*) as nb from User where roles='ROLE_USER' and loggedin = 1";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			if (set.next()) {
				return set.getInt("nb");
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 0;
	}

	public static HashMap<String, Integer> GetCountriesChartData() {
		Connection con = DataSource.getInstance().getCon();
		HashMap<String, Integer> ret = new HashMap<String, Integer>();
		String query = "select nationality,count(*) as nb from User where roles='ROLE_USER' group by nationality having nationality != '' limit 5";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			while (set.next()) {
				ret.put(set.getString("nationality"), set.getInt("nb"));
			}
			return ret;
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static HashMap<Integer, Integer> GetAgesChartData() {
		Connection con = DataSource.getInstance().getCon();
		HashMap<Integer, Integer> ret = new HashMap<Integer, Integer>();
		String query = "select YEAR(STR_TO_DATE(birth_date, '%Y-%m-%d')) as year,count(*) as nb from User where roles='ROLE_USER' group by YEAR(STR_TO_DATE(birth_date, '%Y-%m-%d')) having year IS NOT NULL limit 5";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			while (set.next()) {
				ret.put(Math.abs(LocalDateTime.now().getYear() - set.getInt("year")), set.getInt("nb"));
			}
			return ret;
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static List<Moderator> getAllModerators() {
		Connection con = DataSource.getInstance().getCon();
		List users = new ArrayList<Moderator>();
		String query = "select * from User where roles = 'ROLE_MODERATOR'";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			while (set.next()) {
				Moderator u = new Moderator(set.getString("phone_number"), set.getString("username"), set.getString("email"), set.getBoolean("enabled"), "", "", set.getTimestamp("last_login"), "ROLE_MODERATOR", set.getString("firstname"), set.getString("lastname"));
				users.add(u);
			}
			return users;
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static void BanModerator(String username) {
		Connection con = DataSource.getInstance().getCon();
		String query = "update User set enabled = 0 where username = ?";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setString(1, username);
			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static boolean AddModeratorToDb(Moderator u) {
		Connection con = DataSource.getInstance().getCon();
		String query = "INSERT INTO User(username,username_canonical,email,email_canonical,enabled,password,roles,lastname,firstname,phone_number) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setString(1, u.getUsername());
			ste.setString(2, u.getUsername());
			ste.setString(3, u.getEmail());
			ste.setString(4, u.getEmail());
			ste.setInt(5, (u.getEnabled()) ? 1 : 0);
			ste.setString(6, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12)));
			ste.setString(7, u.getRoles());
			ste.setString(8, u.getLastname());
			ste.setString(9, u.getFirstname());
			ste.setString(10, u.getPhonenumber());
			ste.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public static void UnbanModerator(String username) {
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

	public static List<SimpleUser> searchSimpleUsers(String term) {
		Connection con = DataSource.getInstance().getCon();
		List users = new ArrayList<Moderator>();
		String query = "select * from User where roles = 'ROLE_USER' and (email like '%" + term + "%' or username like '%" + term + "%' or nationality like '%" + term + "%' or firstname like '%" + term + "%' or lastname like '%" + term + "%')";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			while (set.next()) {
				SimpleUser u = new SimpleUser(set.getDate("birth_date"), set.getDate("registration_date"), set.getString("nationality"), true, set.getInt("fidelity_points"), set.getString("profile_picture"), set.getString("username"), set.getString("email"), set.getBoolean("enabled"), null, set.getString("password"), Timestamp.valueOf(LocalDateTime.now()), set.getString("roles"), set.getString("firstname"), set.getString("lastname"));
				users.add(u);
			}
			return users;
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public static Integer getNumberOfUsers(){
		Connection con = DataSource.getInstance().getCon();
		String query = "select count(*) as nb from User where roles = 'ROLE_USER'";
		try {
			Statement ste = con.createStatement();
			ResultSet set = ste.executeQuery(query);
			if (set.next()) {
				return set.getInt("nb");
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public static void ChangePasswordForUser(String email,String newpassword){
		Connection con = DataSource.getInstance().getCon();
		String query = "update User set password = ? where email = ?";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setString(1, BCrypt.hashpw(newpassword, BCrypt.gensalt(12)));
			ste.setString(2, email);
			ste.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
