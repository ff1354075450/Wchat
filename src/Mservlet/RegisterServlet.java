package Mservlet;

import DB.UserDao;
import Mode.User;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * １.将用户注册的数据保存倒数据库中
 * ２.实时检查并返回用户输入的用户名是否已经存在
 * Created by ff on 16-3-8.
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
        //获取客户端信息
        String userid = (String) request.getParameter("userid");
        String username=(String) request.getParameter("username");
        String password= (String) request.getParameter("password");
        String sex = request.getParameter("sex");//1为男2为女
        if(sex.equals("1")) {
            sex = "男";
        }else {
            sex = "女";
        }
        String age = (String) request.getParameter("age");
        String birthday = (String) request.getParameter("birthday");
        String home=(String) request.getParameter("home");




        //创建用户，添加用户倒数据库中
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        判断日期是否正确　　　未做
//        判断用户名是否正确　　　　未做
//        用户头像添加　　未做
        Date bdate = null;
        try {
            bdate = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user = new User(userid,username,password,age,sex,bdate,home,null);
        UserDao ud = new UserDao();

        //检查用户id是否存在
        if(ud.checkuser(userid)){
            System.out.println("用户存在");
            response.sendRedirect("/register-fail.jsp");
        }else{
            System.out.println("用户不存在");
            ud.adduser(user);
            ud.createFriendList(userid);
            //转发
            request.getRequestDispatcher("/register-success.jsp").forward(request,response);

        }

    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      doPost(request,response);
//    }
}
