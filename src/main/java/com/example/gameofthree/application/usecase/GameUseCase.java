package com.example.gameofthree.application.usecase;

import com.example.gameofthree.domain.exception.GameHasNotBeenStarted;
import com.example.gameofthree.domain.exception.WrongTurnPlayingException;
import com.example.gameofthree.domain.model.Game;
import com.example.gameofthree.domain.model.MoveRequest;
import com.example.gameofthree.domain.model.Player;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface GameUseCase {

  Game startGame(@Valid Game game);

  Game makeMove(@Valid MoveRequest moveRequest)
      throws WrongTurnPlayingException, GameHasNotBeenStarted;

  void promptNextMove(Player player);
}
