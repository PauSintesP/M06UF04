package com.accesadades.botiga.Controller;

import com.accesadades.botiga.Model.Llibre;
import com.accesadades.botiga.Service.LlibreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    private LlibreService llibreService;

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("llibres", llibreService.findAll());
        return "catalog";
    }

    @GetMapping("/inserir")
    public String mostrarFormulari(Model model) {
        model.addAttribute("llibre", new Llibre());
        return "inserir";
    }

    @PostMapping("/inserir")
    public String inserirLlibre(@ModelAttribute Llibre llibre, Model model) {
        if (llibreService.existsByIsbn(llibre.getIsbn())) {
            model.addAttribute("error", "ISBN ja existeix");
            return "inserir";
        }
        if (!llibreService.validateISBN(llibre.getIsbn())) {
            model.addAttribute("error", "ISBN invàlid");
            return "inserir";
        }
        llibreService.save(llibre);
        return "redirect:/catalog";
    }

    @GetMapping("/cercaid")
    public String cercaId(@RequestParam Long id, Model model) {
        Optional<Llibre> llibre = llibreService.findByIdLlibre(id);
        model.addAttribute("llibre", llibre.orElse(null));
        return "search";
    }
    @GetMapping("/")
public String index() {
    return "index";
}
}