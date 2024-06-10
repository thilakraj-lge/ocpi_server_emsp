package com.lge.ocpi.emsp.repository;

import com.lge.ocpi.emsp.model.entity.cdr.Cdr;
import com.lge.ocpi.emsp.model.entity.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,String> {
    @Query(value = "select * FROM token WHERE uid =:uid", nativeQuery=true)
    public Token findTokenByID(@Param("uid")String uid);
}
