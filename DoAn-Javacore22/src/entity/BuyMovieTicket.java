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

    public ServiceDetail getServiceDetails() {
        return serviceDetails;
    }

    public float getToatlPrice() {
        return toatlPrice;
    }


    public int getTotalAmount() {
        return totalAmount;
    }


    public ShowTimes getShowTimes() {
        return showTimes;
    }



    @Override
    public String toString() {
        return "BuyMovieTicket{" +
                "totalAmount=" + totalAmount +
                ", showTimes=" + showTimes +
                '}';
    }
}
