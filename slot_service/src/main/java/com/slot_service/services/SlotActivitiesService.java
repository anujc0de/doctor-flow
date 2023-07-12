package com.slot_service.services;

import com.slot_service.entities.SlotStatus;
import com.slot_service.repos.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE)
public class SlotActivitiesService {
    private final SlotRepository slotRepository;

    public void confirmSlot(int slotId) {

        var slot = slotRepository.findById(slotId).orElseThrow();
        Instant currentTime = Instant.now();
        slot.setBookingStatus(SlotStatus.BOOKED);
        slot.setExpiryTime(currentTime);
        slotRepository.save(slot);
    }

    public void failSlot(int slotId) {

        var slot = slotRepository.findById(slotId).orElseThrow();
        slot.setBookingStatus(SlotStatus.PAYMENT_PENDING);
        slotRepository.save(slot);
    }
    public void checkAndBlockSlot(int slotId) {

        var slot = slotRepository.findAvailableSlotById(slotId);

        if (slot != null) {
            slot.setBookingStatus(SlotStatus.PAYMENT_PENDING);
            Instant expiryTime = Instant.now().plus(10, ChronoUnit.MINUTES);
            slot.setExpiryTime(expiryTime);

            slotRepository.save(slot);
        } else {
            throw new RuntimeException("Slot not available or already booked");
        }


    }
    public void unblockSlot(int slotId){
        var slot = slotRepository.findById(slotId).orElseThrow();
        Instant currentTime = Instant.now();
        slot.setBookingStatus(SlotStatus.Available);
        slot.setExpiryTime(currentTime);
        slotRepository.save(slot);
    }

}
