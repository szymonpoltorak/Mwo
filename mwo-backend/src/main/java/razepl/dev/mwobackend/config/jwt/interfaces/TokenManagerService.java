package razepl.dev.mwobackend.config.jwt.interfaces;


import razepl.dev.mwobackend.api.auth.data.AuthResponse;
import razepl.dev.mwobackend.entities.user.User;

public interface TokenManagerService {
    AuthResponse buildTokensIntoResponse(String authToken, String refreshToken);

    AuthResponse buildTokensIntoResponse(User user, boolean shouldBeRevoked);

    void revokeUserTokens(User user);

    void revokeUserTokens(String username);

    void saveUsersToken(String jwtToken, User user);

    void saveUsersToken(String jwtToken, String username);
}
