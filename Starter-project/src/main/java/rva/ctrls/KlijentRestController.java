package rva.ctrls;

import java.util.Collection;

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
import rva.JPA.Kredit;
import rva.repository.KlijentRepository;
import rva.repository.KreditRepository;

@CrossOrigin
@RestController
@Api(tags = { "Klijent CRUD operacije" })
public class KlijentRestController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private KlijentRepository klijentRepository;

	@Autowired
	private KreditRepository kreditRepository;

	@GetMapping("klijent")
	@ApiOperation(value = "Vraća kolekciju svih klijenata iz baze podataka")
	public Collection<Klijent> getKlijenti() {
		return klijentRepository.findAll();
	}

	@GetMapping("klijent/{id}")
	@ApiOperation(value = "Vraća klijenta na osnovu prosleđenog ID-ija")
	public Klijent getKlijent(@PathVariable("id") Integer id) {
		return klijentRepository.getOne(id);
	}

	@GetMapping("klijentPoKreditu/{id}")
	@ApiOperation(value = "Vraća kolekciju klijenata na osnovu prosleđenog ID-ija kredita")
	public Collection<Klijent> getKlijentPoKredit(@PathVariable("id") Integer id) {
		Kredit k = kreditRepository.getOne(id);
		return klijentRepository.findByKredit(k);
	}

	@PostMapping("klijent")
	@ApiOperation(value = "Dodaje novog klijenta u bazu podataka")
	public ResponseEntity<Klijent> insertKlijent(@RequestBody Klijent klijent) {
		if (!klijentRepository.existsById(klijent.getId())) {
			klijentRepository.save(klijent);
			return new ResponseEntity<Klijent>(HttpStatus.OK);
		}
		return new ResponseEntity<Klijent>(HttpStatus.CONFLICT);
	}

	@PutMapping("klijent")
	@ApiOperation(value = "Update-uje klijenta iz baze podataka")
	public ResponseEntity<Klijent> updateKlijent(@RequestBody Klijent klijent) {
		if (!klijentRepository.existsById(klijent.getId()))
			return new ResponseEntity<Klijent>(HttpStatus.NO_CONTENT);
		klijentRepository.save(klijent);
		return new ResponseEntity<Klijent>(HttpStatus.OK);
	}

	@DeleteMapping("klijent/{id}")
	@ApiOperation(value = "Briše klijenta iz baze podataka (na osnovu prosleđene ID vrednosti)")
	public ResponseEntity<Klijent> deleteKlijent(@PathVariable Integer id) {
		if (!klijentRepository.existsById(id)) {
			return new ResponseEntity<Klijent>(HttpStatus.NO_CONTENT);
		}

		jdbcTemplate.execute("DELETE FROM racun WHERE klijent=" + id);

		klijentRepository.deleteById(id);

		if (id == -100) {
			jdbcTemplate.execute("INSERT INTO \"klijent\"(\"id\", \"ime\", \"prezime\", \"broj_lk\", \"kredit\")"
					+ "VALUES (-100, 'Test Klijent', 'Test Klijent', 445787464, 2);");
		}
		return new ResponseEntity<Klijent>(HttpStatus.OK);
	}
}
