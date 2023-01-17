package one.digitalinnovation.personapi.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {
    private int statusCode;
    private LocalDate timestamp;
    private String message;
    private String description;
}
