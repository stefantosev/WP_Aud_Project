package mk.ukim.finki.wp1.web.controllers;


import org.springframework.ui.Model;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp1.model.User;
import mk.ukim.finki.wp1.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp1.model.exceptions.PassDoNotMatchException;
import mk.ukim.finki.wp1.service.impl.AuthenticationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthenticationServiceImpl authService;


    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){  //required false ???
       if(error!=null && !error.isEmpty()){
           model.addAttribute("hasError", true);
           model.addAttribute("error", error);
       }

        return "register";
    }

    @PostMapping
    //gi zimame od register parametrite
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPass,
                           @RequestParam String ime,
                           @RequestParam String prezime){

        try{
            this.authService.register(username,password,ime, repeatPass,prezime);
            return "redirect:/login";

        }catch(PassDoNotMatchException | InvalidArgumentsException ex){
            return "redirect:/register?error=" + ex.getMessage();
        }

    }
}
