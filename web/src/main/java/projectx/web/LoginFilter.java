package projectx.web;


import projectx.service.AuthService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author proger
 * @since 5/26/15
 */
@WebFilter(urlPatterns = {"/secured/*"})
public class LoginFilter implements Filter {
    @EJB
    private AuthService service;

    public void doFilter(
            ServletRequest req,
            ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        String sessionId = request.getSession().getId();

        if (!service.isAuthorized(sessionId)) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/login.xhtml");
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    public void destroy() {
        // do nothing
    }
}
