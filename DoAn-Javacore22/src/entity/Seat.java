package entity;

public class Seat {
    private int idSeat;
    private static int nextIDset= 1;
    public Seat(int idSeat) {
        this.idSeat = idSeat;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "idSeat=" + idSeat +
                '}';
    }
}
