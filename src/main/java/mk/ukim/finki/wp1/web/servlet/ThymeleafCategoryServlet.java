package mk.ukim.finki.wp1.web.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import mk.ukim.finki.wp1.service.CategoryService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


//Update: dopolnuvanje na nov servlet so thyhmeleaf mesto html (aud2)

//@Data  moze i so data da nema constructor
@WebServlet(name = "thymeleaf", urlPatterns = "/servlet/thymeleaf/category")
public class ThymeleafCategoryServlet extends HttpServlet {

    private final CategoryService categoryService; //da imame pristap do service slojot
    private final SpringTemplateEngine springTemplateEngine;

    //mora da instancirame konstruktor za final sekogas
    public ThymeleafCategoryServlet(CategoryService categoryService, SpringTemplateEngine springTemplateEngine) {
        this.categoryService = categoryService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //so jakarta servlet MORA
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                        .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);

        webContext.setVariable("ipAdress", req.getRemoteAddr()); //za da zememe ip adresata
        webContext.setVariable("clienAgent", req.getHeader("User-Agent"));

        webContext.setVariable("categories", this.categoryService.categories()); //da gi iscitame site kategorii od service slojot

        Integer userViews = (Integer) getServletContext().getAttribute("userViews");
        getServletContext().setAttribute("userViews", ++userViews);
        webContext.setVariable("userViews", getServletContext().getAttribute("userViews"));

        //mora html fileot da stoi stocno vo templates, se zima contextot i response writerot
        this.springTemplateEngine.process("categories.html", webContext, resp.getWriter()); //da se izrenderira thymeleaf templates
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String CategoryName = req.getParameter("name");
        String CategoryDesc = req.getParameter("desc");

        categoryService.create(CategoryName, CategoryDesc);  //da gi stavime u lista da kreirame
//        addCategory(new Category(CategoryName, CategoryDesc));  //povikuvame funkcijata za da go stavime
        resp.sendRedirect("/servlet/thymeleaf/category"); //go redirectnue na stranata i vo liststa

    }
}
