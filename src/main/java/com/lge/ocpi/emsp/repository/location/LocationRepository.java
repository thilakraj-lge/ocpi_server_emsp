package com.lge.ocpi.emsp.repository.location;

import com.lge.ocpi.emsp.model.entity.location.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, String> {

    @Query(value = "select * FROM location WHERE id =:id", nativeQuery = true)
    public LocationEntity findLocation(@Param("id") String id);

/*    @Query(value = "SELECT *, ( 6371 * acos( cos( radians(:lat) ) * " +
            "cos( radians( lat ) ) * cos( radians( lng ) - radians(:lng) ) " +
            "+ sin( radians(:lat) ) * sin( radians( lat ) ))) AS distance " +
            "FROM location HAVING distance < :distance", nativeQuery = true)*/

    @Query(value = "SELECT *,( 6371 * acos( cos( radians(:lat) ) * cos( radians(JSON_EXTRACT(coordinates, '$.latitude')) ) * cos( radians(JSON_EXTRACT(coordinates, '$.longitude')) - radians(:lng) ) + sin( radians(:lat) ) * sin( radians(JSON_EXTRACT(coordinates, '$.latitude')) ) ) ) AS distance FROM location HAVING distance < :distance ORDER BY distance ASC;", nativeQuery = true)
    List<LocationEntity> findByLocationWithinRadius(@Param("lat") double lat,
                                                    @Param("lng") double lng,
                                                    @Param("distance") double distance);
}
