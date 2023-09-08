package br.com.rldcarvalho.jornadaMilhas.repository;

import br.com.rldcarvalho.jornadaMilhas.model.destination.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
