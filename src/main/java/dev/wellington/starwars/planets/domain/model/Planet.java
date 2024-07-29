package dev.wellington.starwars.planets.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "planet")
public class Planet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Setter
  @Column(name = "name", unique = true, nullable = false)
  private String name;

  @Setter
  @Column(name = "climate", nullable = false)
  private String climate;

  @Setter
  @Column(name = "terrain", nullable = false)
  private String terrain;

  public Planet(String name, String climate, String terrain) {
    this.name = name;
    this.climate = climate;
    this.terrain = terrain;
  }
}
