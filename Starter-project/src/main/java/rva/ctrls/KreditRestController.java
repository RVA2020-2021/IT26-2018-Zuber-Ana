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
import rva.JPA.Kredit;
import rva.repository.KreditRepository;

@CrossOrigin
@RestController
@Api(tags = {"Kredit CRUD operacije"})
public class KreditRestController {

	@Autowired
	private KreditRepository kreditRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("kredit")
	@ApiOperation(value="Vraća kolekciju svih kredita iz baze podataka")
	public Collection<Kredit> getKrediti() {
		return kreditRepository.findAll();
	}

	@GetMapping("kredit/{id}")
	@ApiOperation(value="Vraća kredit na osnovu prosleđenog ID-ija")
	public Kredit getKredit(@PathVariable("id") Integer id) {
		return kreditRepository.getOne(id);
	}

	@GetMapping("kreditNaziv/{naziv}")
	@ApiOperation(value="Vraća kolekciju kredita na osnovu prosleđenog naziva kredita")
	public Collection<Kredit> getKreditByNaziv(@PathVariable("naziv") String naziv) {
		return kreditRepository.findByNazivContainingIgnoreCase(naziv);
	}

	@PostMapping("kredit")
	@ApiOperation(value="Dodaje novi kredit u bazu podataka")
	public ResponseEntity<Kredit> insertKredit(@RequestBody Kredit kredit) {
		if (!kreditRepository.existsById(kredit.getId())) {
			kreditRepository.save(kredit);
			return new ResponseEntity<Kredit>(HttpStatus.OK);
		}
		return new ResponseEntity<Kredit>(HttpStatus.CONFLICT);
	}

	@PutMapping("kredit")
	@ApiOperation(value="Update-uje kredit iz baze podataka")
	public ResponseEntity<Kredit> updateKredit(@RequestBody Kredit kredit) {
		if (!kreditRepository.existsById(kredit.getId()))
			return new ResponseEntity<Kredit>(HttpStatus.CONFLICT);
		kreditRepository.save(kredit);
		return new ResponseEntity<Kredit>(HttpStatus.OK);
	}

	@DeleteMapping("kredit/{id}")
	@ApiOperation(value="Briše kredit iz baze podataka (na osnovu prosleđene ID vrednosti)")
	public ResponseEntity<Kredit> deleteKredit(@PathVariable Integer id) {
		if (!kreditRepository.existsById(id))
			return new ResponseEntity<Kredit>(HttpStatus.NO_CONTENT);
		kreditRepository.deleteById(id);
		if (id == -100)
			jdbcTemplate.execute(" INSERT INTO \"kredit\"(\"id\", \"naziv\", \"oznaka\", \"opis\")"
					+ "VALUES (-100, 'Test Kredit', '1', 'Test');");
		return new ResponseEntity<Kredit>(HttpStatus.OK);
		
	}
}
