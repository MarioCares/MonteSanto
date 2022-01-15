package dev.mariocares.montesanto.repositories;

import dev.mariocares.montesanto.models.LibroModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends CrudRepository<LibroModel, Long> {

    @Query(value = "SELECT descripcion FROM libro_abreviatura WHERE libro_id = :libro_id", nativeQuery = true)
    List<String> findAbreviaturas(@Param("libro_id") Long libro_id);
}
