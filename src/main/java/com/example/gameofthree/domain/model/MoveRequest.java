package com.example.gameofthree.domain.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record MoveRequest(@NotBlank String playerName, @Min(-1) @Max(1) int addedNumber) {}
