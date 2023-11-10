package mk.ukim.finki.wp1.service.impl;


import mk.ukim.finki.wp1.model.User;
import mk.ukim.finki.wp1.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp1.model.exceptions.InvalidUserCredentialsExceprtions;
import mk.ukim.finki.wp1.model.exceptions.PassDoNotMatchException;
import mk.ukim.finki.wp1.repository.InMemoryUserRepo;
import mk.ukim.finki.wp1.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    //biznis logika

    private final InMemoryUserRepo userRepo;

    public AuthenticationServiceImpl(InMemoryUserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public User login(String username, String password) {
        if(username ==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepo.findByUsernameAndPass(username, password)   //ako ne se najavi uspesno
                .orElseThrow(InvalidUserCredentialsExceprtions::new); //mora vaka
    }

    @Override
    public User register(String username, String password, String repeatPass, String ime, String prezime) {
        if(username ==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }

        if(password.equals(repeatPass)){
            throw new PassDoNotMatchException();
        }

        //ako e se vo red se kreira instanca od user za da se registrira
        User user = new User(username, password, ime, prezime);

        return userRepo.saveOrUpdate(user);  //da go zacuvame
    }
}
