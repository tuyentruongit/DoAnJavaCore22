package entity;

import java.util.PrimitiveIterator;

public class BuyMovieTicket {
    private  User user;
    private int totalAmount;
    private ShowTimes showTimes;

    public BuyMovieTicket(User user, int totalAmount, ShowTimes showTimes) {
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BuyMovieTicket{" +
                "user=" + user +
                ", totalAmount=" + totalAmount +
                ", showTimes=" + showTimes +
                '}';
    }
}
