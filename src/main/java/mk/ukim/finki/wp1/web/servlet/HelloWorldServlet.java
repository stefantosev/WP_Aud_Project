package mk.ukim.finki.wp1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "hello", urlPatterns = "/hello") //mora da predademe url za da funkcionira i na koja porta ke se aktivira
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");  //gi zimame parametrite dadena na url
        String surname = req.getParameter("surname");

        if(name == null && surname == null){
            name = surname = " ";
        }

        PrintWriter writer  = resp.getWriter();
        writer.format("<html><head></head><body><h1>Hello %s %s!</h1></body></html>", name, surname);  //treba html da mu dademe za da raboti pa zatoa thymeleaf ke stavama
        writer.flush(); //go zapira writerot
    }
}
