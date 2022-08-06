package bg.exam.animal4me.web;
import bg.exam.animal4me.service.SpecieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
public class BrandsController {
    private final SpecieService specieService;

    public BrandsController(SpecieService specieService) {
        this.specieService = specieService;
    }
    @GetMapping("/all")
    public String allBrands(Model model){
        model.addAttribute("brands", specieService.getAllSpecies());
        return "brands";
    }
}
