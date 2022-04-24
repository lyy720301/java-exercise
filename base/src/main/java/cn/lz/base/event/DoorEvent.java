package cn.lz.base.event;

import java.util.EventObject;

public class DoorEvent extends EventObject {

    int doorStatus;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DoorEvent(Object source) {
        super(source);
    }

    public DoorEvent(Object source, int doorStatus) {
        super(source);
        this.doorStatus = doorStatus;
    }

    public int getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(int doorStatus) {
        this.doorStatus = doorStatus;
    }
}
