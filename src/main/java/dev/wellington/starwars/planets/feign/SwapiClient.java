package dev.wellington.starwars.planets.feign;

import dev.wellington.starwars.planets.feign.dto.PlanetResponseSwapiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "swapi", url = "https://swapi.dev/api")
public interface SwapiClient {
  @GetMapping("/planets/?search={planetName}")
  ResponseEntity<PlanetResponseSwapiDTO> getPlanet(@RequestParam String planetName);
}
