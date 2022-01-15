package dev.mariocares.montesanto.controllers;

import dev.mariocares.montesanto.models.LibroModel;
import dev.mariocares.montesanto.models.VersiculoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import dev.mariocares.montesanto.services.BibliaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class BibliaController {
    @Autowired
    BibliaService bibliaService;

    @GetMapping(value = "/Biblia")
    public ModelAndView indice(){
        return new ModelAndView("biblia/index")
            .addObject("Libros", bibliaService.findAllLibros());
    }

    @GetMapping(value = "/Biblia/Busqueda")
    public ModelAndView busqueda(){
        return new ModelAndView("biblia/busqueda");
    }

    @GetMapping(value = "/Libro/{id}")
    public ModelAndView libro(@PathVariable Long id){
        ModelAndView respuesta;
        Optional<LibroModel> libroModel = bibliaService.findById(id);
        if(libroModel.isPresent()){
            HashMap<Integer, Iterable<VersiculoModel>> libro = new HashMap<>();
            Iterable<Integer> capitulos = bibliaService.findCapitulos(id);
            capitulos.forEach(capitulo -> {
                libro.put(capitulo, bibliaService.findCapitulo(id, capitulo));
            });
            respuesta = new ModelAndView("biblia/libro")
                .addObject("LibroModel", libroModel.get())
                .addObject("Libro", libro);
        } else {
            respuesta = new ModelAndView("noEncuentro");
        }
        return respuesta;
    }

    @GetMapping(value = "/Libro/{id}/Capitulos")
    public ModelAndView capitulos(@PathVariable Long id){
        ModelAndView respuesta;
        Optional<LibroModel> libroModel = bibliaService.findById(id);
        respuesta = libroModel
            .map(model -> new ModelAndView("biblia/capitulos")
                .addObject("Libro", model)
                .addObject("Capitulos", bibliaService.findCapitulos(id)))
            .orElseGet(() -> new ModelAndView("noEncuentro"));
        return respuesta;
    }

    @GetMapping(value = "/Libro/{id}/Capitulo/{capitulo}")
    public ModelAndView capitulos(@PathVariable Long id, @PathVariable Integer capitulo){
        ModelAndView respuesta;
        Optional<LibroModel> libroModel = bibliaService.findById(id);
        respuesta = libroModel
                .map(model -> new ModelAndView("biblia/capitulo")
                    .addObject("Libro", model)
                    .addObject("Capitulo", bibliaService.findCapitulo(id, capitulo))
                    .addObject("Numero", capitulo)
                )
                .orElseGet(() -> new ModelAndView("noEncuentro"));
        return respuesta;
    }

    @PostMapping(value = "/Biblia/Busqueda")
    public ModelAndView busqueda(@RequestParam String termino){
        ModelAndView respuesta = new ModelAndView("biblia/busqueda");
        List<VersiculoModel> versiculos = new ArrayList<>();
        for (VersiculoModel versiculoModel : bibliaService.findTermino(termino)) {
            versiculoModel.libroModel = bibliaService.findById(versiculoModel.getLibro()).get();
            versiculos.add(versiculoModel);
        }
        return respuesta.addObject("Versiculos", versiculos);
    }
}