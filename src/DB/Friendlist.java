package DB;

import Mode.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * javabean 类根据userid获取好友列表
 * Created by ff on 16-3-31.
 */
public class Friendlist {
    public Friendlist(){

    }

    public List<User> getlist(String userid){
        List<User> list = new ArrayList<User>();
        Connection conn = DBUntil.getConnection();
        String sql = "select userid,username,picture from "+userid+"friends";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            UserDao ud = new UserDao();
            while (rs.next()){
                User user = new User();
                user.setUserid(rs.getString("userid"));
                user.setUsername(rs.getString("username"));
                user.setPicture(rs.getString("picture"));
                if(ud.getuserstatus(user.getUserid())){
                    user.setStatus(1);
                }else {
                    user.setStatus(0);
                }
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args){
        Friendlist friendlist = new Friendlist();
        List<User> list =  friendlist.getlist("111");
        System.out.print(list.get(3).getUserid()+":"+list.get(3).getUsername()+":"+ list.get(3).getPicture());
    }
}

