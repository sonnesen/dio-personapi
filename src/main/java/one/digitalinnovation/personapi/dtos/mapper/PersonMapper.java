package one.digitalinnovation.personapi.dtos.mapper;

import one.digitalinnovation.personapi.api.dto.PersonRequest;
import one.digitalinnovation.personapi.api.dto.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import one.digitalinnovation.personapi.entities.Person;

@Mapper
public interface PersonMapper {

	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
	Person toModel(PersonRequest personRequest);

	PersonResponse toDTO(Person person);

}
