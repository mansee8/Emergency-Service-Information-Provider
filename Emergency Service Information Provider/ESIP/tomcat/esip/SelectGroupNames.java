package esip;

import java.io.*;

import org.w3c.dom.*;

import javax.xml.parsers.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class SelectGroupNames extends Object 
{	
	/** Creates new SoapService */
	public SelectGroupNames() {	}

	/** 
	This is the SOAP exposed method
	*/

	public String selectGroups(String fname, String lname, String city, String comments)
    {
		//Parse to XML

		try {
            /////////////////////////////
            //Creating an empty XML Document

            //We need a Document
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            ////////////////////////
            //Creating the XML tree

            //create the root element and add it to the document
            Element root = doc.createElement("RECORD");
            doc.appendChild(root);

            //create a comment and put it in the root element
            Comment comment = doc.createComment("Created by JAVA on values obtained from Android");
            root.appendChild(comment);

            //create child element, add an attribute, and add to root
            Element fnameChild = doc.createElement("FNAME");
            root.appendChild(fnameChild);
            //add a text element to the child
            Text fnameText = doc.createTextNode(fname);
            fnameChild.appendChild(fnameText);

			//create child element, add an attribute, and add to root
            Element lnameChild = doc.createElement("LNAME");
            root.appendChild(lnameChild);
            //add a text element to the child
            Text lnameText = doc.createTextNode(lname);
            fnameChild.appendChild(lnameText);

			//create child element, add an attribute, and add to root
            Element cityChild = doc.createElement("CITY");
            root.appendChild(cityChild);
            //add a text element to the child
            Text cityText = doc.createTextNode(city);
            fnameChild.appendChild(cityText);

			//create child element, add an attribute, and add to root
            Element commentsChild = doc.createElement("COMMENTS");
            root.appendChild(commentsChild);
            //add a text element to the child
            Text commentsText = doc.createTextNode(comments);
            fnameChild.appendChild(commentsText);

            /////////////////
            //Output the XML

            //set up a transformer
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            //create string from xml tree
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);

            String xmlString = sw.toString();
		
			File file = new File("XMLForm.xml");
			BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			bw.write(xmlString);

			

        } catch (Exception e) {
            System.out.println(e);
        }

		return "success";
	}
	public void viewData()
    {
		//return XML from file
	}	  
}