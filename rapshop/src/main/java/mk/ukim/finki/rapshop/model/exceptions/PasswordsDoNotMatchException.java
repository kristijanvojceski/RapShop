package mk.ukim.finki.rapshop.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{

    public PasswordsDoNotMatchException(){
        super("Password do not match exception");
    }

}
