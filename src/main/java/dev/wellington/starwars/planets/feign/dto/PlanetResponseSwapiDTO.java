package dev.wellington.starwars.planets.feign.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PlanetResponseSwapiDTO {
  private List<PlanetDTO> results;
}
