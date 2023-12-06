package razepl.dev.mwobackend.api.auth.data;

import lombok.Builder;

@Builder
public record LoginRequest(String username, String password) {
}
