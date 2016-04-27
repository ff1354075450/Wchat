package MFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 过滤器，用户登录之前只允许访问登录截面和登录服务器。
 * Created by ff on 16-2-29.
 */
public class LoginFilter implements Filter {
    private String[] unloginpath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String config = filterConfig.getInitParameter("unlonginpath");
        unloginpath = config.split(";");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        /**
         *过滤网址，
         * 过滤有session的连接
         */
        for (int i=0;i<unloginpath.length;i++) {
            if(unloginpath[i] == null || "".equals(unloginpath[i])) continue;
            if (request.getRequestURI().indexOf(unloginpath[i]) != -1) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        if(session.getAttribute("userid") != null){
            filterChain.doFilter(request,response);
            return;
        }else {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}
