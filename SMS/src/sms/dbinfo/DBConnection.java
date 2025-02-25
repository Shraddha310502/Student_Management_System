package sms.dbinfo;
import java.sql.*;

public class DBConnection {

	private static Connection con=null;
	
	public static Connection openConnection()
	{
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");  //it is used to create the object 
			//factory methods --> to create the object of a class 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sms_db","root","root");
			//subprotocol(jdbc:mysql)://localhost(is the name of IP address of the Machine where DB has been installed
			
			//3306(is the port number on which MQSL listen the request 
			//sms_db(database name)
			//root  -->userid in MYSQL
			//root -->password
			//connection string-->  "jdbc:mysql://localhost:3306/sms_db
		}
		catch(ClassNotFoundException | SQLException cse)
		{
			cse.printStackTrace();
		}
		return con;
	}
	
	public static void main(String[] args) {
		Connection con=openConnection();
		System.out.println(con);
	}
}
