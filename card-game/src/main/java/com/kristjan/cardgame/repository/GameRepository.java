package com.kristjan.cardgame.repository;

import com.kristjan.cardgame.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByOrderByCorrectAnswersDesc();
    List<Game> findGamesByPlayerName(String playerName);
    List<Game> findGamesByPlayerNameOrderByCorrectAnswers(String playerName);
    List<Game> findAllByCorrectAnswersIsGreaterThanEqual(int answers);
    Game findTopByOrderByCorrectAnswersDesc();
    @Query("SELECT g FROM Game g ORDER BY g.correctAnswers desc limit 3")
    List<Game> findTop3GamesByCorrectAnswersDec();
}
