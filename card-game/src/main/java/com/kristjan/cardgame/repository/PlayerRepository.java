package com.kristjan.cardgame.repository;

import com.kristjan.cardgame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findAllByOrderByHighestScoreDesc();
    Player findTopByOrderByHighestScoreDesc();
}
