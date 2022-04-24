package cn.lz.base.event.listener;

import cn.lz.base.event.DoorEvent;

public class OpenDoorListener implements DoorListener {

    @Override
    public void doorEvent(DoorEvent doorEvent) {
        int doorStatus = doorEvent.getDoorStatus();
        if (1 == doorStatus) {
            System.out.println("to open a door");
        }
    }
}
