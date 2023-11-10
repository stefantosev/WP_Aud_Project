package mk.ukim.finki.wp1.model;

import lombok.Data;
import org.springframework.stereotype.Component;



@Data
public class User {
   private String username;
   private String password;
   private String ime;
   private String prezime;

    public User(String username, String password, String ime, String prezime) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
    }
}
