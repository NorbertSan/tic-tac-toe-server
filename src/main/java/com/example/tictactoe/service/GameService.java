package com.example.tictactoe.service;

import com.example.tictactoe.exception.GameException;
import com.example.tictactoe.model.Game;
import com.example.tictactoe.model.GameStatus;
import com.example.tictactoe.model.Player;
import com.example.tictactoe.storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(Player player){
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(GameStatus.NEW);

        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToGame(Player player2,String gameId) throws GameException {
        if(!GameStorage.getInstance ().getGames().containsKey(gameId)){
            String gameNotFoundMessage = "Game with id " + gameId + "not found";
            throw new GameException(gameNotFoundMessage);
        };
        Game game = GameStorage.getInstance().getGames().get(gameId);
        if(game.getPlayer2() != null){
            String noFreeSlotsMessage = "Game with id " + gameId + "has to free slots to play";
            throw new GameException(noFreeSlotsMessage);
        }
        game.setPlayer2(player2);
        game.setStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }
}
