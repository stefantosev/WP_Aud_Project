package mk.ukim.finki.wp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan   //da specifirime baranje so servleti (ne e defaulten)
@SpringBootApplication  //glavna klasa vo spring
public class Wp1Application {

    public static void main(String[] args) {
        SpringApplication.run(Wp1Application.class, args);
    }

}
