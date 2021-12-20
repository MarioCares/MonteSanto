package dev.mariocares.montesanto.repositories;

import dev.mariocares.montesanto.models.CancionModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends CrudRepository<CancionModel, Long> {

    @Query(value = "SELECT MAX(c.numero) FROM cancion AS c", nativeQuery = true)
    Integer findMaxNumero();

    @Query(value = "SELECT descripcion FROM cancion_etiqueta WHERE cancion_id = :cancion_id", nativeQuery = true)
    Iterable<String> findEtiquetasByCancion(@Param("cancion_id") Long id);

    @Query(value = "SELECT descripcion FROM cancion_etiqueta GROUP BY descripcion", nativeQuery = true)
    Iterable<String> findEtiquetas();

    @Query(value = "SELECT * FROM cancion WHERE id IN (SELECT cancion_id FROM cancion_etiqueta WHERE descripcion = :descripcion) ORDER BY numero", nativeQuery = true)
    Iterable<CancionModel> findByEtiqueta(@Param("descripcion") String etiqueta);

    Iterable<CancionModel> findByTextoContainingIgnoreCase(String termino);

    Iterable<CancionModel> findAllByOrderByNumeroAsc();
}
