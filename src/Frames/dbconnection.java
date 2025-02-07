/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frames;

import java.sql.*;

/**
 *
 * @author KADSE241F-042
 */
public class dbconnection {
   private static dbconnection instance;
   private final String url = "jdbc:mysql://localhost:3306/library";
   private final String username = "root";
   private final String password = "";
   private Connection con;
    
   private dbconnection(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           con = DriverManager.getConnection(url,username,password);
       } catch(Exception e){
           System.out.println(e);
       }
   }
   public static dbconnection getInstance(){
       if(instance == null){
           instance = new dbconnection();
   }
       return instance;
   }
   public Connection getConnection(){
       return con;
   }
}
