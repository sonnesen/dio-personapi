package one.digitalinnovation.personapi.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;
}
