package com.example.championship.repositories;

import com.example.championship.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByDay_ChampionshipId(Long championshipId);

    List<Game> findByDayId(Long dayId);
}
