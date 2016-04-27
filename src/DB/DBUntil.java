package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 工具类，用于获取数据库连接
 * Created by ff on 16-3-9.
 */
public class DBUntil {
    //主机名，端口，数据库名称
    private static  String URL = "jdbc:mysql://127.0.0.1:3306/Wchat";
    private static String USER = "root";
    private static String PASSWORD = "admin";
    private static Connection conn = null;

    //静态代码块
    static {
        try {
            //加载驱动，这段代码实际是获得一个driver的类型类
            //并且动态加载(在运行时加载)
            Class.forName("com.mysql.jdbc.Driver");
            //获得连接,字符串最后部分设置数据库字符集
            String connstr = "jdbc:mysql://localhost/Wchat?user=root&password=admin&useUnicode=true&characterEncoding=utf8";
            conn = DriverManager.getConnection(connstr);
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){return conn;}

}
