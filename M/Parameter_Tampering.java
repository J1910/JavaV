package MediumThreat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
/**
 * ParameterTampering
 *
 * [How to Fix]
 * ①ログインユーザの権限を検証する
 * ②ユーザの入力情報を直接SQLクエリに連結しない
 */
public class Parameter_Tampering {
    /**
     * メソッド名： badMethod.
     * 問題点：リクエスト値が改ざんされると任意のユーザ情報にアクセスできる
     *
     * @param ユーザ情報を保持したHttpリクエスト
     */
    public void badMethod(HttpServletRequest req) {

        String userID = req.getParameter("userid");
        try (Connection connection = DriverManager.getConnection("url", "id", "password")) {
            PreparedStatement st = connection.prepareStatement("SELECT mail FROM users WHERE id = ?");
            st.setString(1, userID);
            ResultSet rs = st.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * メソッド名： goodMethod1.
     * 修正：ユーザの入力情報を直接SQLクエリに連結しない
     *
     * @param ユーザ情報を保持したHttpリクエスト
     */
    public void goodMethod1(HttpServletRequest req) {


        String userID = req.getParameter("userid");
        HashMap<String, String> map = new HashMap<String, String>();

        try (Connection connection = DriverManager.getConnection("url", "id", "password")) {
            PreparedStatement st = connection.prepareStatement("SELECT mail FROM users WHERE id = ?");
            st.setString(1, map.get(req.getParameter("accountId")));
            ResultSet rs = st.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
