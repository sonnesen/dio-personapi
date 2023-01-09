package one.digitalinnovation.personapi.utils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import one.digitalinnovation.personapi.api.dto.NewPersonRequest;
import one.digitalinnovation.personapi.entities.Person;

public class PersonUtils {

	private static final String FIRST_NAME = "Rodrigo";
	private static final String LAST_NAME = "Peleias";
	private static final String CPF_NUMBER = "369.333.878-79";
	private static final UUID PERSON_ID = UUID.randomUUID();
	private static final LocalDate BIRTH_DATE = LocalDate.of(2010, 10, 1);

	public static NewPersonRequest createFakeRequest() {
		return new NewPersonRequest()
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate(LocalDate.of(2010,4,4))
				.phones(Collections.singletonList(PhoneUtils.createFakeRequest()));
	}

	public static Person createFakeEntity() {
		return Person.builder()
				.id(PERSON_ID)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate(BIRTH_DATE)
				.phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
				.build();
	}
}
