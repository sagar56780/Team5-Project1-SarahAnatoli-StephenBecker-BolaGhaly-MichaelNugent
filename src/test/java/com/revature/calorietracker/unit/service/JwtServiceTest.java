package com.revature.calorietracker.unit.service;

import com.revature.calorietracker.dto.UserTokenDTO;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.auth.Role;
import com.revature.calorietracker.models.auth.Token;
import com.revature.calorietracker.models.auth.TokenType;
import com.revature.calorietracker.repos.TokenRepo;
import com.revature.calorietracker.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;
    @Mock
    private TokenRepo tokenRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateToken_ShouldReturnValidToken() {
        // Arrange
        UserTokenDTO userTokenDTO = new UserTokenDTO(1L, "johndoe", Role.USER);

        // Act
        String token = jwtService.generateToken(userTokenDTO);

        // Assert
        assertNotNull(token);
    }

    @Test
    void extractUsername_ShouldReturnCorrectUsername() {
        // Arrange
        UserTokenDTO userTokenDTO = new UserTokenDTO(1L, "johndoe", Role.USER);
        String token = jwtService.generateToken(userTokenDTO);

        // Act
        String username = jwtService.extractUsername(token);

        // Assert
        assertEquals("johndoe", username);
    }

    @Test
    void isTokenValid_ShouldReturnTrueForValidToken() {
        // Arrange
        UserTokenDTO userTokenDTO = new UserTokenDTO(1L, "johndoe", Role.USER);
        String tokenStr = jwtService.generateToken(userTokenDTO);
        Optional<Token> optTokenObj = Optional.of(new Token(1,tokenStr, TokenType.BEARER,false,false,new User()));
        when(tokenRepo.findByToken(tokenStr)).thenReturn(optTokenObj);

        // Act
        boolean isValid = jwtService.isTokenValid(tokenStr);

        // Assert
        assertTrue(isValid);
    }
}
