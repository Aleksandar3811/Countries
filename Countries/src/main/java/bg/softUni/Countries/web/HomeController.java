package bg.softUni.Countries.web;

import bg.softUni.Countries.entity.Country;
import bg.softUni.Countries.service.CountryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;




@Controller
public class HomeController {
    private final CountryService countryService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");



    public HomeController(CountryService countryService) {
        this.countryService = countryService;

    }


    @GetMapping("/")
    public String index(Model model) {

        String time=countryService.getCurrentTime();
        //String time=dateFormat.format(new Date());

        model.addAttribute("time",time);
        model.addAttribute("commentedCountry", countryService.getMostCommentedCountry());

        return "index";
    }

    @GetMapping("about")
    public ModelAndView about() {

        return new ModelAndView("about");



    }
}
