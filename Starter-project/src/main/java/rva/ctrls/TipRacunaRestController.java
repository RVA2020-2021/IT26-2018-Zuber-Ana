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
import rva.JPA.TipRacuna;
import rva.repository.TipRacunaRepository;

@CrossOrigin
@RestController
@Api(tags = {"TipRacuna CRUD operacije"})

public class TipRacunaRestController {

	@Autowired
	private TipRacunaRepository tipRacunaRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("tipRacuna")
	@ApiOperation(value="Vraća kolekciju svih tipova racuna iz baze podataka")
	public Collection<TipRacuna> getTipoveRacuna() {
		return tipRacunaRepository.findAll();
	}

	@GetMapping("tipRacuna/{id}")
	@ApiOperation(value="Vraća tip racuna na osnovu prosleđenog ID-ija")
	public TipRacuna getTipRacuna(@PathVariable("id") Integer id) {
		return tipRacunaRepository.getOne(id);
	}

	@GetMapping("tipRacunaNaziv/{naziv}")
	@ApiOperation(value="Vraća kolekciju tipova racuna na osnovu prosleđenog naziva tipa racuna")
	public Collection<TipRacuna> getTipRacunaByNaziv(@PathVariable("naziv") String naziv) {
		return tipRacunaRepository.findByNazivContainingIgnoreCase(naziv);
	}

	@PostMapping("tipRacuna")
	@ApiOperation(value="Dodaje novi tip racuna u bazu podataka")
	public ResponseEntity<TipRacuna> insertTipRacuna(@RequestBody TipRacuna tipRacuna) {
		if (!tipRacunaRepository.existsById(tipRacuna.getId())) {
			tipRacunaRepository.save(tipRacuna);
			return new ResponseEntity<TipRacuna>(HttpStatus.OK);
		}
		return new ResponseEntity<TipRacuna>(HttpStatus.CONFLICT);
	}

	@PutMapping("tipRacuna")
	@ApiOperation(value="Update-uje tip racuna iz baze podataka")
	public ResponseEntity<TipRacuna> updateTipRacuna(@RequestBody TipRacuna tipRacuna) {
		if (!tipRacunaRepository.existsById(tipRacuna.getId()))
			return new ResponseEntity<TipRacuna>(HttpStatus.CONFLICT);
		tipRacunaRepository.save(tipRacuna);
		return new ResponseEntity<TipRacuna>(HttpStatus.OK);
	}

	@DeleteMapping("tipRacuna/{id}")
	@ApiOperation(value="Briše tip racuna iz baze podataka (na osnovu prosleđene ID vrednosti)")
	public ResponseEntity<TipRacuna> deleteTipRacuna(@PathVariable Integer id) {
		if (!tipRacunaRepository.existsById(id))
			return new ResponseEntity<TipRacuna>(HttpStatus.NO_CONTENT);
		tipRacunaRepository.deleteById(id);
		if (id == -100)
			jdbcTemplate.execute(" INSERT INTO \"tip_racuna\" (\"id\", \"naziv\", \"oznaka\", \"opis\") "
					+ "VALUES (-100, 'Test tip racuna', '1', 'Test')");
		return new ResponseEntity<TipRacuna>(HttpStatus.OK);
	}
}
