package bg.softUni.Countries.web;

import bg.softUni.Countries.entity.Country;
import bg.softUni.Countries.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HomeController {
    private final CountryService countryService;

    public HomeController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("commentedCountry", countryService.getMostCommentedCountry());

        return "index";
    }

    @GetMapping("about")
    public ModelAndView about() {

        return new ModelAndView("about");



    }
}
