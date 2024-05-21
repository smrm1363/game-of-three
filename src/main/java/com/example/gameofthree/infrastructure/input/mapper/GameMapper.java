package com.example.gameofthree.infrastructure.input.mapper;

import com.example.gameofthree.domain.model.Game;
import com.example.gameofthree.domain.model.MoveRequest;
import com.example.gameofthree.domain.model.Player;
import com.example.gameofthree.infrastructure.input.dto.GameInDTO;
import com.example.gameofthree.infrastructure.input.dto.GameOutDTO;
import com.example.gameofthree.infrastructure.input.dto.MoveRequestDTO;
import com.example.gameofthree.infrastructure.input.dto.PlayerDTO;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

  public Game map(GameInDTO gameInDTO) {
    return new Game(map(gameInDTO.player1()), map(gameInDTO.player2()));
  }

  private Player map(PlayerDTO playerDto) {
    return new Player(playerDto.name());
  }

  public GameOutDTO map(Game game) {
    return new GameOutDTO(game.getCurrentNumber(), map(game.getPlayer1()), map(game.getPlayer2()));
  }

  private PlayerDTO map(Player player) {
    return new PlayerDTO(player.name());
  }

  public MoveRequest map(MoveRequestDTO moveRequestDTO) {
    return new MoveRequest(moveRequestDTO.playerName(), moveRequestDTO.addedNumber());
  }
}
