package br.senai.sc.trunfoapi.repository;

import br.senai.sc.trunfoapi.model.entity.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Integer> {
}
