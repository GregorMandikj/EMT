package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody LoginDTO loginDTO) {
        userService.register(loginDTO);
    }

}
