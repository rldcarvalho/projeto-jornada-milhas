package br.com.rldcarvalho.jornadaMilhas.repository;

import br.com.rldcarvalho.jornadaMilhas.model.destination.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query(value = "SELECT * FROM destination WHERE id = :id AND active = true", nativeQuery = true)
    Optional<Destination> findByIdAndActive(@Param("id") Long id);

    @Query(value = "SELECT * FROM destination WHERE (COALESCE(:name, '') = '' OR name LIKE CONCAT(:name, '%')) AND active = true", nativeQuery = true)
    List<Destination> findAllFilteredByName(String name);
}
