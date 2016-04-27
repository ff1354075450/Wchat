package Mservlet;

import DB.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户删除好友列表里面的好友
 * Created by ff on 16-4-1.
 */
public class Delfriend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String friendid = request.getParameter("userid");
        HttpSession session = request.getSession();
        String usertable = (String) session.getAttribute("userid") + "friends";
        System.out.println("从浏览器中得到好友id 和好友表"+friendid+":"+usertable);

        UserDao ud = new UserDao();
        ud.delfriend(usertable,friendid);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
