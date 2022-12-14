package com.musala.testdrones.danissalakheev.repository;

import com.musala.testdrones.danissalakheev.entity.Drone;
import com.musala.testdrones.danissalakheev.enums.DroneState;
import com.musala.testdrones.danissalakheev.repository.projection.DroneBatteryLevelProjection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DroneRepository extends JpaRepository<Drone, Long> {

    Optional<Drone> findFirstBySerialNumber(String serialNumber);

    @Query("select d from Drone d where d.batteryCapacity > :batteryCapacity and d.state = :droneState")
    List<Drone> findAllAvailableForLoad(int batteryCapacity, DroneState droneState, PageRequest pageRequest);

    @Query("select new com.musala.testdrones.danissalakheev.repository.projection" +
           ".DroneBatteryLevelProjection(d.serialNumber, d.state, d.batteryCapacity)" +
           "from Drone d")
    List<DroneBatteryLevelProjection> findAllBatteryProjections();

}
