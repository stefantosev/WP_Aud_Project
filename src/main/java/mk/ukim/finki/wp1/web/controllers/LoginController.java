package mk.ukim.finki.wp1.web.controllers;


import lombok.AllArgsConstructor;
import mk.ukim.finki.wp1.model.User;
import mk.ukim.finki.wp1.model.exceptions.InvalidUserCredentialsExceprtions;
import mk.ukim.finki.wp1.service.AuthenticationService;
import mk.ukim.finki.wp1.service.impl.AuthenticationServiceImpl;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController  vrakja podatoci pr na get.requests lista na produkti vo json so angular
@AllArgsConstructor
@Controller   //go modificira modelot i vrakja nekoj view kon user
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationServiceImpl authService;

    @GetMapping  //mora za da handlea takov baranje
    //@RequestMapping(method = RequestMethod.GET, value = "")
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
                //Model da mu vrati ui na userot seta data na view ja stava vo modelot i preku html se pristapuvaat
        User user = null;
        try{
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);

            return  "redirect:/home";

        }catch(InvalidUserCredentialsExceprtions ex){
            model.addAttribute("hasError", true);
            model.addAttribute("error", ex.getMessage()); //da frli error i porakata da ispishe
            return "login";
        }
    }
}
