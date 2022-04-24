package cn.lz.base.event.listener;

import cn.lz.base.event.DoorEvent;

import java.util.EventListener;

public interface DoorListener extends EventListener {

    void doorEvent(DoorEvent doorEvent);
}
