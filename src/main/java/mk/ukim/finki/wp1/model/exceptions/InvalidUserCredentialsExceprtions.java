package mk.ukim.finki.wp1.model.exceptions;

public class InvalidUserCredentialsExceprtions  extends RuntimeException{
     public InvalidUserCredentialsExceprtions(){ //koa nasleduame sekogas usper konstruktor
         super("Invalid user credentials");
     }
}
