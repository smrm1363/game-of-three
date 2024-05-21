package com.example.gameofthree.infrastructure.input;

import com.example.gameofthree.application.usecase.GameUseCase;
import com.example.gameofthree.domain.model.Game;
import com.example.gameofthree.domain.model.MoveRequest;
import com.example.gameofthree.infrastructure.input.dto.GameInDTO;
import com.example.gameofthree.infrastructure.input.dto.GameOutDTO;
import com.example.gameofthree.infrastructure.input.dto.MoveRequestDTO;
import com.example.gameofthree.infrastructure.input.mapper.GameMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
  private final GameUseCase gameUseCase;
  private final GameMapper gameMapper;

  @PostMapping("/start")
  public ResponseEntity<GameOutDTO> startGame(@Valid @RequestBody GameInDTO gameInDTO) {
    Game game = gameMapper.map(gameInDTO);
    Game result = gameUseCase.startGame(game);
    return ResponseEntity.ok(gameMapper.map(result));
  }

  @PostMapping("/move")
  public ResponseEntity<GameOutDTO> makeMove(@Valid @RequestBody MoveRequestDTO moveRequestDTO) {
    MoveRequest moveRequest = gameMapper.map(moveRequestDTO);
    Game result = gameUseCase.makeMove(moveRequest);
    return ResponseEntity.ok(gameMapper.map(result));
  }
}
