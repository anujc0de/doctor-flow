package com.slot_service.apis;

import com.slot_service.services.SlotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/slots")
public class SlotController {
    Logger log = LoggerFactory.getLogger(SlotController.class);
    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping
    public ResponseEntity<?> getSlots(@RequestParam("doctorIds") List<UUID> doctorIds) {

        return new ResponseEntity<>(slotService.getSlotsByDoctorId(doctorIds), HttpStatus.OK);

    }
    @GetMapping("/departments/{name}")
    public ResponseEntity<?> getSlots(@PathVariable("name") String departmentName) {

        return new ResponseEntity<>(slotService.getSlotsByDepartment(departmentName), HttpStatus.OK);

    }
    @GetMapping("/{slotId}/check-and-block")
    public   ResponseEntity<?> checkAndBlockSlot(@PathVariable("slotId") int slotId){
        return new ResponseEntity<>(slotService.checkAndBlockSlot(slotId), HttpStatus.OK);

    }
    @PostMapping("/{slotId}/confirm")
    public  ResponseEntity<?> confirmSlot(@PathVariable("slotId") int slotId){
        return new ResponseEntity<>(slotService.confirmSlot(slotId), HttpStatus.OK);

    }


}
