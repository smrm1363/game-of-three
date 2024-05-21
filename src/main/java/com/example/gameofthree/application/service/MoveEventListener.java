package com.example.gameofthree.application.service;

import com.example.gameofthree.domain.event.MoveEvent;
import com.example.gameofthree.domain.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MoveEventListener {
  private final GameService gameService;

  @EventListener
  public void onMoveEvent(MoveEvent event) {
    Player nextPlayer = event.getNextPlayer();
    log.info(
        "It's {}'s turn. Current number: {}", nextPlayer.name(), event.getMove().resultingNumber());
    gameService.promptNextMove(nextPlayer);
  }
}
