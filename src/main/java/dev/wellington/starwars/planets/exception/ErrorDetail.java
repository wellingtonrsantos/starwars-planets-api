package dev.wellington.starwars.planets.exception;

import java.time.LocalDateTime;

public record ErrorDetail(
    String status,
    LocalDateTime timestamp,
    String message
) {}
