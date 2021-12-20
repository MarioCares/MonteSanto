package dev.mariocares.montesanto.services;

import dev.mariocares.montesanto.models.CancionModel;
import dev.mariocares.montesanto.repositories.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CancionService {
    @Autowired
    CancionRepository cancionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<CancionModel> findById(Long id){
        return cancionRepository.findById(id);
    }

    public void save(CancionModel cancion){
        cancionRepository.save(cancion);
    }

    public Iterable<CancionModel> findAllByOrderByNumeroAsc(){
        return cancionRepository.findAllByOrderByNumeroAsc();
    }

    public Integer findLastNumero() { return cancionRepository.findMaxNumero(); }

    public Iterable<CancionModel> findByTextoContaining(String termino){
        return cancionRepository.findByTextoContainingIgnoreCase(termino);
    }

    public Iterable<CancionModel> findByEtiqueta(String etiqueta){
        return cancionRepository.findByEtiqueta(etiqueta);
    }

    @Transactional
    public void asociarEtiqueta(String etiqueta, Long cancion_id){
        entityManager
            .createNativeQuery("INSERT INTO cancion_etiqueta (cancion_id, descripcion) VALUES (:cancion_id, :descripcion)")
            .setParameter("cancion_id", cancion_id)
            .setParameter("descripcion", etiqueta)
            .executeUpdate();
    }

    @Transactional
    public void eliminarEtiquetas(Long cancion_id){
        entityManager
            .createNativeQuery("DELETE FROM cancion_etiqueta WHERE cancion_id = :cancion_id")
            .setParameter("cancion_id", cancion_id)
            .executeUpdate();
    }

    public Iterable<String> findEtiquetasByCancion(Long cancion_id){
        return cancionRepository.findEtiquetasByCancion(cancion_id);
    }

    public Iterable<String> findEtiquetas(){
        return cancionRepository.findEtiquetas();
    }
}
