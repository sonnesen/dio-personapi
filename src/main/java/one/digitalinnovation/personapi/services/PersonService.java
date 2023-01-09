package one.digitalinnovation.personapi.services;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.api.dto.PersonRequest;
import one.digitalinnovation.personapi.api.dto.PersonResponse;
import one.digitalinnovation.personapi.dtos.mapper.PersonMapper;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

	private final PersonRepository personRepository;

	public PersonResponse create(PersonRequest personRequest) {
		var personToSave = PersonMapper.INSTANCE.toModel(personRequest);
		var savedPerson = personRepository.save(personToSave);
		return PersonMapper.INSTANCE.toDTO(savedPerson);
	}

	public PersonResponse findById(Long id) throws PersonNotFoundException {
		var person = verifyIfExists(id);
		return PersonMapper.INSTANCE.toDTO(person);
	}

	public List<PersonResponse> findAll() {
		var allPeople = personRepository.findAll();
		return allPeople.stream().map(PersonMapper.INSTANCE::toDTO).toList();
	}

	public PersonResponse updateById(Long id, PersonRequest personRequest) throws PersonNotFoundException {
		verifyIfExists(id);

		var personToUpdate = PersonMapper.INSTANCE.toModel(personRequest);
		personToUpdate.setId(id);
		var updatedPerson = personRepository.save(personToUpdate);

		return PersonMapper.INSTANCE.toDTO(updatedPerson);
	}

	public void deleteById(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		personRepository.deleteById(id);
	}

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}

}
