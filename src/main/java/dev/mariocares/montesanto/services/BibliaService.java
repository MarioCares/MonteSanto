package dev.mariocares.montesanto.services;

import dev.mariocares.montesanto.models.ArticuloModel;
import dev.mariocares.montesanto.models.Capitulo;
import dev.mariocares.montesanto.models.LibroModel;
import dev.mariocares.montesanto.models.VersiculoModel;
import dev.mariocares.montesanto.repositories.LibroRepository;
import dev.mariocares.montesanto.repositories.VersiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BibliaService {
    @Autowired
    LibroRepository libroRepository;

    @Autowired
    VersiculoRepository versiculoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<LibroModel> findAllLibros(){
        Iterable<LibroModel> libroModels = libroRepository.findAll();
        libroModels.forEach(libroModel -> {
            libroModel.setAbreviaturas(libroRepository.findAbreviaturas(libroModel.getId()));
            libroModel.setCapitulo(versiculoRepository.countCapitulos(libroModel.getId()));
        });
        return libroModels;
    }

    public Optional<LibroModel> findById(Long id){
        return libroRepository.findById(id);
    }

    public Iterable<Integer> findCapitulos(Long libro){
        return versiculoRepository.findCapitulos(libro);
    }

    public Iterable<VersiculoModel> findCapitulo(Long libro, Integer capitulo){
        return versiculoRepository.findByLibroAndCapituloOrderByVersiculo(libro, capitulo);
    }

    public Iterable<VersiculoModel> findTermino(String termino){
        return versiculoRepository.findPorTermino(termino);
    }
}
