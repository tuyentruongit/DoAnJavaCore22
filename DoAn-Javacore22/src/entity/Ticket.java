package entity;

import logichandle.LogicMain;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ticket {
    private LocalDateTime time;
    private User user;
    private CenimaRoom cenimaRoom;

    public Ticket(LocalDateTime time, User user, CenimaRoom cenimaRoom) {
        this.time = LocalDateTime.now();
        this.user = user;
        this.cenimaRoom = cenimaRoom;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CenimaRoom getCenimaRoom() {
        return cenimaRoom;
    }

    public void setCenimaRoom(CenimaRoom cenimaRoom) {
        this.cenimaRoom = cenimaRoom;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "time=" + time +
                ", user=" + user +
                ", cenimaRoom=" + cenimaRoom +
                '}';
    }
}
