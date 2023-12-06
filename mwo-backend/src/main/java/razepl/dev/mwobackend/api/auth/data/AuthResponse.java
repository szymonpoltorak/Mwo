package razepl.dev.mwobackend.api.auth.data;

import lombok.Builder;

@Builder
public record AuthResponse(String authToken, String refreshToken) {
}
