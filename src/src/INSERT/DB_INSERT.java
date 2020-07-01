package src.INSERT;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DB_INSERT extends HttpServlet{
	private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException,ServletException{
     response.setContentType("text/html; charset=UTF-8");
     PrintWriter out = response.getWriter();
       out.println("<html>");
       out.println("<head>");
       out.println("<title>INSERT</title>");
       out.println("</head>");
       out.println("<body>");
       out.println("<name=\"data\">");
       out.println("</body>");
       out.println("</html>");
    }

     protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException,ServletException{
       response.setContentType("text/html; charset=UTF-8");
       PrintWriter out = response.getWriter();
         out.println("<html>");
    	 out.println("<head>");
         out.println("<title>INSERT</title>");
    	 out.println("</head>");
    	 out.println("<body>");
         out.println("<name=\"data\">");

        //変数定義
        Connection conn = null;
        PreparedStatement ps = null;

        //DB接続情報を設定する
        String path = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";  //接続パス
        String id = "root";    //ログインID
        String pw = "sep2019.1173-nakaono-H10.1210";  //ログインパスワード

        //SQL文を定義する
        String data = request.getParameter("data"); //前画面で入力した値
        String sql = "INSERT INTO helloworld values (?)";
     try {
        //JDBCドライバをロードする
        Class.forName("com.mysql.cj.jdbc.Driver"); //JDBCドライバクラス

        //DBへのコネクションを作成する
        conn = DriverManager.getConnection(path, id, pw);

        //実行するSQL文とパラメータを指定する
        ps = conn.prepareStatement(sql);
        ps.setString(1, data);
        //INSERT文を実行する
        int i = ps.executeUpdate();

        //処理件数を表示する
        System.out.println("結果：" + i);
        out.println("結果：" + i);

        //コミット
        conn.commit();

        } catch (Exception ex) {
            //例外発生時の処理
            try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}       //ロールバックする
            ex.printStackTrace();  //エラー内容をコンソールに出力する
        } finally {
            //クローズ処理
            if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
            if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
        }
     out.println("</body>");
     out.println("</html>");
    }
}
