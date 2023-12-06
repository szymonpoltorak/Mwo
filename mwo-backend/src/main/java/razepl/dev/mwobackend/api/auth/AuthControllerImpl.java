package razepl.dev.mwobackend.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.mwobackend.api.auth.constants.AuthMappings;
import razepl.dev.mwobackend.api.auth.data.AuthResponse;
import razepl.dev.mwobackend.api.auth.data.LoginRequest;
import razepl.dev.mwobackend.api.auth.data.RegisterRequest;
import razepl.dev.mwobackend.api.auth.interfaces.AuthController;
import razepl.dev.mwobackend.api.auth.interfaces.AuthService;

import static razepl.dev.mwobackend.config.constants.Matchers.AUTH_MAPPING;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = AUTH_MAPPING)
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    @PostMapping(value = AuthMappings.REGISTER_MAPPING)
    @ResponseStatus(value = HttpStatus.CREATED)
    public final AuthResponse registerUser(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @Override
    @PostMapping(value = AuthMappings.LOGIN_MAPPING)
    public final AuthResponse loginUser(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Override
    @PostMapping(value = AuthMappings.REFRESH_MAPPING)
    public final AuthResponse refreshUserToken(@RequestParam String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}
