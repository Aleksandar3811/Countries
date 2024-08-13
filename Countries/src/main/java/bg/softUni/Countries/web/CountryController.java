package bg.softUni.Countries.web;

import bg.softUni.Countries.entity.CategoryType;
import bg.softUni.Countries.entity.Level;
import bg.softUni.Countries.entity.dto.AddCountryDto;
import bg.softUni.Countries.entity.dto.CountryShortInfoDto;
import bg.softUni.Countries.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class CountryController {
    private final CountryService countryService;



    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public String routes(Model model) {
        List<CountryShortInfoDto> routes = countryService.getAll();

        model.addAttribute("allCountries", routes);

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
            @Valid AddCountryDto data,
            @RequestParam("gpxCoordinates") MultipartFile file
    ) throws IOException {


        countryService.add(data, file);

        return "redirect:/add-country";
    }


}
