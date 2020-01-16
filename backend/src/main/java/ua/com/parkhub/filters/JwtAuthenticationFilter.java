package ua.com.parkhub.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ua.com.parkhub.exceptions.InvalidTokenException;
import ua.com.parkhub.security.JwtUtil;
import ua.com.parkhub.values.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends GenericFilterBean {


    private JwtUtil jwtUtil;

    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        try {
            String token = ((HttpServletRequest) request).getHeader(Constants.TOKEN_HEADER);

            if ((token != null) && (!token.isEmpty())) {
                Authentication auth = jwtUtil.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);

                ((HttpServletResponse) response).setHeader(Constants.TOKEN_HEADER, jwtUtil.refresh(token));
            }
        } catch (InvalidTokenException e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        filterChain.doFilter(request,response);
    }
}

