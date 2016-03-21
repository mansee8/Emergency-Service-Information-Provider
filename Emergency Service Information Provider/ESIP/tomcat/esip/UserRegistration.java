package esip;
//STEP 1. Import required packages
import java.sql.*;

public class UserRegistration extends Object {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/esip";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	String reply="UNCHANGED";
	int resflag;
	/** Creates new SoapService */
	public UserRegistration() {
	}

	public String insertRecord(String recduser_name , String recdlogin_id , String recdpassword , String recdphn_number) 
		{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "SELECT * FROM user_info WHERE user_name = '"
					+ recduser_name + "';";
			rs = stmt.executeQuery(sql);

			if (rs.next())
				reply = "User-Name Not Available";
			else {

			String sqlIns = "INSERT INTO user_info(user_name, login_id, password, phn_number) VALUES ('"+ recduser_name + "', '" + recdlogin_id + "', '" + recdpassword + "', '" + recdphn_number + "')";
			
			System.out.println("Query: "+sqlIns);
			resflag = stmt.executeUpdate(sqlIns);
			}
		}catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		if (resflag > 0) {
			String reply = "Inserted";
			return reply;
		} else {
			String reply = "Operation Unsuccessful";
			return reply;
		}

	}// end main
}// end UserRegistration