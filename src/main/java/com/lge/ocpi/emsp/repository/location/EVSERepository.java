package com.lge.ocpi.emsp.repository.location;

import com.lge.ocpi.emsp.model.entity.location.EvsesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EVSERepository extends JpaRepository<EvsesEntity,String> {
    @Query(value = "select * FROM evses WHERE ev_id =:id", nativeQuery=true)
    public EvsesEntity findEvses(@Param("id")String id);
}
