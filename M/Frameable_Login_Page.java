package MediumThreat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * FrameableLoginPage
 *
 * [How to Fix]
 * ①状況に応じてX-FRAME-OPTIONSを指定する
 */
public class Frameable_Login_Page {

	/**
	 * メソッド名： badMethod.
	 * 問題点：X-Frame-OPTIONSを指定していない
	 *
	 * @param ユーザ情報を保持したHttpリクエスト
	 */


	public void badMethod(HttpServletRequest req,HttpServletResponse repo) throws IOException {
        if(isLogin(req)) {
            doSomething(req.getSession());
        }
        PrintWriter out = repo.getWriter();
	}

	/**
	 * メソッド名： goodMethod.
	 * 修正：X-FRAME-OPTIONSを指定する
	 *
	 * @param ユーザ情報を保持したHttpリクエスト
	 * @throws IOException
	 */
	public void goodMethod(HttpServletRequest req,HttpServletResponse repo) throws IOException {
        if(isLogin(req)) {
            doSomething(req.getSession());
        }
        repo.addHeader("X-Frame-Options", "DENY");
        PrintWriter out = repo.getWriter();

	}

    private void doSomething(HttpSession newSession) {
		newSession.setAttribute("user",username);
        newSession.setAttribute("role",getRole(username));
    }

    private boolean isLogin(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String userID = req.getParameter("userid");
        String userPass = req.getParameter("userpass");
        req.setCharacterEncoding("Shift-JIS");

        return userID.equals("admin") && userPass.equals("pass");
    }

}
