/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simo
 */
public class DataSource {
    private Connection con;
    private static DataSource data;
    final String url = "jdbc:mysql://localhost:3306/WorldCup";
    final String user = "root";
    final String pwd = "root";
    private DataSource() {
        try {
            con = DriverManager.getConnection(url,user,pwd);   
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getCon() {
        return con;
    }
	
    public static DataSource getInstance(){
        if(data == null){
            data = new DataSource();
        }
        return data;
    }
    
}
