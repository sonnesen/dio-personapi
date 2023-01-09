package one.digitalinnovation.personapi.utils;

import one.digitalinnovation.personapi.api.dto.PhoneRequest;
import one.digitalinnovation.personapi.entities.Phone;
import one.digitalinnovation.personapi.enums.PhoneType;

public class PhoneUtils {

	private static final String PHONE_NUMBER = "(11) 99999-9999";
	private static final long PHONE_ID = 1L;
	
	public static PhoneRequest createFakeRequest() {
		return new PhoneRequest()
				.number(PHONE_NUMBER)
				.type(PhoneRequest.TypeEnum.MOBILE);
	}
	
	public static Phone createFakeEntity() {
		return Phone.builder()
				.id(PHONE_ID)
				.number(PHONE_NUMBER)
				.type(PhoneType.MOBILE)
				.build();
	}
}
