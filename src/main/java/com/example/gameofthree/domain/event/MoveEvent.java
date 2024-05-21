package com.example.gameofthree.domain.event;

import com.example.gameofthree.domain.model.Move;
import com.example.gameofthree.domain.model.Player;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MoveEvent extends ApplicationEvent {
  private final Move move;
  private final Player nextPlayer;

  public MoveEvent(Object source, Move move, Player nextPlayer) {
    super(source);
    this.move = move;
    this.nextPlayer = nextPlayer;
  }
}
