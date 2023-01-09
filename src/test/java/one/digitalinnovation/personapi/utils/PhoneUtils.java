package one.digitalinnovation.personapi.utils;

import one.digitalinnovation.personapi.api.dto.NewPersonPhoneRequest;
import one.digitalinnovation.personapi.entities.Phone;
import one.digitalinnovation.personapi.enums.PhoneType;

public class PhoneUtils {

	private static final String PHONE_NUMBER = "(11) 99999-9999";
	private static final long PHONE_ID = 1L;
	
	public static NewPersonPhoneRequest createFakeRequest() {
		return new NewPersonPhoneRequest()
				.number(PHONE_NUMBER)
				.type(NewPersonPhoneRequest.TypeEnum.MOBILE);
	}
	
	public static Phone createFakeEntity() {
		return Phone.builder()
				.id(PHONE_ID)
				.number(PHONE_NUMBER)
				.type(PhoneType.MOBILE)
				.build();
	}
}
