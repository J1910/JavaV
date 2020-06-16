package MediumThreat;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DangerousFileInclusion
 *
 * [How to Fix] ①ライブラリ用見込み入力情報を直接使用しない
 */
public class Dangerous_File_Inclusion {

	/**
	 * メソッド名： badMethod. 問題点：ユーザ入力をライブラリ読み込みに直接使用している
	 *
	 * @param ユーザ情報を保持したHttpリクエスト
	 */

	public void badMethod(HttpServletRequest req, HttpServletResponse resp) {
		// System.loadLibrary(req.getParameter("filePass"));
		String libName = req.getParameter("filePass");
		RequestDispatcher dispatcher = req.getRequestDispatcher(libName);
		dispatcher.include(req, resp);

	}

	/**
	 * メソッド名： goodMethod. 修正：ライブラリ用見込み入力情報を直接使用しない
	 *
	 * @param ユーザ情報を保持したHttpリクエスト
	 * @throws IOException
	 */
	public void goodMethod(HttpServletRequest req, HttpServletResponse resp) {
		String usertype = req.getParameter("usertype");
		String libName = "";
		if (usertype.equals("user")) {
			libName = "secLibrary.dll";
		} else {
			libName = "anonymousLib.dll";
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("C://Temp/" + libName);
		dispatcher.include(req, resp);

	}

}
