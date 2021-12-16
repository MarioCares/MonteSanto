package dev.mariocares.montesanto.controllers;

import dev.mariocares.montesanto.models.CancionFormData;
import dev.mariocares.montesanto.models.CancionModel;
import dev.mariocares.montesanto.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class CancionController {
    @Autowired
    CancionService cancionService;

    @GetMapping(value = "/Cancion/Nueva")
    public ModelAndView nueva(){
        ModelAndView respuesta = new ModelAndView("himnario/nueva");
        respuesta.addObject("formData", new CancionFormData());
        respuesta.addObject("siguienteNumero", cancionService.findLastNumero() + 1);
        return respuesta;
    }

    @GetMapping(value = "/Cancion/Busqueda")
    public String busqueda(){
        return "himnario/busqueda";
    }

    @PostMapping(value = "/Cancion/Nueva")
    public String nueva(@Valid @ModelAttribute("formData") CancionFormData formData, BindingResult binding, Model model){
        if(binding.hasErrors()){
            return "himnario/nueva";
        }
        cancionService.save(formData.toModel());
        return "redirect:/Himnario";
    }

    @PostMapping(value = "/Cancion/Busqueda")
    public ModelAndView busqueda(@RequestParam String termino){
        ModelAndView respuesta = new ModelAndView("himnario/index");
        respuesta.addObject("canciones", cancionService.findByTextoContaining(termino));
        return respuesta;
    }

    @GetMapping(value = "/Cancion/{id}")
    public ModelAndView buscarCancion(@PathVariable Long id){
        ModelAndView respuesta;
        Optional<CancionModel> optCancion = cancionService.findById(id);
        if(optCancion.isPresent()){
            respuesta = new ModelAndView("himnario/cancion");
            respuesta.addObject("cancion", optCancion.get());
        } else {
            respuesta = new ModelAndView("himnario/noEncuentro");
        }
        return respuesta;
    }

    @GetMapping(value = "/Himnario")
    public ModelAndView todas(){
        ModelAndView respuesta = new ModelAndView("himnario/index");
        respuesta.addObject("canciones", cancionService.findAll());
        return respuesta;
    }
}
