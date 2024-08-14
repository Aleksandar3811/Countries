package bg.softUni.Countries.web;


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

        String time=countryService.getCurrentTime();


        model.addAttribute("time",time);
        if (countryService.getMostCommentedCountry()!=null){
            model.addAttribute("commentedCountry", countryService.getMostCommentedCountry());

        }

        return "index";
    }

    @GetMapping("about")
    public ModelAndView about() {

        return new ModelAndView("about");



    }
}
