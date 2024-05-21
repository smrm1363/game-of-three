package com.example.gameofthree.domain.exception;

public class GameHasNotBeenStarted extends RuntimeException {

  public GameHasNotBeenStarted() {
    super("The game has not been started");
  }
}
