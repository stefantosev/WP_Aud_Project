package mk.ukim.finki.wp1.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest request){
        request.getSession().invalidate(); //da nemame pristap kon sesijata pak
        return "redirect:/login";
    }
}
