package mk.ukim.finki.emt.lab.web;


import mk.ukim.finki.emt.lab.model.Role;
import mk.ukim.finki.emt.lab.model.User;
import mk.ukim.finki.emt.lab.repository.RoleRepository;
import mk.ukim.finki.emt.lab.service.SecurityService;
import mk.ukim.finki.emt.lab.service.UserService;
import mk.ukim.finki.emt.lab.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    public void loadData()
    {
        Role r1 = new Role();
        r1.setName("ROLE_USER");

        roleRepository.save(r1);

        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        Set<Role> roles = new HashSet<Role>();
        roles.add(r1);
        user.setRoles(roles);
        userService.save(user);

        Role r2 = new Role();
        r2.setName("ROLE_ADMIN");

        roleRepository.save(r2);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        roles.add(r2);
        admin.setRoles(roles);
        userService.save(admin);
    }

//    @Autowired
//    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

//        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}