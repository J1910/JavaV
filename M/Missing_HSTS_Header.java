import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Missing_HSTS_Header extends HttpServlet {
  public void doPostBad(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    response.setContentType("text/html; charset=UTF8");
    PrintWriter out = response.getWriter();
    request.setCharacterEncoding("Shift-JIS");
    //response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
    response.getWriter().println("{\"msg\":\"Failed.\"}");
  }


  public void doPostGood(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    response.setContentType("text/html; charset=UTF8");
    PrintWriter out = response.getWriter();
    request.setCharacterEncoding("Shift-JIS");
    response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
    response.getWriter().println("{\"msg\":\"Failed.\"}");
  }


}