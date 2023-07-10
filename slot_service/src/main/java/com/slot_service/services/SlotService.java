package com.slot_service.services;

import com.common.dto.DoctorDto;
import com.common.response.DepartmentResponse;
import com.slot_service.entities.Slot;
import com.slot_service.entities.SlotStatus;
import com.slot_service.imp.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class SlotService {
    private final SlotRepository slotRepository;

    private final WebClient.Builder webClientBuilder;


    public List<Slot> getSlotsByDoctorId(List<UUID> doctorIds) {
        return slotRepository.findByDoctorIdIn(doctorIds);
    }

    public List<Slot> getSlotsByDepartment(String departmentName) {

        DepartmentResponse department = webClientBuilder.build().get()
                .uri("http://localhost:8082/departments/{name}", departmentName)
                .retrieve()
                .bodyToMono(DepartmentResponse.class)
                .block();

        assert department != null;
        var doctorIds = department.getDoctors().stream().map(DoctorDto::getId).toList();
        return getSlotsByDoctorId(doctorIds);


    }

    public Slot checkAndBlockSlot(int slotId) {

        var slot = slotRepository.findAvailableSlotById(slotId);

        if (slot != null) {
            slot.setBookingStatus(SlotStatus.PAYMENT_PENDING);
            Instant expiryTime = Instant.now().plus(10, ChronoUnit.MINUTES);
            slot.setExpiryTime(expiryTime);

            slotRepository.save(slot);
        } else {
            throw new RuntimeException("Slot not available or already booked");
        }
        return slot;


    }

    public Slot confirmSlot(int slotId) {

        var slot = slotRepository.findById(slotId).orElseThrow();
        Instant currentTime = Instant.now();
        slot.setBookingStatus(SlotStatus.BOOKED);
        slot.setExpiryTime(currentTime);
        return slotRepository.save(slot);
    }


}
