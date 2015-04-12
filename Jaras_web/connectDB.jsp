
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
 
public class SQL{ 
 
	public static void myConnection ()
	{
		try 
		{
		
			Class.forName("com.mysql.jdbc.Driver");
		    System.out.println ("Worked");
		    
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static void ConnectToMySQL ()
	
	{   myConnection();
		String host = "jdbc:mysql://localhost:8080/LMS";
		String username = "root";
		String password = "";
		try 
		{
			Connection connect = DriverManager.getConnection(host, username, password);
		 // System.out.println("fgd");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public static void main (String args [])

{
myConnection ();
ConnectToMySQL ();

}



}
