package Mservlet;

import DB.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ff on 16-2-29.
 */
//@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");


        session.setAttribute("userid", userid);
        session.setAttribute("password", password);
        session.setAttribute("picture","./image/"+userid+".png");

        System.out.println("当前回话用户:"+ userid);
        //检查用户是否存在
        UserDao ud = new UserDao();
        System.out.println("userid");
        if(ud.checkuser(userid)){
            //用户存在

            String result = ud.checkuserpw(userid,password);
            //根据不同的登录状态返回信息给页面
            //user-online标识用户已经在在线了，这边应该强制下线前一个用户，但未进行操作
            if(result.equals("success") || result.equals("user-online")) {
                System.out.println("登录成功");
                request.getRequestDispatcher("/Login-success.jsp").forward(request,response);
            }else {
                System.out.println("登录失败的原因："+ result);
                response.sendRedirect("/Login-failure.jsp");
            }
        }else {
            //用户不存在
            System.out.println("登录失败用户不存在");
            response.sendRedirect("/Login-failure.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
