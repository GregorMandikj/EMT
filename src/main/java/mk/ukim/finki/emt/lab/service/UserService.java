package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.User;
import mk.ukim.finki.emt.lab.web.LoginDTO;

public interface UserService {
    void save(User user);

    void register(LoginDTO loginDTO);

    User findByUsername(String username);
}