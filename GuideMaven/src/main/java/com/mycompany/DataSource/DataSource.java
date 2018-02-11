/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.DataSource;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sof
 */
public class DataSource {   //jdbc:mysql://localhost:3306/espritbd
    private static DataSource data ; 
    final String url ="jdbc:mysql://localhost:3306/WorldCup" ; 
    final String user = "root";
    final String password = "";
     Connection con ; 
    private DataSource()
    {
    try {
        con = DriverManager.getConnection(url,user,password) ; 
        System.out.println("Connected");
    }
    catch(SQLException ex)  {
        System.out.println(ex);
    
                            }
    }
    
    public Connection getConnection()
    {
    return con ; 
    }
    
    public static DataSource getInstance()
    {
    if(data == null)
    {
    data = new DataSource();
    }
    return data;
    }
    
    
}

