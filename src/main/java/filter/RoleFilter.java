package filter;

import model.CMSUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class RoleFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        CMSUser user = (CMSUser) session.getAttribute("user");

        if(!user.isAdmin()){
            this.context.log("Unauthorized access request. User with Mentor access tried to access Admin features");
            response.sendRedirect("/dashboard");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

