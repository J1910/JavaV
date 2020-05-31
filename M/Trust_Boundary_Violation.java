package MediumThreat;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Trust_Boundary_Violation
 *
 * [How to Fix]
 * ①入力情報のホワイトリスト検証
 */
public class Trust_Boundary_Violation {
    HashMap<String, String> map;

	/**
	 * メソッド名： badMethod.
	 * 問題点：ユーザの入力でパスに指定したファイルなどを操作可能
	 *
	 * @param ユーザ情報を保持したHttpリクエスト
	 */
	public void badMethod(HttpServletRequest req) {
		HttpSession session = req.getSession();

		if(!req.getParameter("user").isEmpty()) {
			session.setAttribute("user", req.getParameter("user"));
        }
    }

    public void goodMethod(HttpServletRequest req) {
		HttpSession session = req.getSession();

		if(!req.getParameter("user").isEmpty()) {
            String user = req.getParameter("user");
			user = map.get(user);
			session.setAttribute("user", user);
        }
    }

}
