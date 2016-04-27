package DB;

import Mode.User;

import java.sql.*;
import java.text.ParseException;

/**
 * 对数据库中的user表的增删改查操作
 * Created by ff on 16-3-9.
 */
public class UserDao {
    //获取数据库连接
    private Connection conn = DBUntil.getConnection();

    /**
     * 增加一个user
     * @param user　　user类
     * @return true 添加成功，false添加失败
     */
    public boolean adduser(User user){
        String sql = "insert into user(userid,username,password,age,sex,birthday,home,picture)"+
                "values(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,user.getUserid());
            ptmt.setString(2,user.getUsername());
            ptmt.setString(3,user.getPassword());
            ptmt.setString(4,user.getAge());
            ptmt.setString(5,user.getSex());
            //util.Date转换为sql.Date类型
            ptmt.setDate(6, new Date(user.getBirthday().getTime()));
            ptmt.setString(7,user.getHome());
            ptmt.setString(8,user.getPicture());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 删除一个用户
     * @param userid 用户ｉｄ
     * @return　true添加成功　false添加失败
     */
    public boolean deluser(String userid){
        String sql = "delete from user where userid=? ;";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,userid);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;}

    /**
     * 传入新用户的更改的信息，如果未更改则传入null
     * @param user　用户类
     * @return　true修改成功　false修改失败
     */
    public boolean moduser(User user){return true;}

    /**
     * 查询用户是否存在,
     * @param userid 传入用户账号，int型数据
     * @return
     */
    public ResultSet finduserid(String userid){
        String sql = "select userid,username,status,picture"+
                " from user where userid = ? ;";
        ResultSet rs = null;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,userid);
            rs = ptmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;}

    /**
     * 查询用户是否存在 模糊查找
     * @param username　传入用户名
     * @return
     */
    public ResultSet findusername(String username){
        String sql = "select userid,username,age,sex"+
                " from user where username like ? ;";
        ResultSet rs = null;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,username);
            rs = ptmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;}

    /**
     * 检测用户是否存在
     * @param userid　用户账号
     * @return　存在返回true 不存在返回false
     */
    public boolean checkuser(String userid){
        String sql = "select count(*) from user where userid=?";
        int count=0;
        PreparedStatement ptmt = null;
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,userid);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                count=rs.getInt("count(*)");
            }
            System.out.println("count="+ count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(count==0) {
            return false;
        }else {
            return true;
        }
    }

    /**
     * 更改用户的登录状态
     * @param userid　用户名
     * @param status 用户的登录状态，1为登录，0为未登录．
     */
    public void changuserstatus(String userid,int status){
        String sql = "UPDATE user SET status=? WHERE userid =? ;" ;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1,status);
            ptmt.setString(2,userid);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户的登录状态
     * @param userid　用户ｉｄ
     * @return 用户在线返回true，否则返回false
     */
    public boolean getuserstatus(String userid){
        String sql = "select status from user where userid=?";
        int status=0;
        PreparedStatement ptmt = null;
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,userid);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                status=rs.getInt("status");
            }
            System.out.println("status="+ status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(status == 1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 检查用户密码，登录状态
     * @param userid　用户id
     * @param password　用户密码
     * @return success表示登录成功，否则返回登录失败的原因
     */
    public String checkuserpw(String userid,String password){
        String sql = "select * from user where userid=?";
        int status=0;
        String realpw = null;
        PreparedStatement ptmt = null;
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,userid);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                status=rs.getInt("status");
                realpw = rs.getString("password");
            }
            System.out.println("status="+ status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //判断登录是否成功
//        System.out.println(realpw + "和"+password);
        if(realpw.equals(password)){
            if(status==0){
                //未登录,更改登录状态,
                changuserstatus(userid,1);
                return "success";
            }else {
                return "user-online";
            }
        }else {
            return "password-error";
        }
    }

    /**
     * 创建一张用户好友的表，表名用userid+friends 命名
     * @param userid
     * @return 　true表示添加成功，false表示，需要添加的用户不存在
     */
    public void createFriendList(String userid){
        String sql = "CREATE TABLE "+userid+"friends" +"(userid VARCHAR(20) PRIMARY KEY ,username VARCHAR(20),status INT DEFAULT 0, picture VARCHAR(20));";
        System.out.println("sql语句开始执行"+sql);
        try {
            //表操作用statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("sql语言:执行结束" + sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向一张好友表中添加好友
     * @param friendtable　好友表的表名
     * @param userid　好友ｉｄ
     */
    public boolean addfriend(String friendtable, String userid){
        //根据userid从用户表中查找用户是否存在
        ResultSet rs = finduserid(userid);
        String friendname=null;
        int status=0;
        String picture=null;
        try {
            while (rs.next()){
                friendname = rs.getString("username");
                status = rs.getInt("status");
                picture = rs.getString("picture");
            }
            if(friendname.equals("") || friendname==null){
                System.out.println(userid+"用户不存在，添加好友失败");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("从user表中得到的信息:"+friendname+":"+status+":"+picture);
        //将得到的好友信息存储倒好友表中去
        String sql = "insert into " +friendtable+"(userid,username,status,picture) "+"values(?,?,?,?);";
        System.out.println("执行slq语句:" + sql);
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,userid);
            ptmt.setString(2,friendname);
            ptmt.setInt(3,status);
            ptmt.setString(4,picture);
            ptmt.execute();
            System.out.println("sql语句执行结束");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void delfriend(String friendtable,String userid){
        //删除好友列表中的一个好友
        String sql = "delete from " + friendtable + " where userid=?;";
        System.out.println("执行sql语句:" + sql);
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,userid);
            ptmt.execute();
            System.out.println("sql语句执行结束");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 改变用户表中图片名
     * @param userid
     * @param picture
     */
    public void changpicture(String picture,String userid){
        String sql = "UPDATE user SET picture=? WHERE userid =? ;" ;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,picture);
            ptmt.setString(2,userid);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public static void main(String[] args) throws ParseException {
        UserDao ud = new UserDao();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date date = sdf.parse("2015-11-11");

//        User user = new User("2","hh","ff","21","男",date,"火星","002.png");
//        ud.checkuser("333");
//        ud.adduser(user);
//        ud.createFriendList("2");
        ud.addfriend("111friends","333");
//        ud.delfriend("111friends","333");
    }
}
