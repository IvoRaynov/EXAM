package bg.exam.animal4me.web;
import bg.exam.animal4me.exception.ObjectNotFoundException;
import bg.exam.animal4me.model.dto.offer.AddOrEditOfferDTO;
import bg.exam.animal4me.model.dto.offer.SearchOfferDTO;
import bg.exam.animal4me.service.OfferService;
import bg.exam.animal4me.service.SpecieService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


import java.util.UUID;


@Controller
public class OfferController {

    private final OfferService offerService;
    private final SpecieService specieService;

    public OfferController(OfferService offerService,
                           SpecieService specieService) {
        this.offerService = offerService;
        this.specieService = specieService;
    }

    @GetMapping("/offers/all")
    public String allOffers(@PageableDefault(sort = "price", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable pageable, Model model) {
        model.addAttribute("offers", offerService.getAllOffers(pageable));
        return "offers";
    }

    @GetMapping("/offers/add")
    public String addOffer(Model model) {
        if (!model.containsAttribute("addOfferModel")) {
            model.addAttribute("addOfferModel", new AddOrEditOfferDTO());
        }
        model.addAttribute("species", specieService.getAllSpecies());

        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String addOffer(@Valid AddOrEditOfferDTO addOfferModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel",
                    bindingResult);
            return "redirect:/offers/add";
        }

        offerService.addOffer(addOfferModel, userDetails);

        return "redirect:/offers/all";
    }


    @GetMapping("/offers/search")
    public String searchQuery(@Valid SearchOfferDTO searchOfferDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("searchOfferModel", searchOfferDTO);
            redirectAttributes.addAttribute(
                    "org.springframework.validation.BindingResult.searchOfferModel",
                    bindingResult);
            return "offer-search";
        }
        if (!model.containsAttribute("searchOfferModel")) {
            model.addAttribute("searchOfferModel", searchOfferDTO);
        }
        if (!searchOfferDTO.isEmpty()) {
            model.addAttribute("offers", offerService.searchOffer(searchOfferDTO));
        }

        return "offer-search";
    }


    @PreAuthorize("isOwner(#uuid)")
    @DeleteMapping("/offers/{id}")

    public String deleteOffer(@PathVariable("id") UUID uuid) {
        offerService.deleteOfferById(uuid);

        return "redirect:/offers/all";
    }

    @GetMapping("/offers/{id}")
    public String getOfferDetail(@PathVariable("id") UUID uuid,
                                 Model model) {

        var offerDto =
                offerService.findOfferByUUID(uuid).
                        orElseThrow(() -> new ObjectNotFoundException("Offer with UUID " +
                                uuid + " not found!"));

        model.addAttribute("offer", offerDto);

        return "details";
    }

}