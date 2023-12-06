package razepl.dev.mwobackend.api.auth.interfaces;


import razepl.dev.mwobackend.api.auth.data.AuthResponse;
import razepl.dev.mwobackend.api.auth.data.LoginRequest;
import razepl.dev.mwobackend.api.auth.data.RegisterRequest;

public interface AuthController {
    AuthResponse registerUser(RegisterRequest registerRequest);

    AuthResponse loginUser(LoginRequest loginRequest);

    AuthResponse refreshUserToken(String refreshToken);
}
