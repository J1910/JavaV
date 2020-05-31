import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Improper_Restriction_of_Stored_XXE_Ref {
    public List<String> badMethod1(HttpServletRequest req) throws XMLStreamException {

		Connection connection;
		try {
			connection = DriverManager.getConnection("url", "id", "password");
			String sql = "SELECT * FROM Brob WHERE data = ?";
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setString(1, req.getParameter("xml"));

			ResultSet resultSet = pre.executeQuery();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the XML filename: ");
            String xmlName = scanner.next();
            
            List<String> list = new ArrayList<String>();
        
            XMLInputFactory factory = XMLInputFactory.newFactory();
            factory.setProperty(factory.IS_SUPPORTING_EXTERNAL_ENTITIES,true);
            factory.setProperty(factory.SUPPORT_DTD,true);
            XMLStreamReader read = factory.createXMLStreamReader(new StringReader(resultSet.getString("xml")));
        
            while(read.hasNext()) {
                list.add(read.getText());
                read.next();
            }
        } catch (SQLException e) {
			e.printStackTrace();
		}
        
    
        return list;
    
    }

    public List<String> goodMethod(HttpServletRequest req) throws XMLStreamException {

		Connection connection;
		try {
			connection = DriverManager.getConnection("url", "id", "password");
			String sql = "SELECT * FROM Brob WHERE data = ?";
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setString(1, req.getParameter("xml"));

			ResultSet resultSet = pre.executeQuery();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the XML filename: ");
            String xmlName = scanner.next();
            
            List<String> list = new ArrayList<String>();
        
            XMLInputFactory factory = XMLInputFactory.newFactory();
            factory.setProperty(factory.IS_SUPPORTING_EXTERNAL_ENTITIES,false);
            factory.setProperty(factory.SUPPORT_DTD,false);
            XMLStreamReader read = factory.createXMLStreamReader(new StringReader(resultSet.getString("xml")));
        
            while(read.hasNext()) {
                list.add(read.getText());
                read.next();
            }
        } catch (SQLException e) {
			e.printStackTrace();
		}
        
    
        return list;
    
    }

}