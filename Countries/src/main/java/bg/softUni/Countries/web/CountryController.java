package bg.softUni.Countries.web;

import bg.softUni.Countries.entity.CategoryType;
import bg.softUni.Countries.entity.Level;
import bg.softUni.Countries.entity.dto.AddCountryDto;
import bg.softUni.Countries.entity.dto.CountryShortInfoDto;
import bg.softUni.Countries.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



import java.util.List;

@Controller
public class CountryController {
    private final CountryService countryService;



    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public String routes(Model model) {
        List<CountryShortInfoDto> countries = countryService.getAll();

        model.addAttribute("allCountries", countries);

        return "countries";
    }
    @GetMapping("add-country")
    public ModelAndView addRoute() {
        ModelAndView modelAndView = new ModelAndView("add-country");

        modelAndView.addObject("country", new CountryShortInfoDto());
        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categoryTypes", CategoryType.values());

        return modelAndView;
    }
    @ModelAttribute("countryData")
    public AddCountryDto countryData() {
        return new AddCountryDto();
    }

    @PostMapping("/add-country")
    public String doAddRoute(
            @Valid AddCountryDto data
    )  {


        countryService.add(data);

        return "redirect:/add-country";
    }
    @GetMapping("country/{id}")
    public ModelAndView details(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("country-details");

        modelAndView.addObject("country", countryService.getDetails(id));

        return modelAndView;
    }
    @GetMapping("/countries/{category}")
    public ModelAndView getCountriesByCategory(@PathVariable CategoryType category) {


        String view = countryService.getView(category);

        ModelAndView modelAndView = new ModelAndView(view);

        modelAndView.addObject("countries", countryService.getCountryByCategory(category));

        return modelAndView;
    }



}
