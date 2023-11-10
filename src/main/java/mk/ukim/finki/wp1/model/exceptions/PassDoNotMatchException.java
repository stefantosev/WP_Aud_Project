package mk.ukim.finki.wp1.model.exceptions;

public class PassDoNotMatchException extends RuntimeException {
    public PassDoNotMatchException(){
        super("Password do not match");
    }
}
