package rva.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.JPA.Klijent;
import rva.JPA.Kredit;

public interface KlijentRepository extends JpaRepository<Klijent, Integer> {

	Collection<Klijent> findByKredit(Kredit k);
}
