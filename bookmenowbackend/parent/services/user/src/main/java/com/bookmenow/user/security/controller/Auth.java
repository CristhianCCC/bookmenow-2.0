package com.bookmenow.user.security.controller;
import com.bookmenow.user.exceptions.exceptions.BusinessRuleException;
import com.bookmenow.user.exceptions.validators.UserValidators;
import com.bookmenow.user.security.dto.AuthRequest;
import com.bookmenow.user.security.dto.AuthResponse;
import com.bookmenow.user.security.service.CustomUserDetailsService;
import com.bookmenow.user.security.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class Auth {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param request the user's credentials
     * @return the JWT token if authentication is successful
     */
    @Operation(summary = "Authenticate user", description = "Authenticate a user using email and password and receive a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful authentication",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        try {
            UserValidators.validateCredentials(request);

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());

            List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .collect(Collectors.toList());

            String token = jwtService.generateToken(
                    Map.of("role", roles),
                    userDetails.getUsername()
            );

            return new AuthResponse(token);

        } catch (BusinessRuleException ex) {
            throw ex;
        } catch (AuthenticationException ex) {
            throw new BusinessRuleException(null, "AUTH_INVALID", HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
    }
}
