package com.lge.ocpi.emsp.repository;

import com.lge.ocpi.emsp.model.entity.cdr.Cdr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CdrRepository extends JpaRepository<Cdr,Long> {
    @Query(value = "select * FROM cdr WHERE cdr_id =:id", nativeQuery=true)
    public Cdr findCdrById(@Param("id")String id);
}
