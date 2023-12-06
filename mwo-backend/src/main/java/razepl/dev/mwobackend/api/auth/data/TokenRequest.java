package razepl.dev.mwobackend.api.auth.data;

import lombok.Builder;

@Builder
public record TokenRequest(String authToken) {
}
