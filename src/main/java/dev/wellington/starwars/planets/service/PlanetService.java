package dev.wellington.starwars.planets.service;

import dev.wellington.starwars.planets.domain.dto.request.PlanetRequestDTO;
import dev.wellington.starwars.planets.domain.dto.response.PlanetResponseDTO;
import dev.wellington.starwars.planets.domain.model.Planet;
import dev.wellington.starwars.planets.exception.PlanetIdNotFoundException;
import dev.wellington.starwars.planets.exception.PlanetNameNotFoundException;
import dev.wellington.starwars.planets.feign.SwapiClient;
import dev.wellington.starwars.planets.feign.dto.PlanetResponseSwapiDTO;
import dev.wellington.starwars.planets.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlanetService {

  private final PlanetRepository planetRepository;
  private final SwapiClient swapiClient;

  public void save(PlanetRequestDTO planet) {
    log.info("Saving planet request [{}]", planet);
    planetRepository.save(planet.toEntity());
  }

  public List<PlanetResponseDTO> list() {
    log.info("Listing all planets...");
    List<Planet> planetsList = planetRepository.findAll();

    if(planetsList.isEmpty()) return new ArrayList<>();

    return planetsList.stream()
            .map(planet -> new PlanetResponseDTO(
                    planet,
                    getQuantityOfFilmAppearances(planet.getName())))
            .toList();
  }

  public PlanetResponseDTO findByName(String name) {
    log.info("Listing planet by name [{}]", name);
    Planet planet = planetRepository
        .findByName(name)
        .orElseThrow(PlanetNameNotFoundException::new);

    PlanetResponseDTO planetResponseDTO = new PlanetResponseDTO(planet);
    planetResponseDTO.setQtdAppearances(getQuantityOfFilmAppearances(name));

    return planetResponseDTO;
  }

  public PlanetResponseDTO findByPlanetId(Integer id) {
    log.info("Listing planet by id [{}]", id);
    Planet planet = findById(id);
    
    PlanetResponseDTO planetResponseDTO = new PlanetResponseDTO(planet);
    planetResponseDTO.setQtdAppearances(getQuantityOfFilmAppearances(planetResponseDTO.getName()));
    
    return planetResponseDTO;
  }

  public void removeById(Integer id) {
    log.info("Removing planet by id [{}]", id);
    Planet planet = this.findById(id);
    planetRepository.delete(planet);
  }
  
  private Planet findById(Integer id) {
    log.info("Finding planet by id [{}]", id);
    return planetRepository
        .findById(id)
        .orElseThrow(PlanetIdNotFoundException::new);
  }

  private Integer getQuantityOfFilmAppearances(String planetName) {
    log.info("Searching for the number of movie appearances on the planet {}", planetName);
    ResponseEntity<PlanetResponseSwapiDTO> response = swapiClient.getPlanet(planetName);

    log.info("Swapi Api response: [{}]", response);
    if (response.hasBody()) {
      PlanetResponseSwapiDTO planet = response.getBody();

      if (Objects.isNull(planet)) {
        return 0;
      }

      if (planet.getResults().isEmpty()) {
        return 0;
      }

      return planet.getResults().getFirst().getFilms().size();

    }
    return 0;
  }
}
