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
import java.util.Arrays;
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
    public ModelAndView busqueda(){
        ModelAndView respuesta = new ModelAndView("himnario/busqueda");
        respuesta.addObject("etiquetas", cancionService.findEtiquetas());
        return respuesta;
    }

    @GetMapping(value = "/Cancion/{id}")
    public ModelAndView buscarCancion(@PathVariable Long id){
        ModelAndView respuesta;
        Optional<CancionModel> optCancion = cancionService.findById(id);
        if(optCancion.isPresent()){
            respuesta = new ModelAndView("himnario/cancion");
            respuesta.addObject("cancion", optCancion.get());
            respuesta.addObject("etiquetas",
                    String.join(",", cancionService.findEtiquetasByCancion(optCancion.get().getId())));
        } else {
            respuesta = new ModelAndView("himnario/noEncuentro");
        }
        return respuesta;
    }

    @GetMapping(value = "/Cancion/Editar/{id}")
    public ModelAndView editarCancion(@PathVariable Long id){
        ModelAndView respuesta;
        Optional<CancionModel> optCancion = cancionService.findById(id);
        if(optCancion.isPresent()){
            respuesta = new ModelAndView("himnario/cancionEditar");
            respuesta.addObject("formData",
                    new CancionFormData(optCancion.get().getNumero(),
                            optCancion.get().getPagina(),
                            optCancion.get().getTitulo(),
                            optCancion.get().getTexto()
                    )
            );
            respuesta.addObject("id", optCancion.get().getId());
            respuesta.addObject("etiquetas",
                String.join(",", cancionService.findEtiquetasByCancion(optCancion.get().getId())));
        } else {
            respuesta = new ModelAndView("noEncuentro");
        }
        return respuesta;
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
    public ModelAndView busqueda(@RequestParam String termino, @RequestParam String etiqueta){
        ModelAndView respuesta = new ModelAndView("himnario/index");
        if(!etiqueta.isBlank()){
            respuesta.addObject("canciones", cancionService.findByEtiqueta(etiqueta));
        } else {
            respuesta.addObject("canciones", cancionService.findByTextoContaining(termino));
        }
        return respuesta;
    }

    @PostMapping(value = "/Cancion/{id}")
    public String editarCancion(@Valid @ModelAttribute("formData") CancionFormData formData,
                                @PathVariable Long id, BindingResult binding, Model model){
        if(binding.hasErrors()){
            return "redirect:/Cancion/" + id;
        }
        CancionModel cancionModel = formData.toModel();
        cancionModel.setId(id);
        cancionService.save(cancionModel);
        return "redirect:/Cancion/" + id;
    }

    @PostMapping(value = "/Cancion/Etiquetas/{id}")
    public String editarEtiquetas(@RequestParam String etiquetas, @PathVariable Long id){
        cancionService.eliminarEtiquetas(id);
        Arrays.stream(etiquetas.split(",")).forEach(etiqueta -> {
            cancionService.asociarEtiqueta(etiqueta, id);
        });
        return "redirect:/Cancion/" + id;
    }

    @GetMapping(value = "/Himnario")
    public ModelAndView todas(){
        ModelAndView respuesta = new ModelAndView("himnario/index");
        respuesta.addObject("canciones", cancionService.findAllByOrderByNumeroAsc());
        return respuesta;
    }

}
