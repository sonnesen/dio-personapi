package one.digitalinnovation.personapi.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.api.PeopleApi;
import one.digitalinnovation.personapi.api.dto.NewPersonRequest;
import one.digitalinnovation.personapi.api.dto.PersonResponse;
import one.digitalinnovation.personapi.api.dto.UpdatePersonRequest;
import one.digitalinnovation.personapi.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController implements PeopleApi {

    private final PersonService personService;

	@Override
	public ResponseEntity<PersonResponse> create(@RequestBody @Valid NewPersonRequest personRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(personRequest));
	}

	@Override
	public ResponseEntity<PersonResponse> findById(@PathVariable Long id) {
		return ResponseEntity.ok(personService.findById(id));
	}

	@Override
	public ResponseEntity<List<PersonResponse>> findAll() {
		return ResponseEntity.ok(personService.findAll());
	}

	@Override
	public ResponseEntity<PersonResponse> updateById(@PathVariable Long id,
													 @RequestBody @Valid UpdatePersonRequest personRequest) {
		return ResponseEntity.ok(personService.updateById(id, personRequest));
	}

	@Override
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		personService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
