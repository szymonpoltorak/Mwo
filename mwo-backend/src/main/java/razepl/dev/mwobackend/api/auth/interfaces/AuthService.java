package razepl.dev.mwobackend.api.auth.interfaces;


import razepl.dev.mwobackend.api.auth.data.AuthResponse;
import razepl.dev.mwobackend.api.auth.data.LoginRequest;
import razepl.dev.mwobackend.api.auth.data.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest userRequest);

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refreshToken(String refreshToken);
}

