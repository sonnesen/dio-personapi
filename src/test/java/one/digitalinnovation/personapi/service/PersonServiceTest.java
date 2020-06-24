package one.digitalinnovation.personapi.service;

import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import one.digitalinnovation.personapi.dtos.request.PersonDTO;
import one.digitalinnovation.personapi.dtos.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.repositories.PersonRepository;
import one.digitalinnovation.personapi.services.PersonService;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private PersonService personService;
	
	@Test
	void testGivenPersonDTOThenReturnSavedMessage() {
		PersonDTO personDTO = createFakeDTO();
		Person expectedSavedPerson = createFakeEntity();
		
		when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);
		
		MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
		MessageResponseDTO successMessage = personService.create(personDTO);
		
		assertEquals(expectedSuccessMessage, successMessage);
	}
	
	private MessageResponseDTO createExpectedMessageResponse(Long id) {
		return MessageResponseDTO
				.builder()
				.message("Created person with ID " + id)
				.build();
	}

}
