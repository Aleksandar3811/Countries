package bg.softUni.Countries.web;

import bg.softUni.Countries.entity.Level;
import bg.softUni.Countries.entity.dto.UserLoginDTO;
import bg.softUni.Countries.entity.dto.UserRegisterDTO;
import bg.softUni.Countries.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        if (!model.containsAttribute("registerData")) {
            model.addAttribute("registerData", new UserRegisterDTO());
        }

        model.addAttribute("levels", Level.values());

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO data,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

            // handle errors
            return "redirect:register";
        }

        userService.register(data);

        // register user
        return "redirect:/users/login";
    }


    @GetMapping("/login")
    public ModelAndView viewLogin() {

        ModelAndView mv=new ModelAndView("login");
        mv.addObject("loginData",new UserLoginDTO());
        return mv;
    }
    @GetMapping("users/login-error")
    public ModelAndView viewLoginError() {
        ModelAndView modelAndView = new ModelAndView("login");

        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("loginData", new UserLoginDTO());

        return modelAndView;
    }
    @GetMapping("/profile")
    public ModelAndView profile(){
        ModelAndView mvn=new ModelAndView("profile");
        mvn.addObject("profileData",userService.getProfileData());
        return mvn;
    }



}
