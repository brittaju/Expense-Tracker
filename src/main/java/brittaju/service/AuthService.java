package brittaju.service;

import brittaju.dto.AuthResponse;
import brittaju.dto.LoginRequest;
import brittaju.dto.RegisterRequest;
import brittaju.entity.UserEntity;
import brittaju.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(request.fullName());
        userEntity.setEmail(request.email());
        userEntity.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(userEntity);

        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);
        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserEntity userEntity = userRepository.findByEmail(request.email())
                .orElseThrow();

        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);
        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshAccessToken(String refreshToken) {
        if (jwtService.isRefreshTokenValid(refreshToken)) {
            throw new BadCredentialsException("Invalid refresh token");
        }
        UserEntity userEntity = userRepository.findByEmail(jwtService.extractUsername(refreshToken))
                .orElseThrow(() -> new BadCredentialsException("User not found"));
        return new AuthResponse(
                jwtService.generateAccessToken(userEntity),
                refreshToken
        );
    }

    public AuthResponse refreshRefreshToken(String refreshToken) {
        if (jwtService.isRefreshTokenValid(refreshToken)) {
            throw new BadCredentialsException("Invalid refresh token");
        }
        UserEntity userEntity = userRepository.findByEmail(jwtService.extractUsername(refreshToken))
                .orElseThrow(() -> new BadCredentialsException("User not found"));
        return new AuthResponse(
                jwtService.generateAccessToken(userEntity),
                jwtService.generateRefreshToken(userEntity)
        );
    }
}

