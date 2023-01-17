package one.digitalinnovation.personapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 100)
    private String firstname;
    @NotBlank
    @Size(min = 3, max = 100)
    private String lastname;
    @NotBlank
    @Size(min = 6, max = 120)
    private String password;
    @NotBlank
    @Size(max = 100)
    @Email
    private String email;
}
