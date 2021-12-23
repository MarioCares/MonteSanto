package dev.mariocares.montesanto.repositories;

import dev.mariocares.montesanto.models.ArticuloModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends CrudRepository<ArticuloModel, Long> {

    Iterable<ArticuloModel> findByTextoContainingIgnoreCase(String termino);

    Iterable<ArticuloModel> findFirst30ByOrderByArticuloAtDesc();

    @Query(value = "SELECT * FROM articulo WHERE id IN (SELECT articulo_id FROM articulo_etiqueta WHERE descripcion = :descripcion) ORDER BY articulo_at", nativeQuery = true)
    Iterable<ArticuloModel> findByEtiqueta(@Param("descripcion") String etiqueta);

    @Query(value = "SELECT descripcion FROM articulo_etiqueta WHERE articulo_id = :articulo_id", nativeQuery = true)
    Iterable<String> findEtiquetasByArticulo(@Param("articulo_id") Long id);

    @Query(value = "SELECT descripcion FROM articulo_etiqueta GROUP BY descripcion", nativeQuery = true)
    Iterable<String> findEtiquetas();
}
