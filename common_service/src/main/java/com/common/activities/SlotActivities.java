package com.common.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SlotActivities {
    @ActivityMethod
    void completeSlot(int slotId);
    @ActivityMethod
    void failSlot(int slotId);

    @ActivityMethod
    void checkAndBlock(int slotId);
    @ActivityMethod
    void unblock(int slotId);



}
