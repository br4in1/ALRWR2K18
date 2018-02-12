/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DataSource;
import Views.main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simo
 */
public class StadiumCrud {
	public static HashMap<String,Integer> GetNameIdMap(){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		Connection con = DataSource.getInstance().getCon();
		String query = "SELECT id,name from Stadium";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ResultSet set = ste.executeQuery();
			while (set.next()) {
				map.put(set.getString("name"), set.getInt("id"));
			}
			return map;
		} catch (SQLException ex) {
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
	}
}
