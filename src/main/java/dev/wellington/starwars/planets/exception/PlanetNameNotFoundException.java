package dev.wellington.starwars.planets.exception;

public class PlanetNameNotFoundException extends RuntimeException {
  public PlanetNameNotFoundException() {
    super("Name not found!");
  }
}
