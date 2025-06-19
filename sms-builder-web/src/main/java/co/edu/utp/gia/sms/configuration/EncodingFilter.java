package co.edu.utp.gia.sms.configuration;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.*;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    public void init(FilterConfig filterConfig) {}
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
    public void destroy() {}
}
