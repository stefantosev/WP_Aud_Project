package mk.ukim.finki.wp1.web.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp1.model.User;
import mk.ukim.finki.wp1.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp1.model.exceptions.InvalidUserCredentialsExceprtions;
import mk.ukim.finki.wp1.service.impl.AuthenticationServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/servlet/login")
public class LoginServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final AuthenticationServiceImpl authenticationService;


    public LoginServlet(SpringTemplateEngine springTemplateEngine, AuthenticationServiceImpl authenticationService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);

        //da se izgenerira html
        springTemplateEngine.process("login.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);

       String username = req.getParameter("username");
       String password = req.getParameter("password");

       User user = null;

       try {
            user = authenticationService.login(username, password);
       }catch (InvalidUserCredentialsExceprtions | InvalidArgumentsException ex){
           webContext.setVariable("hassError", true);
           webContext.setVariable("error", ex.getMessage());
           springTemplateEngine.process("login.html", webContext, resp.getWriter()); //ako ima error da go vrati na login stranata pak
       }

        if (user != null) {
            req.getSession().setAttribute("user", user);  //da se izgenerira sesija za toj korisnik
            resp.sendRedirect("/servlet/thymeleaf/category"); //da go redirecctne na category stranata
        }

    }
}
