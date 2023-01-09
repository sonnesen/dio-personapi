package one.digitalinnovation.personapi.dtos.mapper;

import one.digitalinnovation.personapi.api.dto.NewPersonRequest;
import one.digitalinnovation.personapi.api.dto.PersonResponse;
import one.digitalinnovation.personapi.api.dto.UpdatePersonRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import one.digitalinnovation.personapi.entities.Person;

@Mapper
public interface PersonMapper {

	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
	Person toModel(NewPersonRequest personRequest);

	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
	Person toModel(UpdatePersonRequest personRequest);

	PersonResponse toDTO(Person person);

}
