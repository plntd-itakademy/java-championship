package com.example.championship.repositories;

import com.example.championship.models.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
    List<Day> findByChampionshipId(Long championshipId);
}
