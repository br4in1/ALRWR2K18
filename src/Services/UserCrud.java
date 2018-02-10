/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import Utils.DataSource;
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
}
