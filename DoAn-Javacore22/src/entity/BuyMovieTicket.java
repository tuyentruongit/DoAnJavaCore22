package entity;

import java.io.Serializable;
import java.util.PrimitiveIterator;

public class BuyMovieTicket implements Serializable {
    private int totalAmount;
    private ShowTimes showTimes;

    public BuyMovieTicket(int totalAmount, ShowTimes showTimes) {
        this.totalAmount = totalAmount;
        this.showTimes = showTimes;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int   totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ShowTimes getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(ShowTimes showTimes) {
        this.showTimes = showTimes;
    }


    @Override
    public String toString() {
        return "BuyMovieTicket{" +
                "totalAmount=" + totalAmount +
                ", showTimes=" + showTimes +
                '}';
    }
}
