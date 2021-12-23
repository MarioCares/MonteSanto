package dev.mariocares.montesanto.controllers;

import dev.mariocares.montesanto.models.*;
import dev.mariocares.montesanto.services.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class ArticuloController {
    @Autowired
    ArticuloService articuloService;

    @GetMapping(value = "/Articulos")
    public ModelAndView articulos(){
        return new ModelAndView("articulos/index")
            .addObject("Articulos", articuloService.findFirst30ByOrderByArticuloAtDesc());
    }

    @GetMapping(value = "/Articulo/Nuevo")
    public ModelAndView nuevo(){
        return new ModelAndView("articulos/nuevo")
            .addObject("Tipos", ArticuloTipo.values())
            .addObject("Publicadores", Persona.values())
            .addObject("Etiquetas", "")
            .addObject("formData", new ArticuloFormData());
    }

    @GetMapping(value = "/Articulo/{id}")
    public ModelAndView articulo(@PathVariable Long id){
        ModelAndView respuesta;
        Optional<ArticuloModel> articulo = articuloService.findById(id);
        if(articulo.isPresent()){
            respuesta = new ModelAndView("articulos/articulo");
            respuesta.addObject("Articulo", articulo.get());
        }else{
            respuesta = new ModelAndView("noEncuentro");
        }
        return respuesta;
    }

    @PostMapping(value = "/Articulo/Nuevo")
    public String nuevo(@Valid @ModelAttribute("formData") ArticuloFormData formData,
                        BindingResult binding,
                        @RequestParam String etiquetas,
                        Model model){
        if(binding.hasErrors()){
            model
                .addAttribute("Tipos", ArticuloTipo.values())
                .addAttribute("Publicadores", Persona.values())
                .addAttribute("Etiquetas", etiquetas);
            return "articulos/nuevo";
        }
        try {
            Long id = articuloService.save(formData.toModel());
            Arrays.stream(etiquetas.split(",")).forEach(etiqueta -> {
                articuloService.asociarEtiqueta(etiqueta, id);
            });
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return "articulos/index";
    }
}