package dev.wellington.starwars.planets.repository;

import dev.wellington.starwars.planets.domain.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Integer> {
  Optional<Planet> findByName(String name);
}
