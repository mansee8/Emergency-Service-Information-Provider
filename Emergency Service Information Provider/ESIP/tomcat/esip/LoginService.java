package esip;

import java.sql.*;

public class LoginService extends Object {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/esip";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	/** Creates new LoginService */
	public LoginService() {
	}

	/** This is the SOAP exposed method */
	public String loginUser(String username, String password) {
		String reply = "";	

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "SELECT * FROM user_info WHERE login_id = '"
					+ username + "' AND password = '" + password + "';";
			rs = stmt.executeQuery(sql);

			if (rs.next())
				reply = "Authenticated";
			else
				reply = "Invalid Login";
		} catch (SQLException se) {
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

		return reply;
	}
}