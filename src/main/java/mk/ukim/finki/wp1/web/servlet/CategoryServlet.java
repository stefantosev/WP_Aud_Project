package mk.ukim.finki.wp1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp1.model.Category;
import mk.ukim.finki.wp1.repository.InMemoryCategoryRepository;
import mk.ukim.finki.wp1.service.CategoryService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



//kako da se obraboti get i post baranje so nekoja html forma

@WebServlet(name = "category", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    //Update: soodvetna povrzanost na site metodi i raboti (aud2)
    private  final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService){
        this.categoryService  = categoryService;
    }

    //za prikaz na lista kategorija za sega so html pravime
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();  //da vratime nekoj odgovor

        //ako sakame da ispiseme ip na client
        String ip = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");

        writer.format("<html><head></head><body>");
        writer.format("<h1>USER INFO: <h1> </br>");
        writer.format("<h3>IP ADDRESS:</br> <p>%s</p></br>", ip);
        writer.format("CLIENT AGENT: </br> <p>%s</p><h3></br>", clientAgent);

        writer.format("<h1>Category List</h1> <ul>");
        categoryService.categories().forEach(r->{
                    if(r.getDescription() !=null && !Objects.equals(r.getDescription(), "")) {
                        writer.format("<li>%s ( %s )</li>", r.getName(), r.getDescription());
                    }
                    else{
                            writer.format("<li>%s</li>", r.getName());
                    }
                });
        writer.format("</ul>");

        //pravime forma za vnes
        writer.format("<h2>Add Category</h2>");
        writer.format("<form method='POST' action='/servlet/category'>"); //povikuvame post method vo forma prakjame potadoci
        writer.format("<label for='name'>Name:</label>");
        writer.format("<input id='name' type='text' name='name'/>");
        writer.format("<label for='desc'>Description:</label>");
        writer.format("<input id='desc' type='text' name='desc'/> <input type='Submit' value='Submit'/>");
        writer.format("</form></body></html>");
        writer.flush();
    }

    //za vnes u forma za kategorija za podatocite da se pratat
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String CategoryName = req.getParameter("name");
        String CategoryDesc = req.getParameter("desc");
        categoryService.create(CategoryName, CategoryDesc);  //da gi stavime u lista da kreirame
//        addCategory(new Category(CategoryName, CategoryDesc));  //povikuvame funkcijata za da go stavime
        resp.sendRedirect("/servlet/category"); //go redirectnue na stranata i vo liststa
    }
}
