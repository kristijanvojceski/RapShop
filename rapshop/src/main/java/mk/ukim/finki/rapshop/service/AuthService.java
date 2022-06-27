package mk.ukim.finki.rapshop.service;

import mk.ukim.finki.rapshop.model.User;

public interface AuthService {

    User login (String username,String password);

}
