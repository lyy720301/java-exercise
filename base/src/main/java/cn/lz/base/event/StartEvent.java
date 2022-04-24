package cn.lz.base.event;

import cn.lz.base.event.listener.CloseDoorListener;
import cn.lz.base.event.listener.DoorListener;
import cn.lz.base.event.listener.OpenDoorListener;
import com.google.common.collect.Lists;

import java.util.List;

public class StartEvent {

    public static List<DoorListener> getAllDoorListener() {
        return Lists.newArrayList(new CloseDoorListener(), new OpenDoorListener());
    }

    public static void main(String[] args) {
        List<DoorListener> allDoorListener = getAllDoorListener();
        DoorEvent doorEvent = new DoorEvent("open", 1);
        allDoorListener.forEach(doorListener -> doorListener.doorEvent(doorEvent));
    }
}
