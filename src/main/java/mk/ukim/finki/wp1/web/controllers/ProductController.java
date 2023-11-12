package mk.ukim.finki.wp1.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {


    @GetMapping
    public String getProductsPage(){
        return  "products";
    }
}
