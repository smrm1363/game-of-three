package com.example.gameofthree.infrastructure.input.dto;

import jakarta.validation.constraints.NotNull;

public record GameInDTO(@NotNull PlayerDTO player1, @NotNull PlayerDTO player2) {}
