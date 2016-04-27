package Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 全局的监听器
 * Created by ff on 16-3-13.
 */
public class MyServletContext implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String initparam = servletContextEvent.getServletContext().getInitParameter("initparam");
        System.out.println("contextinitialized:" + initparam);
//        servletContextEvent.getServletContext().setAttribute("1","全局的属性对象");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        System.out.println("contextDestroyed");
    }
}
