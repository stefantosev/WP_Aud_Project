package mk.ukim.finki.wp1.service;

import mk.ukim.finki.wp1.model.User;

public interface AuthenticationService {

    //da ovozmozi authentication

    User login(String username, String Password);

    User register(String username, String password, String repeatPass, String ime, String prezime);
}
