package com.example.gameofthree.domain.model;

import jakarta.validation.constraints.NotBlank;

public record Player(@NotBlank String name){}
