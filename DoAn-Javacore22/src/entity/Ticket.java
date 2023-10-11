package entity;

import logichandle.LogicMain;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ticket {
    private LocalDateTime time;
    private User user;
    private CenimaRoom cenimaRoom;

    public Ticket(LocalDateTime time, User user, CenimaRoom cenimaRoom) {
        this.time = time;
        this.user = user;
        this.cenimaRoom = cenimaRoom;
    }
}
