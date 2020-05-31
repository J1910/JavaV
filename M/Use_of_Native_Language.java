package MediumThreat;

import javax.servlet.http.HttpServletRequest;

import com.sun.jna.Library;
import com.sun.jna.Native;


/**
 * Use_of_Native_Language
 *
 * [How to Fix]
 * ①Java APIを用いて実装する
 */
public class Use_of_Native_Language {
	private native int hardwareIO (char[] input);

	/**
	 * メソッド名： badMethod.
	 * 問題点：ユーザの入力でパスに指定したファイルなどを操作可能
	 *
	 * @param ユーザ情報を保持したHttpリクエスト
	 */

	public void badMethod(HttpServletRequest req) {
        String hwInput = req.getParameter("name");

        char[] input = hwInput.toCharArray();
        int result = hardwareIO( input );
	}

    public void goodMethod(HttpServletRequest req) {
        String hwInput = req.getParameter("name");

        char[] input = hwInput.toCharArray();
        if(input[0] == 'a'){
            int result = hardwareIO( input );
        }
	}

}
