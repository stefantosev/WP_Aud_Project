package mk.ukim.finki.wp1.bootstrap;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import lombok.extern.apachecommons.CommonsLog;
import mk.ukim.finki.wp1.model.Category;
import mk.ukim.finki.wp1.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component  //za da bide singleton da se instancira na pocetok pri start
public class DataHolder {
    public static List<Category> categories = new ArrayList<>(); //in memory koga ke restartirame brise
    public static List<User> users = new ArrayList<>();

    @PostConstruct    //da se povika odma posle instancata
    public void init() throws ServletException {   //ednas se povikue za start na spring
        this.categories = new ArrayList<>();
        this.categories.add(new Category("Software", "Softwer category"));
        this.categories.add(new Category("Books", "Books category"));
        this.categories.add(new Category("Movies", "Movies category"));


        this.users.add(new User("walkorion", "st", "Stefan", "Tosev"));
        this.users.add(new User("hexodian", "tm", "Toso", "Macorot"));
    }
}
