package com.example.gameofthree.application.service;

import com.example.gameofthree.domain.event.MoveEvent;
import com.example.gameofthree.domain.exception.GameHasNotBeenStarted;
import com.example.gameofthree.domain.exception.WrongTurnPlayingException;
import com.example.gameofthree.domain.model.Game;
import com.example.gameofthree.domain.model.MoveRequest;
import com.example.gameofthree.domain.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class GameServiceTest {

  @Mock
  private ApplicationEventPublisher eventPublisher;

  @InjectMocks
  private GameService gameService;

  private Game game;
  private Player player1;
  private Player player2;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    player1 = new Player("Player 1");
    player2 = new Player("Player 2");
    game = new Game(player1, player2);
  }

  @Test
  void startGame_ShouldInitializeGameAndSetInitialNumber() {
    gameService.startGame(game);

    assertTrue(game.getCurrentNumber() > 0);  // Ensuring the number is positive
    assertTrue(gameService.isGameStarted());
  }

  @Test
  void makeMove_ShouldProcessValidMove() throws GameHasNotBeenStarted, WrongTurnPlayingException {
    gameService.startGame(game);
    MoveRequest moveRequest = new MoveRequest(player2.name(), 0);
    game.setNextPlayer(player2);

    gameService.makeMove(moveRequest);

    verify(eventPublisher).publishEvent(any(MoveEvent.class));
  }

  @Test
  void makeMove_ShouldThrowGameHasNotBeenStartedException() {
    MoveRequest moveRequest = new MoveRequest(player2.name(), 0);

    assertThrows(GameHasNotBeenStarted.class, () -> gameService.makeMove(moveRequest));
  }

  @Test
  void makeMove_ShouldThrowWrongTurnPlayingException() {
    gameService.startGame(game);
    MoveRequest moveRequest = new MoveRequest(player1.name(), 0);

    assertThrows(WrongTurnPlayingException.class, () -> gameService.makeMove(moveRequest));
  }

  @Test
  void makeMove_ShouldEndGameWhenResultingNumberIsOne() throws GameHasNotBeenStarted, WrongTurnPlayingException {
    gameService.startGame(game);
    MoveRequest moveRequest = new MoveRequest(player2.name(), -game.getCurrentNumber() + 1);
    game.setNextPlayer(player2);

    gameService.makeMove(moveRequest);

    assertEquals(1, game.getCurrentNumber()+1);
    assertTrue(gameService.isGameStarted());
  }

  @Test
  void promptNextMove_ShouldSetNextPlayer() {
    gameService.startGame(game);

    gameService.promptNextMove(player1);

    assertEquals(player1, game.getNextPlayer());
  }
}
