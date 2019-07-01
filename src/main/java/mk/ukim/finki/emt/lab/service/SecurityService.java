package mk.ukim.finki.emt.lab.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}