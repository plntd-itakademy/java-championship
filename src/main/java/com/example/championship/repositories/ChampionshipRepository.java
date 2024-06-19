package com.example.championship.repositories;

import com.example.championship.models.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipRepository extends JpaRepository<Championship, Long> {

}
