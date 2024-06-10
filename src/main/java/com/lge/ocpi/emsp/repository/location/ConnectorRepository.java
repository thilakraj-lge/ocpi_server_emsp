package com.lge.ocpi.emsp.repository.location;

import com.lge.ocpi.emsp.model.entity.location.ConnectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectorRepository extends JpaRepository<ConnectorEntity, String> {
    @Query(value = "select * FROM connector WHERE id =:id", nativeQuery=true)
    public ConnectorEntity findConnector(@Param("id")String id);

    @Query(value = "select * FROM connector WHERE id =:connector_id AND fk_ev_id =:evse_id", nativeQuery=true)
    public ConnectorEntity findConnectorByEvse(@Param("evse_id")String evse_id,@Param("connector_id")String connector_id);
}
