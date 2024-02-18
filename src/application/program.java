package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbExeception;

public class program {

    public static void main(String[] args) {
      
    	Connection conn = null;
    	Statement st = null;
    	  try {
    		  conn = DB.getConnection();
    		  st = conn.createStatement();
    		  conn.setAutoCommit(false);
    		  
    		  
    		  
    		  int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
    		
//    		  int x = 1;
//    		   if(x < 2) {
//    			   throw new SQLException("error falso");
//    		   }
    		  
    		  int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
    			
    		  conn.commit();
    		  
    		  System.out.println("rowns1 = " + rows1);
    		  System.out.println("rowns2 = " + rows2);
    		  
    	  } catch ( SQLException e) {
    		     try {
					conn.rollback();
					throw new DbExeception("transaction rolled back! caused by: " + e.getMessage());
				} catch (SQLException e1) {
					throw new DbExeception("error trying to rollback!caused by:  " + e1.getMessage());
				}
    	  } finally {
    		  DB.closeConnection();
    		  DB.closeStatement(st);
    		  
    	  }
       
          
    }
}