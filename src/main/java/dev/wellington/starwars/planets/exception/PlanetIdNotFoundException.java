package dev.wellington.starwars.planets.exception;

public class PlanetIdNotFoundException extends RuntimeException {
  public PlanetIdNotFoundException() {
    super("Id not found!");
  }
}
