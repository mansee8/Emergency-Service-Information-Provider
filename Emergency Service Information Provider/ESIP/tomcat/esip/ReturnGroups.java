package esip;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ReturnGroups extends Object {
	String reply = "UNCHANGED";

	public ReturnGroups() {
	}

	public String viewGroups(String loginId) throws Exception {
		try {
			File file = new File("D:\\tomcat7\\webapps\\esip\\esipgroups.xml");
			if (!file.exists()) {
					file.createNewFile();
				}
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			Element results = doc.createElement("Results");
			doc.appendChild(results);

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/esip", "root", "");

			ResultSet rs = con.createStatement().executeQuery(
					"select * from group_info");

			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();

			System.out.println("colCount: " + colCount);

			while (rs.next()) {
				Element row = doc.createElement("group");
				results.appendChild(row);

				// String columnName = rsmd.getColumnName(i);
				// System.out.println("colname: "+columnName);
				// Object value = rs.getObject(i);

				Element node1 = doc.createElement("groupname");
				node1.appendChild(doc.createTextNode(rs.getString("group_name")));
				row.appendChild(node1);

				System.out.println("value: " + rs.getString("group_name"));

				Element node2  = doc.createElement("createdby");
				node2.appendChild(doc.createTextNode(rs.getString("created_by")));
				row.appendChild(node2);

				System.out.println("value: " + rs.getString("created_by"));
			}
							
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			
			DOMSource domSource = new DOMSource(doc);
			
			System.out.println("Test1");

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");			
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "8");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer
					.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

			System.out.println("Test2");
			/*
			 * StringWriter sw = new StringWriter(); StreamResult sr = new
			 * StreamResult(sw); transformer.transform(domSource, sr);
			 * 
			 * 
			 * 
			 * System.out.println(sw.toString());
			 * System.out.println(sr.toString());
			 * 
			 * BufferedWriter out = new BufferedWriter(new
			 * FileWriter("..\\webapps\\esip\\esipgroups.xml"));
			 * out.write(sw.toString()); out.close();
			 */

			

			StreamResult result = new StreamResult(new FileWriter(file));
			transformer.transform(domSource, result);
			System.out.println("Test3");
			
			con.close();
			rs.close();

			reply = "Success";

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			reply = "FNFE: " + e;
			System.out.println("Exception: " + reply);
		}		 
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			reply = "PCE: " + pce;
			System.out.println("Exception: " + reply);
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			reply = "TFE: " + tfe;
			System.out.println("Exception: " + reply);
		}
		catch (Exception e) {
			reply = "Exception: " + e;
			System.out.println("Exception: " + reply);
			e.printStackTrace();
		}
		return reply;
	}
}