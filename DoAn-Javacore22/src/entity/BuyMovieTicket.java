package entity;

import java.io.Serializable;
import java.util.List;
import java.util.PrimitiveIterator;

public class BuyMovieTicket implements Serializable {
    private int totalAmount;
    private ShowTimes showTimes;
    private ServiceDetail serviceDetails;
    private float toatlPrice;

    public BuyMovieTicket(int totalAmount, ShowTimes showTimes, float toatlPrice) {
        this.totalAmount = totalAmount;
        this.showTimes = showTimes;
        this.toatlPrice = toatlPrice;
    }

    public BuyMovieTicket(int totalAmount, ShowTimes showTimes,ServiceDetail serviceDetails, float toatlPrice) {
        this.totalAmount = totalAmount;
        this.showTimes = showTimes;
        this.serviceDetails = serviceDetails;
        this.toatlPrice = toatlPrice;
    }

    public int getTotalAmount() {
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
