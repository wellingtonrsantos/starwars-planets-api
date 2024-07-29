package dev.wellington.starwars.planets.domain.dto.request;

import dev.wellington.starwars.planets.domain.model.Planet;
import jakarta.validation.constraints.NotEmpty;

public record PlanetRequestDTO(
    @NotEmpty(message = "Name cannot be null or empty!") String name,
    @NotEmpty(message = "Climate cannot be null or empty!") String climate,
    @NotEmpty(message = "Terrain cannot be null or empty!") String terrain
) {
  public Planet toEntity() {
    return new Planet(this.name, this.climate, this.terrain);
  }
}
