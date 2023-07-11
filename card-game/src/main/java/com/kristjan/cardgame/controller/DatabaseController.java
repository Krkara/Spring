package com.kristjan.cardgame.controller;

import com.kristjan.cardgame.entity.Game;
import com.kristjan.cardgame.entity.Player;
import com.kristjan.cardgame.repository.GameRepository;
import com.kristjan.cardgame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("database")
public class DatabaseController {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("players")
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("games")
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/games/max-answers")
    public List<Game> getGamesOrderByCorrectAnswers() {
        return gameRepository.findAllByOrderByCorrectAnswersDesc();
    }

    @GetMapping("/players/max-scores")
    public List<Player> getPlayersOrderByHighestScore() {
        return playerRepository.findAllByOrderByHighestScoreDesc();
    }

    @GetMapping("games/{playerName}")
    public List<Game> getGamesByPlayerName(@PathVariable String playerName) {
        return gameRepository.findGamesByPlayerName(playerName);
    }

    @GetMapping("/games/max-score/{playerName}")
    public List<Game> getGamesByPlayerNameOrderByCorrectAnswers(@PathVariable String playerName) {
        return gameRepository.findGamesByPlayerNameOrderByCorrectAnswers(playerName);
    }

    @GetMapping("/games/min-score/{answers}")
    public List<Game> getGamesByMinimumCorrectAnswers(@PathVariable int answers) {
        return gameRepository.findAllByCorrectAnswersIsGreaterThanEqual(answers);
    }

    @GetMapping("/games/max-score")
    public Game getGameWithHighestCorrectAnswers() {
        return gameRepository.findTopByOrderByCorrectAnswersDesc();
    }

    @GetMapping("/players/max-score")
    public Player getPlayerWithHighestScore() {
        return playerRepository.findTopByOrderByHighestScoreDesc();
    }

    @GetMapping("/games/top3-max-score")
    public List<Game> getTop3GamesByCorrectAnswers() {
        return gameRepository.findTop3GamesByCorrectAnswersDec();
    }
}
