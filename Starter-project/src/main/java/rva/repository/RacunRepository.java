package rva.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.JPA.Klijent;
import rva.JPA.Racun;
import rva.JPA.TipRacuna;

public interface RacunRepository extends JpaRepository<Racun, Integer> {

	Collection<Racun> findByKlijent(Klijent k);

	Collection<Racun> findByTipRacuna(TipRacuna t);

}
