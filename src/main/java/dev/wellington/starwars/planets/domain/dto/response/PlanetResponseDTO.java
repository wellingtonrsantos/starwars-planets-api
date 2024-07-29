package dev.wellington.starwars.planets.domain.dto.response;

import dev.wellington.starwars.planets.domain.model.Planet;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanetResponseDTO {
  private String name;
  private String climate;
  private String terrain;
  private Integer qtdAppearances;

  public PlanetResponseDTO(Planet planet) {
    this.name = planet.getName();
    this.climate = planet.getClimate();
    this.terrain = planet.getTerrain();
    this.qtdAppearances = 0;
  }

  public PlanetResponseDTO(Planet planet, Integer qtdAppearances) {
    this.name = planet.getName();
    this.climate = planet.getClimate();
    this.terrain = planet.getTerrain();
    this.qtdAppearances = qtdAppearances;
  }
}
