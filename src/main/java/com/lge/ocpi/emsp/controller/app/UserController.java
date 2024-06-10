package com.lge.ocpi.emsp.controller.app;

import com.lge.ocpi.emsp.helper.OcpiStatusCode;
import com.lge.ocpi.emsp.helper.ResponseFormat;
import com.lge.ocpi.emsp.model.dto.location.LocationDto;
import com.lge.ocpi.emsp.model.dto.userDetails.*;
import com.lge.ocpi.emsp.model.entity.autentication.RefreshToken;
import com.lge.ocpi.emsp.model.entity.autentication.UserInfo;
import com.lge.ocpi.emsp.service.autentication.JwtService;
import com.lge.ocpi.emsp.service.autentication.RefreshTokenService;
import com.lge.ocpi.emsp.service.autentication.UserService;
import jakarta.validation.Valid;
import com.lge.ocpi.emsp.service.credentials.CredentialsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    RefreshTokenService refreshTokenService;


    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseFormat<?>> saveUser(@Valid @RequestBody UserRequest userRequest) {

        ResponseFormat<?> responseFormat;
        try {
            UserResponse user = userService.getUserByID(userRequest.getUsername());
            if (user != null) {
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.CLIENT_ERROR, "User already present!");

            } else {
                UserResponse userResponse = userService.saveUser(userRequest);
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.SUCCESS, userResponse);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(responseFormat);
    }

  /*  @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        try {
            List<UserResponse> userResponses = userService.getAllUser();
            return ResponseEntity.ok(userResponses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/


    @PostMapping("/profile")
    public ResponseEntity<ResponseFormat<?>> getUserProfile(@RequestParam(value = "username") String username) {

        ResponseFormat<?> responseFormat;
        try {
            UserResponse userResponse = userService.getUserByID(username);
            if (userResponse == null) {
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.CLIENT_ERROR, "Invalid User!");

            } else {

                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.SUCCESS, userResponse);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(responseFormat);


    }

  /*  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/test")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    @PostMapping("/login")
    public ResponseEntity<ResponseFormat<?>> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {

       /* UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePassword);
        authentication.isAuthenticated();
        try {
            log.info("Autentication" + authentication.isAuthenticated());
        } catch (Exception e) {
            log.info("Autentication " + e + "  " + authentication.isAuthenticated());
        }*/


        ResponseFormat<?> responseFormat = new ResponseFormat<>();
        try {
            UserResponse userResponse = userService.getUserByID(authRequestDTO.getUsername());
            if (userResponse != null) {
                RefreshToken refreshToken = refreshTokenService.findByUserID(userResponse.getId());
                if (refreshToken != null) {
                    JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                            .message("Authentication Successful !!")
                            .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                            .token(refreshToken.getToken())
                            .build();
                    responseFormat = new ResponseFormat<>()
                            .build(OcpiStatusCode.SUCCESS, jwtResponseDTO);
                } else {
                    RefreshToken refreshTokennew = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
                    JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                            .message("Authentication Successful !!")
                            .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                            .token(refreshTokennew.getToken())
                            .build();
                    responseFormat = new ResponseFormat<>()
                            .build(OcpiStatusCode.SUCCESS, jwtResponseDTO);
                }

            } else {
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.INVALID_PARAMETERS, "Invalid username and password!");
            }
        } catch (Exception e) {
            responseFormat = new ResponseFormat<>()
                    .build(OcpiStatusCode.CLIENT_ERROR, "Error!! "+e);
            //  throw new UsernameNotFoundException("invalid user request..!!");

        }

        return ResponseEntity.ok(responseFormat);
    }

      /*  Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        try {
            if (authentication.isAuthenticated()) {
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
                JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                        .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                        .token(refreshToken.getToken())
                        .build();
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.SUCCESS, jwtResponseDTO);

                return ResponseEntity.ok(responseFormat);
            } else {
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.INVALID_PARAMETERS, "Invalid username and password!");
                //  throw new UsernameNotFoundException("invalid user request..!!");
                return ResponseEntity.ok(responseFormat);
            }
        } catch (Exception e) {
            UserResponse userResponse = userService.getUserByID(authRequestDTO.getUsername());
            String status = refreshTokenService.deleteById(userResponse.getId());
            if (status.equals("Success")) {

                RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
                JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                        .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                        .token(refreshToken.getToken())
                        .message("Authentication Successful !!")
                        .build();
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.SUCCESS, jwtResponseDTO);
            } else {
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.CLIENT_ERROR, "Failed!!");
                return ResponseEntity.ok(responseFormat);
            }
            return ResponseEntity.ok(responseFormat);*/



      /*  try {
            ResponseFormat<?> responseFormat = new ResponseFormat<>();
            String authenticationFlag = "Not Authenticated ";
            try {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
                if (authentication.isAuthenticated()) {
                    authenticationFlag = "Authenticated ";
                    RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
                    JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
                    jwtResponseDTO.setAccessToken(jwtService.GenerateToken(authRequestDTO.getUsername()));
                    jwtResponseDTO.setToken(refreshToken.getToken());
                    jwtResponseDTO.setMessage(" Login Successful!!");
                    responseFormat = new ResponseFormat<>()
                            .build(OcpiStatusCode.SUCCESS, jwtResponseDTO);
                }
            } catch (BadCredentialsException e) {
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.INVALID_PARAMETERS, "Invalid username and password!");
            }
            return ResponseEntity.ok(responseFormat);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/











       /* return new ResponseEntity(JwtResponseDTO.builder()
                .message(authenticationFlag + " Login Successful!!")
                .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                .token(refreshToken.getToken()).build(), HttpStatus.OK);*/


    @PostMapping("/refreshToken")
    public ResponseEntity<ResponseFormat<?>> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        ResponseFormat<?> responseFormat;
        try {
            JwtResponseDTO jwtResponseDTO = refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUserInfo)
                    .map(userInfo -> {
                        String accessToken = jwtService.GenerateToken(userInfo.getUsername());
                        return JwtResponseDTO.builder()
                                .accessToken(accessToken)
                                .token(refreshTokenRequestDTO.getToken()).build();
                    }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
            if (jwtResponseDTO == null) {
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.CLIENT_ERROR, "Not Valid token!! ");

            } else {

                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.SUCCESS, jwtResponseDTO);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(responseFormat);

    }


    @PostMapping("/signout/{id}")
    public ResponseEntity<ResponseFormat<?>> logoutUser(@PathVariable(value = "id") Long id) {
        ResponseFormat<?> responseFormat = null;

        try {
            String status = refreshTokenService.deleteById(id);
            if (status.equals("Success")) {
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.SUCCESS, "Sign out Successful");
                return ResponseEntity.ok(responseFormat);
            } else {
                responseFormat = new ResponseFormat<>()
                        .build(OcpiStatusCode.CLIENT_ERROR, "Sign out Failed");
                return ResponseEntity.ok(responseFormat);
            }
        } catch (Exception e) {

            responseFormat = new ResponseFormat<>()
                    .build(OcpiStatusCode.CLIENT_ERROR, "Sign out Failed");
            return ResponseEntity.ok(responseFormat);
        }


    }


}
