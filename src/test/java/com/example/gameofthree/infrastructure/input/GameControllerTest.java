package com.example.gameofthree.infrastructure.input;

import com.example.gameofthree.application.usecase.GameUseCase;
import com.example.gameofthree.domain.model.Game;
import com.example.gameofthree.domain.model.MoveRequest;
import com.example.gameofthree.domain.model.Player;
import com.example.gameofthree.infrastructure.input.dto.GameInDTO;
import com.example.gameofthree.infrastructure.input.dto.GameOutDTO;
import com.example.gameofthree.infrastructure.input.dto.MoveRequestDTO;
import com.example.gameofthree.infrastructure.input.dto.PlayerDTO;
import com.example.gameofthree.infrastructure.input.mapper.GameMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameControllerTest {

  @Mock
  private GameUseCase gameUseCase;

  @Mock
  private GameMapper gameMapper;

  @InjectMocks
  private GameController gameController;

  private GameInDTO gameInDTO;
  private GameOutDTO gameOutDTO;
  private Game game;
  private MoveRequestDTO moveRequestDTO;
  private MoveRequest moveRequest;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    gameInDTO = new GameInDTO(new PlayerDTO("Player 1"), new PlayerDTO("Player 2"));
    gameOutDTO = new GameOutDTO(10, new PlayerDTO("Player 1"), new PlayerDTO("Player 2"));
    game = new Game(new Player("Player 1"), new Player("Player 2"));
    moveRequestDTO = new MoveRequestDTO("Player 1", 0);
    moveRequest = new MoveRequest("Player 1", 0);
  }

  @Test
  void startGame_ShouldReturnGameOutDTO() {
    when(gameMapper.map(gameInDTO)).thenReturn(game);
    when(gameUseCase.startGame(game)).thenReturn(game);
    when(gameMapper.map(game)).thenReturn(gameOutDTO);

    ResponseEntity<GameOutDTO> response = gameController.startGame(gameInDTO);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(gameOutDTO, response.getBody());
    verify(gameMapper).map(gameInDTO);
    verify(gameUseCase).startGame(game);
    verify(gameMapper).map(game);
  }

  @Test
  void makeMove_ShouldReturnGameOutDTO() {
    when(gameMapper.map(moveRequestDTO)).thenReturn(moveRequest);
    when(gameUseCase.makeMove(moveRequest)).thenReturn(game);
    when(gameMapper.map(game)).thenReturn(gameOutDTO);

    ResponseEntity<GameOutDTO> response = gameController.makeMove(moveRequestDTO);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(gameOutDTO, response.getBody());
    verify(gameMapper).map(moveRequestDTO);
    verify(gameUseCase).makeMove(moveRequest);
    verify(gameMapper).map(game);
  }
}
