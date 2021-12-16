package dev.mariocares.montesanto.repositories;

import dev.mariocares.montesanto.models.CancionModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends CrudRepository<CancionModel, Long> {

    @Query(value = "SELECT MAX(c.numero) FROM cancion AS c", nativeQuery = true)
    Integer findMaxNumero();

    Iterable<CancionModel> findByTextoContainingIgnoreCase(String termino);
}
