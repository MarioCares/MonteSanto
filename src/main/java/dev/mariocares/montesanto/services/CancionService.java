package dev.mariocares.montesanto.services;

import dev.mariocares.montesanto.models.CancionModel;
import dev.mariocares.montesanto.repositories.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CancionService {
    @Autowired
    CancionRepository cancionRepository;

    public Optional<CancionModel> findById(Long id){
        return cancionRepository.findById(id);
    }

    public void save(CancionModel cancion){
        cancionRepository.save(cancion);
    }

    public Iterable<CancionModel> findAll(){
        return cancionRepository.findAll();
    }
}
