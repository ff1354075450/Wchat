package Listener;

import DB.UserDao;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 一个session的监听器
 * Created by ff on 16-3-13.
 */
public class loginListener implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //添加session
        System.out.println("添加session:"+httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //当session删除是设置用户的登录状态
        UserDao ud = new UserDao();
        HttpSession session = httpSessionEvent.getSession();
        String userid;

        Integer usernumber = (Integer) session.getAttribute("usernumber");
        userid = (String) session.getAttribute("userid");
        System.out.println("删除session userid="+userid);
        ud.changuserstatus(userid, 0);


    }
}
