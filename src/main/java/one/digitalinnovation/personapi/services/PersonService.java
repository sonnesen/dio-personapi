package one.digitalinnovation.personapi.services;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import one.digitalinnovation.personapi.dtos.mapper.PersonMapper;
import one.digitalinnovation.personapi.dtos.request.PersonDTO;
import one.digitalinnovation.personapi.dtos.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.repositories.PersonRepository;

@Service
@RequiredArgsConstructor
public class PersonService {

	private final PersonRepository personRepository;

	public MessageResponseDTO create(PersonDTO personDTO) {
		var personToSave = PersonMapper.INSTANCE.toModel(personDTO);
		var savedPerson = personRepository.save(personToSave);

		return createMessageResponse(savedPerson.getId(), "Created person with ID ");
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		var person = verifyIfExists(id);

		return PersonMapper.INSTANCE.toDTO(person);
	}

	public List<PersonDTO> listAll() {
		var allPeople = personRepository.findAll();

		return allPeople.stream().map(PersonMapper.INSTANCE::toDTO).toList();
	}

	public MessageResponseDTO update(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);

		var personToUpdate = PersonMapper.INSTANCE.toModel(personDTO);
		var updatedPerson = personRepository.save(personToUpdate);

		return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
	}

	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		personRepository.deleteById(id);
	}

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}

	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO.builder().message(message + id).build();
	}

}
