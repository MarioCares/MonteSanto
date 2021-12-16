package dev.mariocares.montesanto.repositories;

import dev.mariocares.montesanto.models.CancionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends CrudRepository<CancionModel, Long> {
}
