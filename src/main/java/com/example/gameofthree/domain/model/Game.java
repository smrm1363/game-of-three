package com.example.gameofthree.domain.model;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Game {
  private int currentNumber;
  @NotNull private Player player1;
  @NotNull private Player player2;
  private Player nextPlayer;

  public Game(Player player1, Player player2) {
    if (player1.name().equals(player2.name()))
      throw new ValidationException("Players name could not be similar");
    this.player1 = player1;
    this.player2 = player2;
  }
}
