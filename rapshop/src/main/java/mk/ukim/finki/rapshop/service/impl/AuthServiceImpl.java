package mk.ukim.finki.rapshop.service.impl;

import mk.ukim.finki.rapshop.model.User;
import mk.ukim.finki.rapshop.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.rapshop.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.rapshop.repository.UserRepository;
import mk.ukim.finki.rapshop.service.AuthService;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {

        if(username==null|| username.isEmpty()||password==null||password.isEmpty()){
            throw new InvalidArgumentsException();
        }

        return userRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(()-> new InvalidUserCredentialsException());

    }
}
