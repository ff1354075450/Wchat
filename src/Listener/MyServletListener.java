package Listener;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ff on 16-3-13.
 */
public class MyServletListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
//        System.out.println("requestdestroyed====");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
//        String closesession = servletRequestEvent.getServletRequest().getParameter("closesession");
//        //如果得到closession为closesession那么设置超时时间为１秒，差不多就是立即超时
//        if (closesession.equals("closesession")){
//            HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
//            HttpSession session = request.getSession();
//            session.setMaxInactiveInterval(1);
//
//        }
    }
}
