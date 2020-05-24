import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Second_Order_SQL_Injection extends HttpServlet {
    Connection conn;
    String url;
    String user;
    String password;

    public void doGet(HttpServletRequest request, HttpServletResponse response){
    	try {
			PrintWriter out = response.getWriter();
			String name = request.getParameter("name");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT user FROM user where name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
			    String data = rs.getString("user");
				String sql2 = "SELECT * FROM product where user = " + data;
				Statement stmt = conn.createStatement();
				ResultSet rs2 = stmt.executeQuery(sql2);
			    while(rs2.next()) {
			    	out.println(rs2.getString(1));
			    }

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public void doGetFix(HttpServletRequest request, HttpServletResponse response){
    	try {
			PrintWriter out = response.getWriter();
			String name = request.getParameter("name");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT user FROM user where name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
			    String data = rs.getString("user");
				String sql2 = "SELECT " + data + " FROM product where user = ?";
			    PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			    pstmt2.setString(1, data);
			    ResultSet rs2 = pstmt.executeQuery();
			    while(rs2.next()) {
			    	out.println(rs2.getString(1));
			    }

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}