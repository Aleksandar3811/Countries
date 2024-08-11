package bg.softUni.Countries.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {

        return "index";
    }

    @GetMapping("about")
    public ModelAndView about() {

        return new ModelAndView("about");



    }
}
