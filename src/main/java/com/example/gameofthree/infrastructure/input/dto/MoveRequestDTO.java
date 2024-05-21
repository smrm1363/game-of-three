package com.example.gameofthree.infrastructure.input.dto;

import jakarta.validation.constraints.NotBlank;

public record MoveRequestDTO(@NotBlank String playerName, int addedNumber) {}
