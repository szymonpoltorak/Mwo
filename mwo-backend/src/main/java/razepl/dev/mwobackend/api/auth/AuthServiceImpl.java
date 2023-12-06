package razepl.dev.mwobackend.api.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import razepl.dev.mwobackend.api.auth.data.AuthResponse;
import razepl.dev.mwobackend.api.auth.data.LoginRequest;
import razepl.dev.mwobackend.api.auth.data.RegisterRequest;
import razepl.dev.mwobackend.api.auth.interfaces.AuthService;
import razepl.dev.mwobackend.config.jwt.interfaces.JwtService;
import razepl.dev.mwobackend.config.jwt.interfaces.TokenManagerService;
import razepl.dev.mwobackend.entities.user.User;
import razepl.dev.mwobackend.entities.user.interfaces.UserRepository;
import razepl.dev.mwobackend.exceptions.auth.InvalidTokenException;
import razepl.dev.mwobackend.exceptions.auth.TokenDoesNotExistException;
import razepl.dev.mwobackend.exceptions.auth.UserAlreadyExistsException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final String USER_NOT_EXIST_MESSAGE = "Such user does not exist!";
    private static final String BUILDING_TOKEN_RESPONSE_MESSAGE = "Building token response for user : {}";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenManagerService tokenManager;
    private final JwtService jwtService;

    @Override
    public final AuthResponse register(@Valid RegisterRequest registerRequest) {
        log.info("Registering user with data: \n{}", registerRequest);

        String password = validateUserRegisterData(registerRequest);

        User user = User
                .builder()
                .name(registerRequest.name())
                .username(registerRequest.username())
                .surname(registerRequest.surname())
                .password(passwordEncoder.encode(password))
                .build();
        createUserWithEncodedPassword(user);

        log.info(BUILDING_TOKEN_RESPONSE_MESSAGE, user);

        return tokenManager.buildTokensIntoResponse(user, false);
    }

    @Override
    public final AuthResponse login(LoginRequest loginRequest) {
        log.info("Logging user with data: \n{}", loginRequest);

        String username = loginRequest.username();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, loginRequest.password())
        );

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(USER_NOT_EXIST_MESSAGE)
        );
        log.info(BUILDING_TOKEN_RESPONSE_MESSAGE, user);

        return tokenManager.buildTokensIntoResponse(user, true);
    }

    @Override
    public final AuthResponse refreshToken(String refreshToken) {
        log.info("Refresh token : {}", refreshToken);

        User user = validateRefreshTokenData(refreshToken);
        String authToken = jwtService.generateToken(user);

        log.info("New auth token : {}\nFor user : {}", authToken, user);

        tokenManager.revokeUserTokens(user);

        tokenManager.saveUsersToken(authToken, user);

        return tokenManager.buildTokensIntoResponse(authToken, refreshToken);
    }
    private void createUserWithEncodedPassword(@Valid User user) {
        userRepository.save(user);
    }

    private String validateUserRegisterData(RegisterRequest registerRequest) {
        String password = registerRequest.password();

        Optional<User> existingUser = userRepository.findByUsername(registerRequest.username());

        if (existingUser.isPresent()) {
            log.error("User already exists! Found user: {}", existingUser.get());

            throw new UserAlreadyExistsException("User already exists!");
        }
        return password;
    }

    private User validateRefreshTokenData(String refreshToken) {
        if (refreshToken == null) {
            throw new TokenDoesNotExistException("Token does not exist!");
        }
        Optional<String> usernameOptional = jwtService.getUsernameFromToken(refreshToken);

        if (usernameOptional.isEmpty()) {
            throw new UsernameNotFoundException(USER_NOT_EXIST_MESSAGE);
        }
        String username = usernameOptional.get();

        log.info("User of username : {}", username);

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(USER_NOT_EXIST_MESSAGE)
        );

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new InvalidTokenException("Token is not valid!");
        }
        return user;
    }
}
