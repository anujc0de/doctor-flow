package com.slot_service.impl;

import com.common.activities.SlotActivities;
import com.slot_service.services.SlotActivitiesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SlotActivitiesImpl implements SlotActivities {
    private final SlotActivitiesService slotActivitiesService;




    @Override
    public void completeSlot(int slotId) {
        slotActivitiesService.confirmSlot(slotId);

    }

    @Override
    public void failSlot(int slotId) {
        slotActivitiesService.failSlot(slotId);

    }

    @Override
    public void checkAndBlock(int slotId) {
        slotActivitiesService.checkAndBlockSlot(slotId);

    }

    @Override
    public void unblock(int slotId) {
        slotActivitiesService.unblockSlot(slotId);

    }
}
