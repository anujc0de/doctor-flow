package com.slot_service.repos;

import com.slot_service.entities.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface SlotRepository extends JpaRepository<Slot, Integer> {

    List<Slot> findByDoctorIdIn(List<UUID> doctorIds);

    @Query(value = "SELECT * FROM slots s WHERE s.id =:slotId AND s.booking_status = 'available' AND s.availability = true AND (s.expiry_time IS NULL OR s.expiry_time + INTERVAL '10 minutes' < CURRENT_TIMESTAMP)",nativeQuery = true)
    Slot findAvailableSlotById(@Param("slotId") int slotId);
}
