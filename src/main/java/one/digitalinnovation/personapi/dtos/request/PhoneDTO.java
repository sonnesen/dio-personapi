package one.digitalinnovation.personapi.dtos.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.PhoneType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PhoneType type;
	
	@NotEmpty
	@Size(min = 14, max = 15)
	private String number;
}
