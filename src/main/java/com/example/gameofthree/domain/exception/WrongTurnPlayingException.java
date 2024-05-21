package com.example.gameofthree.domain.exception;

public class WrongTurnPlayingException extends RuntimeException{

  public WrongTurnPlayingException(String playerName) {
    super(String.format("It is not %s's turn to play",playerName));
  }
}
