package Ant_Recco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class MysqlConnection {

	public static void main(String args[]) {
		Connection con=getConnection();
		if(con==null) {
			System.out.println("Not Connected!");
		}
		else
			System.out.println("***Connected!***");	
	}
	public static Connection getConnection() {
		// TODO Auto-generated method stub
		Connection con=null;
		try {                                           
			con=DriverManager.getConnection("jdbc:mysql://localhost/jsgmysql","root","");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;

	}
}

