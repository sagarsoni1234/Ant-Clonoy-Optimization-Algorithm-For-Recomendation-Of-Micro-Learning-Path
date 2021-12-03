package Ant_Pack3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class MysqlConnector {
	public static Connection getConnection() {
		Connection con=null;
		try {                                           
			con=DriverManager.getConnection("jdbc:mysql://localhost/jsgmysql","root","");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String args[]) {
		Connection con=getConnection();
		if(con==null) {
			System.out.println("Not Connected!");
		}
		else
			System.out.println("***Connected!***");	
	}
}

