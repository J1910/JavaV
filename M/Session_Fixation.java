import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Session_Fixation extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getSession(true).invalidate(); //<-対策
        HttpSession session = req.getSession();
        String userKey = "loggedInUser";
        User user = (User) session.getAttribute(userKey);
        if (user == null) {
            session.setAttribute(userKey, user);
        }
        req.changeSessionId();
    }

}
