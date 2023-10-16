package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ShowTimes implements Serializable {
  private Room room;
  private List<ShowTimeMovie> showTimeMovieList;

    public ShowTimes(Room room, List<ShowTimeMovie> showTimeMovieList) {
        this.room = room;
        this.showTimeMovieList = showTimeMovieList;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<ShowTimeMovie> getShowTimeMovieList() {
        return showTimeMovieList;
    }

    public void setShowTimeMovieList(List<ShowTimeMovie> showTimeMovieList) {
        this.showTimeMovieList = showTimeMovieList;
    }

    @Override
    public String toString() {
        return "ShowTimes{" +
                "room=" + room +
                ", showTimeMovieList=" + showTimeMovieList +
                '}';
    }
}
