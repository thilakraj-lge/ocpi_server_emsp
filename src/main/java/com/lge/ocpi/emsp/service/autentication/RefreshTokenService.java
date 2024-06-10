package com.lge.ocpi.emsp.service.autentication;


import com.lge.ocpi.emsp.model.entity.autentication.RefreshToken;
import com.lge.ocpi.emsp.model.entity.autentication.UserInfo;
import com.lge.ocpi.emsp.repository.user.RefreshTokenRepository;
import com.lge.ocpi.emsp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userRepository.findByUsername(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(432000000)) //5days
                .build();
        return refreshTokenRepository.save(refreshToken);
    }


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;

    }

    public RefreshToken findByUserID(Long id) {
        return refreshTokenRepository.findRefreshId(id);
    }


    public String deleteById(Long id) {
        try {
            RefreshToken refreshToken = refreshTokenRepository.findRefreshId(id);
            RefreshToken refreshData = refreshTokenRepository.findById(refreshToken.getId()).orElseThrow(() -> new RuntimeException("Data not present in database"));
            refreshTokenRepository.delete(refreshData);
            return "Success";

        } catch (Exception e) {
            return "Failed" + e;
        }

    }


}
