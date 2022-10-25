package MyPastebinClone.controller;

import MyPastebinClone.model.DTOModel;
import MyPastebinClone.model.UserModel;
import MyPastebinClone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String viewWellcomePage(){
        return "wellcomePage";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginPage";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        DTOModel user = new DTOModel();
        model.addAttribute("user", user);
        return "registerPage";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") DTOModel user,
                               BindingResult result,
                               Model model){
        UserModel existing = userService.findByUserName(user.getUserName());
        if (existing != null) {
            result.rejectValue("userName", null);
            return "errorPage";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "registerPage";
        }
        userService.saveUser(user);
        return "loginPage";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<DTOModel> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "usersListPage";
    }
}