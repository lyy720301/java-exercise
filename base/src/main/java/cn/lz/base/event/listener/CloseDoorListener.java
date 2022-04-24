package cn.lz.base.event.listener;

import cn.lz.base.event.DoorEvent;

public class CloseDoorListener implements DoorListener {

    @Override
    public void doorEvent(DoorEvent doorEvent) {
        int doorStatus = doorEvent.getDoorStatus();
        if (0 == doorStatus) {
            System.out.println("to close a door");
        }
    }
}
