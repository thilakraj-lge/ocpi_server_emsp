package com.lge.ocpi.emsp.repository.user;


import com.lge.ocpi.emsp.model.entity.autentication.RefreshToken;
import com.lge.ocpi.emsp.model.entity.location.LocationEntity;
import com.lge.ocpi.emsp.repository.userHelpers.RefreshableCRUDRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends RefreshableCRUDRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);
    @Query(value = "select * FROM refresh_tokens WHERE user_id =:id", nativeQuery = true)
    public RefreshToken findRefreshId(@Param("id") Long id);
}
