package dev.mariocares.montesanto.services;

import dev.mariocares.montesanto.models.ArticuloModel;
import dev.mariocares.montesanto.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ArticuloService {
    @Autowired
    ArticuloRepository articuloRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<ArticuloModel> findById(Long id){
        return articuloRepository.findById(id);
    }

    public Long save(ArticuloModel articulo){
        return articuloRepository.save(articulo).getId();
    }

    public Iterable<ArticuloModel> findFirst30ByOrderByArticuloAtDesc(){
        return articuloRepository.findFirst30ByOrderByArticuloAtDesc();
    }

    public Iterable<ArticuloModel> findByTextoContaining(String termino){
        return articuloRepository.findByTextoContainingIgnoreCase(termino);
    }

    public Iterable<ArticuloModel> findByEtiqueta(String etiqueta){
        return articuloRepository.findByEtiqueta(etiqueta);
    }

    @Transactional
    public void asociarEtiqueta(String etiqueta, Long articulo_id){
        entityManager
            .createNativeQuery("INSERT INTO articulo_etiqueta (articulo_id, descripcion) VALUES (:articulo_id, :descripcion)")
            .setParameter("articulo_id", articulo_id)
            .setParameter("descripcion", etiqueta)
            .executeUpdate();
    }

    @Transactional
    public void eliminarEtiquetas(Long articulo_id){
        entityManager
            .createNativeQuery("DELETE FROM articulo_etiqueta WHERE articulo_id = :articulo_id")
            .setParameter("articulo_id", articulo_id)
            .executeUpdate();
    }

    public Iterable<String> findEtiquetasByArticulo(Long articulo_id){
        return articuloRepository.findEtiquetasByArticulo(articulo_id);
    }

    public Iterable<String> findEtiquetas(){
        return articuloRepository.findEtiquetas();
    }
}
