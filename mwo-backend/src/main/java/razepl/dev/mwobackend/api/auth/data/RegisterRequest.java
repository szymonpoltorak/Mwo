package razepl.dev.mwobackend.api.auth.data;

import lombok.Builder;
import razepl.dev.mwobackend.entities.user.interfaces.Password;

@Builder
public record RegisterRequest(String name, String surname, String username, @Password String password) {
}
