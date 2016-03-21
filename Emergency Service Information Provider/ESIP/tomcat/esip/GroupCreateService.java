package esip;

import java.sql.*;

public class GroupCreateService extends Object {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/esip";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	/** Creates new GroupCreateService */
	public GroupCreateService() {}

	/** This is the SOAP exposed method */
	
	public String checkAvailibility(String groupName, String CreatedBy) {
		String reply = "UNCHANGED "+groupName;

		try {
			
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "SELECT * FROM group_info WHERE group_name = '"
					+ groupName + "';";
			rs = stmt.executeQuery(sql);

			if (rs.next())
				reply = "Group Name Not Available";
			else {
				/*stmt = null;
				conn.close();
			
				//Class.forName("com.mysql.jdbc.Driver");		
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();
				*/
				String sqlIns = "INSERT INTO group_info(group_name, created_by) VALUES ('"
						+ groupName + "', '" + CreatedBy + "')";

				int resflag = stmt.executeUpdate(sqlIns);

				if(resflag>0)
					reply = "Success";
				else
					reply = "Group Creation Failed";
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			reply="Reply From Exception 1: "+se;
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			reply="Reply From Exception 2: "+e;
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				reply="Reply From Exception 3: "+se;
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
				reply="Reply From Exception 4: "+se;
			}// end finally try
		}// end try

		return reply;
	}
}