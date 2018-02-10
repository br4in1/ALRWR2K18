/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.SimpleUser;
import Utils.BCrypt;
import java.sql.Connection;
import Utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author br4in
 */
public class UserCrud {
	public static Boolean findUserByUsername(String username){
		Connection con = DataSource.getInstance().getCon();
		String query = "select * from User where username = '"+username+"'";
        try {
            Statement ste = con.createStatement();
            ResultSet set = ste.executeQuery(query);
            if(set.next()) return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
		return false;
	}
	
	public static Boolean findUserByEmail(String email){
		Connection con = DataSource.getInstance().getCon();
		String query = "select * from User where email = '"+email+"'";
        try {
            Statement ste = con.createStatement();
            ResultSet set = ste.executeQuery(query);
            if(set.next()) return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
		return false;
	}
	
	public static void AddUserToDataBaseStepOne(SimpleUser u){
		Connection con = DataSource.getInstance().getCon();
		String query = "INSERT INTO User(username,username_canonical,email,email_canonical,enabled,password,roles,birth_date,registration_date,lastname,firstname,nationality,loggedin,fidelity_points) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			ste.setInt(13,(u.getLoggedin()) ? 1 : 0);
			ste.setInt(14,0);
			ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
