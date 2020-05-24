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

        public void SQL_Injection(HttpServletRequest request, Connection c) {

                String text = request.getParameter("text");

                Statement s = c.createStatement();

                String sql = "select * from table where 1 = 1";

                s.executeQuery(sql + " and a = '" + text + "'");

        }

        public void SQL_Injection_Fix(HttpServletRequest request, Connection c) {

                String text = request.getParameter("text");

                String sql = "select * from table where 1 = 1 and a = ?";

                PreparedStatement s = c.preparedStatement(sql);

                s.setString(1, text);

                s.executeQuery();

        }
}