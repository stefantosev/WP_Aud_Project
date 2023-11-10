package mk.ukim.finki.wp1.web.servlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp1.model.User;

import java.io.IOException;


//ke precekuva baranje i ke gi obraboti

@WebFilter
public class LoginFilter implements Filter {

    //login da ni bide inicijalna strana drugite da se zastiteni
    //prven na login da ne nosi


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //filter chain e veriga na filtri se preprakja do drugi filtri ako e potrebno

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        User user = (User)request.getSession().getAttribute("user");
        String path = request.getServletPath();

        if(!"/login".equals(path)  && !"/main.css".equals(path) && user == null){   //ako ne e login da ne ne nosi na drugite
            response.sendRedirect("/login");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
