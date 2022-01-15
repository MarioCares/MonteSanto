package dev.mariocares.montesanto.repositories;

import dev.mariocares.montesanto.models.Capitulo;
import dev.mariocares.montesanto.models.VersiculoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

public interface VersiculoRepository extends CrudRepository<VersiculoModel, Long> {

    @Query(value = "SELECT capitulo FROM versiculo WHERE libro_id = :libro_id GROUP BY capitulo ORDER BY capitulo DESC LIMIT 1",
            nativeQuery = true)
    Integer countCapitulos(@Param("libro_id") Long libro_id);

    @Query(value = "SELECT capitulo FROM versiculo WHERE libro_id = :libro_id GROUP BY capitulo ORDER BY capitulo",
            nativeQuery = true)
    Iterable<Integer> findCapitulos(@Param("libro_id") Long libro_id);

    Iterable<VersiculoModel> findByLibroAndCapituloOrderByVersiculo(Long libro, Integer capitulo);

    @Query(value = "SELECT * FROM versiculo WHERE tokens_spanish @@ to_tsquery('spanish', :termino) "
            + "ORDER BY (ts_rank(tokens_spanish, to_tsquery('spanish', :termino)) + "
            + "ts_rank(tokens_simple, to_tsquery(:termino))) DESC, libro_id, capitulo, versiculo", nativeQuery = true)
    List<VersiculoModel> findPorTermino(@Param("termino") String termino);

}