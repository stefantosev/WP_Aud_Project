package mk.ukim.finki.wp1.repository;


import mk.ukim.finki.wp1.bootstrap.DataHolder;
import mk.ukim.finki.wp1.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository  //se anotira za dependency
public class InMemoryUserRepo {
    //konekcija so baza
    //da gi prebaruvame po ime

    public Optional<User>  findByUsername(String username){
        return DataHolder.users.stream().filter(u -> u.getUsername().equals(username)).findFirst();  //da go zeme samo prviot ako ima
    }

    public Optional<User> findByUsernameAndPass(String username, String password){
        return DataHolder.users.stream().filter(u->u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst();
    }

    public User saveOrUpdate(User user){
            DataHolder.users.removeIf(u-> u.getUsername().equals(user.getUsername()));
            DataHolder.users.add(user);

            return user;
    }

    public void delete(String username){
        DataHolder.users.removeIf(u->u.getUsername().equals(username));
    }
}
