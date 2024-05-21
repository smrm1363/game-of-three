package com.example.gameofthree.application.service;

import static org.mockito.Mockito.verify;

import com.example.gameofthree.domain.event.MoveEvent;
import com.example.gameofthree.domain.model.Move;
import com.example.gameofthree.domain.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MoveEventListenerTest {

  @Mock
  private GameService gameService;

  @InjectMocks
  private MoveEventListener moveEventListener;
  private Player player2;
  private Move move;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    player2 = new Player("Player 2");
    move = new Move(0, 10);  // Example move with 0 added and resulting number 10
  }

  @Test
  void onMoveEvent_ShouldPromptNextMove() {
    MoveEvent event = new MoveEvent(this, move, player2);

    moveEventListener.onMoveEvent(event);

    verify(gameService).promptNextMove(player2);
  }
}
