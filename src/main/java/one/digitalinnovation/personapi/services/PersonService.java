package one.digitalinnovation.personapi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.digitalinnovation.personapi.api.dto.NewPersonRequest;
import one.digitalinnovation.personapi.api.dto.PersonResponse;
import one.digitalinnovation.personapi.api.dto.UpdatePersonRequest;
import one.digitalinnovation.personapi.dtos.mapper.PersonMapper;
import one.digitalinnovation.personapi.exceptions.PersonAlreadyExistsException;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.models.Person;
import one.digitalinnovation.personapi.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Transactional
    public PersonResponse create(NewPersonRequest personRequest) {
        verifyIfCpfExists(personRequest.getCpf());
        var personToSave = PersonMapper.INSTANCE.toModel(personRequest);
        var savedPerson = personRepository.save(personToSave);
        return PersonMapper.INSTANCE.toDTO(savedPerson);
    }

    public PersonResponse findById(UUID id) {
        var person = verifyIfIdExists(id);
        return PersonMapper.INSTANCE.toDTO(person);
    }

    public List<PersonResponse> findAll() {
        var allPeople = personRepository.findAll();
        return allPeople.stream().map(PersonMapper.INSTANCE::toDTO).toList();
    }

    @Transactional
    public PersonResponse updateById(UUID id, UpdatePersonRequest personRequest) {
        verifyIfIdExists(id);

        var personToUpdate = PersonMapper.INSTANCE.toModel(personRequest);
        personToUpdate.setId(id);
        var updatedPerson = personRepository.save(personToUpdate);

        return PersonMapper.INSTANCE.toDTO(updatedPerson);
    }

    @Transactional
    public void deleteById(UUID id) {
        verifyIfIdExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyIfIdExists(UUID id) {
        return personRepository.findById(id).orElseThrow(() -> {
            log.error("Person with ID {} not found.", id);
            return new PersonNotFoundException(id);
        });
    }

    private void verifyIfCpfExists(String cpf) {
        if (personRepository.existsByCpf(cpf)) {
            throw new PersonAlreadyExistsException(cpf);
        }
    }

}
