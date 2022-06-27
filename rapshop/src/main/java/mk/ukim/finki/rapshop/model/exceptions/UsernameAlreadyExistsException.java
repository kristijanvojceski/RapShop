package mk.ukim.finki.rapshop.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String username){
        super(String.format("User with username: %s already exist",username));
    }

}
