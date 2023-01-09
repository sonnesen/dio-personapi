package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.api.dto.NewPersonRequest;
import one.digitalinnovation.personapi.api.dto.PersonResponse;
import one.digitalinnovation.personapi.dtos.mapper.PersonMapper;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.repositories.PersonRepository;
import one.digitalinnovation.personapi.services.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;
import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        NewPersonRequest personRequest = createFakeRequest();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        PersonResponse personResponse = personService.create(personRequest);
        PersonResponse expectedResponse = PersonMapper.INSTANCE.toDTO(expectedSavedPerson);

        assertEquals(expectedResponse, personResponse);
    }


}
