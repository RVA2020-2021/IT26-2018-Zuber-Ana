package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.JPA.Klijent;
import rva.JPA.Racun;
import rva.JPA.TipRacuna;
import rva.repository.KlijentRepository;
import rva.repository.RacunRepository;
import rva.repository.TipRacunaRepository;

@CrossOrigin
@RestController
@Api(tags = {"Racun CRUD operacije"})
public class RacunRestController {
	//komentar za commit

	@Autowired
	private RacunRepository racunRepository;

	@Autowired
	private KlijentRepository klijentRepository;
	
	@Autowired
	private TipRacunaRepository tipRacunaRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("racun")
	@ApiOperation(value="Vraća kolekciju svih računa iz baze podataka")
	public Collection<Racun> getRacuni() {
		return racunRepository.findAll();
	}

	@GetMapping("racun/{id}")
	@ApiOperation(value="Vraća račun na osnovu prosleđenog ID-ija")
	public Racun getRacuni(@PathVariable("id") Integer id) {
		return racunRepository.getOne(id);
	}

	@GetMapping("racunPoKlijentu/{id}")
	@ApiOperation(value="Vraća kolekciju računa na osnovu prosleđenog ID-ija klijenta")
	public Collection<Racun> getRacunPoKlijentu(@PathVariable("id") Integer id) {
		Klijent k = klijentRepository.getOne(id);
		return racunRepository.findByKlijent(k);
	}

	@GetMapping("racunPoTipuRacuna/{id}")
	@ApiOperation(value="Vraća kolekciju računa na osnovu prosleđenog ID-ija tipa računa")
	public Collection<Racun> getRacunPoTipuRacuna(@PathVariable("id") Integer id) {
		TipRacuna t = tipRacunaRepository.getOne(id);
		return racunRepository.findByTipRacuna(t);
	}

	@PostMapping("racun")
	@ApiOperation(value="Dodaje novi račun u bazu podataka")
	public ResponseEntity<Racun> insertRacun(@RequestBody Racun racun) {
		if (!racunRepository.existsById(racun.getId())) {
			racunRepository.save(racun);
			return new ResponseEntity<Racun>(HttpStatus.OK);
		}
		return new ResponseEntity<Racun>(HttpStatus.CONFLICT);
	}

	@PutMapping("racun")
	@ApiOperation(value="Update-uje račun iz baze podataka")
	public ResponseEntity<Racun> updateRacun(@RequestBody Racun racun) {
		if (!racunRepository.existsById(racun.getId()))
			return new ResponseEntity<Racun>(HttpStatus.NO_CONTENT);
		racunRepository.save(racun);
		return new ResponseEntity<Racun>(HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping("racun/{id}")
	@ApiOperation(value="Briše račun iz baze podataka (na osnovu prosleđene ID vrednosti)")
	public ResponseEntity<Racun> deleteRacun(@PathVariable Integer id) {
		if (!racunRepository.existsById(id))
			return new ResponseEntity<Racun>(HttpStatus.NO_CONTENT);

		racunRepository.deleteById(id);

		if (id == -100)
			jdbcTemplate.execute(
					"INSERT INTO \"racun\"(\"id\", \"naziv\", \"oznaka\", \"opis\", \"tip_racuna\", \"klijent\")"
							+ "VALUES (-100, 'Test Kredit', 'Test', 'Test', 1, 4);");
		return new ResponseEntity<Racun>(HttpStatus.OK);
	}

}
