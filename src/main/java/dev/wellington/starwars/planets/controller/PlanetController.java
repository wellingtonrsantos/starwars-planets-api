package dev.wellington.starwars.planets.controller;

import dev.wellington.starwars.planets.domain.dto.request.PlanetRequestDTO;
import dev.wellington.starwars.planets.domain.dto.response.PlanetResponseDTO;
import dev.wellington.starwars.planets.service.PlanetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/star-wars/planets")
@Tag(name = "Planets Management", description = "APIs for managing Star Wars planets")
public class PlanetController {

  private final PlanetService planetService;

  @PostMapping
  @ResponseStatus(CREATED)
  @Operation(summary = "Add a new Planet")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201"),
          @ApiResponse(
                  responseCode = "400",
                  content = @Content(
                          mediaType = "application/json",
                          examples = @ExampleObject(
                                  value = "{ \"status\": \"BAD_REQUEST\", \"timestamp\": \"2024-06-28T19:42:22.988-03:00\", \"message\": \"Name cannot be null or empty!\" }"
                          )
                  ))
  })
  public void save(@RequestBody @Valid PlanetRequestDTO planetRequest) {
    this.planetService.save(planetRequest);
  }

  @GetMapping
  @Operation(summary = "Find all planets")
  @ResponseStatus(OK)
  public List<PlanetResponseDTO> findAll() {
    return planetService.list();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Find planet by id")
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  content = @Content(
                          mediaType = "application/json",
                          examples = @ExampleObject(
                                  value = "{ \"name\": \"Dagobah\", \"climate\": \"Murky\", \"terrain\": \"Swamp\", \"qtdAppearances\": 3 }"
                  )
          )),
          @ApiResponse(
                  responseCode = "400",
                  content = @Content(
                          mediaType = "application/json",
                          examples = @ExampleObject(
                                  value = "{ \"status\": \"BAD_REQUEST\", \"timestamp\": \"2024-06-29T13:16:56.80376994\", \"message\": \"Id not found!\" }"
                          )
                  ))
  })
  public ResponseEntity<PlanetResponseDTO> findById(@PathVariable Integer id) {
    return new ResponseEntity<>(planetService.findByPlanetId(id), OK);
  }

  @Operation(summary = "Find planet by name")
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  content = @Content(
                          mediaType = "application/json",
                          examples = @ExampleObject(
                                  value = "{ \"name\": \"Dagobah\", \"climate\": \"Murky\", \"terrain\": \"Swamp\", \"qtdAppearances\": 3 }"
                          )
                  )),
          @ApiResponse(
                  responseCode = "400",
                  content = @Content(
                          mediaType = "application/json",
                          examples = @ExampleObject(
                                  value = "{ \"status\": \"BAD_REQUEST\", \"timestamp\": \"2024-06-29T13:19:43.233957069\", \"message\": \"Name not found!\" }"
                          )
                  ))
  })
  @GetMapping("/name")
  public ResponseEntity<PlanetResponseDTO> findByName(@RequestParam(value = "planet-name") String name) {
    return new ResponseEntity<>(planetService.findByName(name), OK);
  }

  @Operation(summary = "Delete planet by id")
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "400",
                  content = @Content(
                          mediaType = "application/json",
                          examples = @ExampleObject(
                                  value = "{ \"status\": \"BAD_REQUEST\", \"timestamp\": \"2024-06-29T13:16:56.80376994\", \"message\": \"Id not found!\" }"
                          )
                  ))
  })
  @DeleteMapping("{id}")
  @ResponseStatus(OK)
  public void delete(@PathVariable Integer id) {
    planetService.removeById(id);
  }
}
