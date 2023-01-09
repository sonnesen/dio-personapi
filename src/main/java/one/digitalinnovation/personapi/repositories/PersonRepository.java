package one.digitalinnovation.personapi.repositories;

import one.digitalinnovation.personapi.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    boolean existsByCpf(String cpf);
}
