package brittaju.controller;

import brittaju.dto.AuthResponse;
import brittaju.dto.LoginRequest;
import brittaju.dto.RefreshRequest;
import brittaju.dto.RegisterRequest;
import brittaju.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> refreshAccess(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(authService.refreshAccessToken(request.refreshToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshRefresh(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(authService.refreshRefreshToken(request.refreshToken()));
    }
}


