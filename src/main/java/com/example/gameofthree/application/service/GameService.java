package com.example.gameofthree.application.service;

import com.example.gameofthree.application.usecase.GameUseCase;
import com.example.gameofthree.domain.exception.GameHasNotBeenStarted;
import com.example.gameofthree.domain.exception.WrongTurnPlayingException;
import com.example.gameofthree.domain.event.MoveEvent;
import com.example.gameofthree.domain.model.Game;
import com.example.gameofthree.domain.model.Move;
import com.example.gameofthree.domain.model.MoveRequest;
import com.example.gameofthree.domain.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService implements GameUseCase {
  private final ApplicationEventPublisher eventPublisher;
  private Game game;
  private boolean gameStarted;

  @Override
  public Game startGame(Game game) {
    this.game = game;
    this.game.setCurrentNumber(Math.abs(ThreadLocalRandom.current().nextInt()));
    log.info("Game started with initial number: " + this.game.getCurrentNumber());
    // Assume player1 starts the game
    promptNextMove(this.game.getPlayer2());
    gameStarted = true;
    return this.game;
  }

  @Override
  public Game makeMove(MoveRequest moveRequest)
      throws WrongTurnPlayingException, GameHasNotBeenStarted {
    if (!gameStarted) throw new GameHasNotBeenStarted();
    Player player = findPlayerByName(moveRequest.playerName());
    if (player.equals(game.getNextPlayer())) {
      int addedNumber = moveRequest.addedNumber();
      int currentNumber = game.getCurrentNumber();
      int resultingNumber = (currentNumber + addedNumber) / 3;

      Move move = new Move(addedNumber, resultingNumber);
      game.setCurrentNumber(resultingNumber);

      log.info(
          "Player {} added {}, resulting number: {}", player.name(), addedNumber, resultingNumber);

      if (resultingNumber == 1) {
        log.info("Player {} wins!", player.name());
        gameStarted = false;
        return game;
      }

      Player nextPlayer = determineNextPlayer(player);
      eventPublisher.publishEvent(new MoveEvent(this, move, nextPlayer));
    } else {
      throw new WrongTurnPlayingException(player.name());
    }
    return game;
  }

  @Override
  public void promptNextMove(Player player) {
    game.setNextPlayer(player);
    log.info("Waiting for {} to make their move.", player.name());
  }

  private Player findPlayerByName(String playerName) {
    if (game.getPlayer1().name().equals(playerName)) {
      return game.getPlayer1();
    } else if (game.getPlayer2().name().equals(playerName)) {
      return game.getPlayer2();
    }
    throw new IllegalArgumentException("Player not found: " + playerName);
  }

  private Player determineNextPlayer(Player currentPlayer) {
    return currentPlayer.equals(game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1();
  }

  public boolean isGameStarted() {
    return gameStarted;
  }
}
