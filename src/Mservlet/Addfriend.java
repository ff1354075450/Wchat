package Mservlet;

import DB.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 给好友列表添加好友
 * Created by ff on 16-3-31.
 */
public class Addfriend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String friendid = request.getParameter("userid");
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid") ;
        System.out.println("从浏览器中得到好友id 和好友表"+friendid+":"+userid + "friends");
        boolean result=false;

        //往指定表中添加数据
        UserDao ud = new UserDao();
        if(!ud.addfriend( userid + "friends",friendid)){
            System.out.println(friendid+ " 添加失败");
        }else {
            System.out.println(friendid+"添加成功");
            result=true;
        }

        if(!ud.addfriend(friendid+"friends",userid)){
            System.out.println(userid+"失败添加倒"+friendid);
            result = false;
        }else {
            System.out.println("添加成功");
        }


        request.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        if(result){
            pw.write("success");
        }else {
            pw.write("failed");
        }
        pw.flush();
        pw.close();

    }

}
